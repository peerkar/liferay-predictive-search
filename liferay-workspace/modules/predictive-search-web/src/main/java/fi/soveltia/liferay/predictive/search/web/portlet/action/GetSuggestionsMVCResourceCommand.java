package fi.soveltia.liferay.predictive.search.web.portlet.action;

import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.blogs.model.BlogsEntry;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.search.document.Document;
import com.liferay.portal.search.hits.SearchHit;
import com.liferay.portal.search.hits.SearchHits;
import com.liferay.portal.search.searcher.SearchRequest;
import com.liferay.portal.search.searcher.SearchRequestBuilderFactory;
import com.liferay.portal.search.searcher.SearchResponse;
import com.liferay.portal.search.searcher.Searcher;

import fi.soveltia.liferay.predictive.search.web.constants.PredictiveSearchBarPortletKeys;

import java.util.List;
import java.util.Locale;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Petteri Karttunen
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + PredictiveSearchBarPortletKeys.PREDICTIVESEARCHBAR,
		"mvc.command.name=/predictive_search/get_suggestions"
	},
	service = MVCResourceCommand.class
)
public class GetSuggestionsMVCResourceCommand extends BaseMVCResourceCommand {

	@Override
	protected void doServeResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		try {
			JSONPortletResponseUtil.writeJSON(
				resourceRequest, resourceResponse,
				_toSuggestions(
					resourceRequest, resourceResponse,
					_searcher.search(_getSearchRequest(resourceRequest))));
		}
		catch (Exception exception) {
			_log.error(exception);
		}
	}

	private AssetRenderer<?> _getAssetRenderer(Document document) {
		try {
			AssetRendererFactory<?> assetRendererFactory =
				AssetRendererFactoryRegistryUtil.
					getAssetRendererFactoryByClassName(
						document.getString(Field.ENTRY_CLASS_NAME));

			if (assetRendererFactory == null) {
				return null;
			}

			return assetRendererFactory.getAssetRenderer(
				document.getLong(Field.ENTRY_CLASS_PK));
		}
		catch (PortalException portalException) {
			_log.error(portalException.getMessage(), portalException);
		}

		return null;
	}

	private SearchRequest _getSearchRequest(ResourceRequest resourceRequest) {
		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		return _searchRequestBuilderFactory.builder(
		).companyId(
			themeDisplay.getCompanyId()
		).groupIds(
			new long[] {themeDisplay.getScopeGroupId()}
		).modelIndexerClassNames(
			BlogsEntry.class.getName(), DLFileEntry.class.getName(),
			JournalArticle.class.getName()
		).queryString(
			ParamUtil.getString(resourceRequest, "keywords")
		).size(
			10
		).build();
	}

	private String _getText(
		AssetRenderer<?> assetRenderer, Document document, String languageId) {

		if (assetRenderer == null) {
			return document.getString(
				LocalizationUtil.getLocalizedName(Field.TITLE, languageId));
		}

		return assetRenderer.getTitle(LocaleUtil.fromLanguageId(languageId));
	}

	private JSONArray _toAttributes(
		AssetRenderer<?> assetRenderer, Document document, Locale locale,
		ResourceRequest resourceRequest, ResourceResponse resourceResponse) {

		JSONArray jsonArray = _jsonFactory.createJSONArray();

		try {
			jsonArray.put(
				JSONUtil.put(
					"key", "type"
				).put(
					"value",
					_language.get(
						locale,
						"model.resource." +
							document.getString(Field.ENTRY_CLASS_NAME))
				));

			jsonArray.put(
				JSONUtil.put(
					"key", "url"
				).put(
					"value",
					assetRenderer.getURLViewInContext(
						_portal.getLiferayPortletRequest(resourceRequest),
						_portal.getLiferayPortletResponse(resourceResponse),
						null)
				));
		}
		catch (Exception exception) {
			_log.error(exception);
		}

		return jsonArray;
	}

	private JSONObject _toSuggestions(
		ResourceRequest resourceRequest, ResourceResponse resourceResponse,
		SearchResponse searchResponse) {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		JSONArray jsonArray = _jsonFactory.createJSONArray();

		SearchHits hits = searchResponse.getSearchHits();

		List<SearchHit> searchHits = hits.getSearchHits();

		for (SearchHit searchHit : searchHits) {
			AssetRenderer<?> assetRenderer = _getAssetRenderer(
				searchHit.getDocument());

			jsonArray.put(
				JSONUtil.put(
					"attributes",
					_toAttributes(
						assetRenderer, searchHit.getDocument(),
						themeDisplay.getLocale(), resourceRequest,
						resourceResponse)
				).put(
					"text",
					_getText(
						assetRenderer, searchHit.getDocument(),
						themeDisplay.getLanguageId())
				));
		}

		return JSONUtil.put("suggestions", jsonArray);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		GetSuggestionsMVCResourceCommand.class);

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private Language _language;

	@Reference
	private Portal _portal;

	@Reference
	private Searcher _searcher;

	@Reference
	private SearchRequestBuilderFactory _searchRequestBuilderFactory;

}
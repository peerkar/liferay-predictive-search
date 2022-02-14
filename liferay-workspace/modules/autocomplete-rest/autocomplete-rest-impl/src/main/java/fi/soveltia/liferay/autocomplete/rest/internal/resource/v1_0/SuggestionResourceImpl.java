package fi.soveltia.liferay.autocomplete.rest.internal.resource.v1_0;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.search.document.Document;
import com.liferay.portal.search.engine.adapter.SearchEngineAdapter;
import com.liferay.portal.search.engine.adapter.search.SearchSearchRequest;
import com.liferay.portal.search.engine.adapter.search.SearchSearchResponse;
import com.liferay.portal.search.hits.SearchHit;
import com.liferay.portal.search.hits.SearchHits;
import com.liferay.portal.search.index.IndexNameBuilder;
import com.liferay.portal.search.query.BooleanQuery;
import com.liferay.portal.search.query.MultiMatchQuery;
import com.liferay.portal.search.query.Queries;
import com.liferay.portal.search.query.Query;

import fi.soveltia.liferay.autocomplete.rest.dto.v1_0.Suggestion;
import fi.soveltia.liferay.autocomplete.rest.dto.v1_0.Suggestions;
import fi.soveltia.liferay.autocomplete.rest.resource.v1_0.SuggestionResource;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Petteri Karttunen
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/suggestion.properties",
	scope = ServiceScope.PROTOTYPE, service = SuggestionResource.class
)
public class SuggestionResourceImpl extends BaseSuggestionResourceImpl {

	@Override
	public Suggestions getSuggestions(
			String keywords, String languageId, Long size)
		throws Exception {

		return new Suggestions() {
			{
				suggestions = _toSuggestions(
					languageId,
					_searchEngineAdapter.execute(
						_getSearchSearchRequest(keywords, languageId, size)));
			}
		};
	}

	private Query _getSearchQuery(String keywords, String languageId) {
		BooleanQuery booleanQuery = _queries.booleanQuery();

		booleanQuery.addFilterQueryClauses(
			_queries.term(Field.LANGUAGE_ID, languageId),
			_queries.term(Field.TYPE, "querySuggestion"));

		// Using MultiMatch instead of Match because
		// portal search API doesn't support Match Boolean Prefix
		// query.

		String keywordSearchField = 
				LocalizationUtil.getLocalizedName(
				Field.KEYWORD_SEARCH, languageId);
		
		MultiMatchQuery multiMatchQuery = _queries.multiMatch(
			keywords,
			keywordSearchField,
			StringBundler.concat(keywordSearchField, "._2gram"),
			StringBundler.concat(keywordSearchField, "._3gram"));

		multiMatchQuery.setType(MultiMatchQuery.Type.BOOL_PREFIX);

		booleanQuery.addMustQueryClauses(multiMatchQuery);

		return booleanQuery;
	}

	private SearchSearchRequest _getSearchSearchRequest(
		String keywords, String languageId, Long size) {

		return new SearchSearchRequest() {
			{
				setIndexNames(
					_indexNameBuilder.getIndexName(
						contextCompany.getCompanyId()));
				setQuery(_getSearchQuery(keywords, languageId));
				setSize(size.intValue());
			}
		};
	}

	private Suggestion[] _toSuggestions(
		String languageId, SearchSearchResponse searchSearchResponse) {

		List<Suggestion> suggestions = new ArrayList<>();

		SearchHits hits = searchSearchResponse.getSearchHits();

		List<SearchHit> searchHits = hits.getSearchHits();

		for (SearchHit searchHit : searchHits) {
			Document document = searchHit.getDocument();

			suggestions.add(
				new Suggestion() {
					{
						text = document.getString(
							LocalizationUtil.getLocalizedName(
								Field.KEYWORD_SEARCH, languageId));
					}
				});
		}

		return suggestions.toArray(new Suggestion[0]);
	}

	@Reference
	private IndexNameBuilder _indexNameBuilder;

	@Reference
	private Queries _queries;

	@Reference
	private SearchEngineAdapter _searchEngineAdapter;

}
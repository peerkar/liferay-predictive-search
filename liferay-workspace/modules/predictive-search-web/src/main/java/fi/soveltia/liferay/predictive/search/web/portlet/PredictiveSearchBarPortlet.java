package fi.soveltia.liferay.predictive.search.web.portlet;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchRequest;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchResponse;
import com.liferay.portal.search.web.search.request.SearchSettings;

import java.io.IOException;
import java.util.Optional;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import fi.soveltia.liferay.predictive.search.web.constants.PredictiveSearchBarPortletKeys;

/**
 * @author Petteri Karttunen
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=PredictiveSearchBar",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + PredictiveSearchBarPortletKeys.PREDICTIVESEARCHBAR,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class PredictiveSearchBarPortlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		PortletSharedSearchResponse portletSharedSearchResponse =
			_portletSharedSearchRequest.search(renderRequest);

		Optional<String> keywordsOptional =
			portletSharedSearchResponse.getKeywordsOptional();

		renderRequest.setAttribute(
			"keywords", keywordsOptional.orElse(StringPool.BLANK));

		SearchSettings searchSettings =
			portletSharedSearchResponse.getSearchSettings();

		Optional<String> keywordsParameterNameOptional =
			searchSettings.getKeywordsParameterName();

		renderRequest.setAttribute(
			"keywordsParameterName", keywordsParameterNameOptional.orElse("q"));

		super.render(renderRequest, renderResponse);
	}

	@Reference
	private PortletSharedSearchRequest _portletSharedSearchRequest;

}
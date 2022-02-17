package fi.soveltia.liferay.predictive.search.web.portlet.shared.search;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.search.searcher.SearchRequestBuilder;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchContributor;
import com.liferay.portal.search.web.portlet.shared.search.PortletSharedSearchSettings;

import fi.soveltia.liferay.predictive.search.web.constants.PredictiveSearchBarPortletKeys;

import java.util.Optional;

import org.osgi.service.component.annotations.Component;

/**
 * @author Petteri Karttunen
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + PredictiveSearchBarPortletKeys.PREDICTIVESEARCHBAR,
	service = PortletSharedSearchContributor.class
)
public class PredictiveSearchBarSharedSearchContributor
	implements PortletSharedSearchContributor {

	@Override
	public void contribute(
		PortletSharedSearchSettings portletSharedSearchSettings) {

		SearchRequestBuilder searchRequestBuilder =
			portletSharedSearchSettings.getSearchRequestBuilder();

		Optional<String> keywordsParameterNameOptional =
			portletSharedSearchSettings.getKeywordsParameterName();

		Optional<String> keywordsOptional =
			portletSharedSearchSettings.getParameterOptional(
				keywordsParameterNameOptional.orElse("q"));

		searchRequestBuilder.queryString(
			keywordsOptional.orElse(StringPool.BLANK));
	}

}
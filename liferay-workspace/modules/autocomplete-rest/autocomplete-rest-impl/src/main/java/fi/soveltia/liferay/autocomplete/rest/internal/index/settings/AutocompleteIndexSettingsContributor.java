package fi.soveltia.liferay.autocomplete.rest.internal.index.settings;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.elasticsearch7.settings.IndexSettingsContributor;
import com.liferay.portal.search.elasticsearch7.settings.IndexSettingsHelper;
import com.liferay.portal.search.elasticsearch7.settings.TypeMappingsHelper;

import org.osgi.service.component.annotations.Component;

/**
 * @author Petteri Karttunen
 */
@Component(
	enabled = true, immediate = true, service = IndexSettingsContributor.class
)
public class AutocompleteIndexSettingsContributor implements IndexSettingsContributor {

	@Override
	public int compareTo(IndexSettingsContributor indexSettingsContributor) {
		if (getPriority() > indexSettingsContributor.getPriority()) {
			return 1;
		}
		else if (getPriority() == indexSettingsContributor.getPriority()) {
			return 0;
		}

		return -1;
	}

	@Override
	public void contribute(
		String indexName, TypeMappingsHelper typeMappingsHelper) {

		typeMappingsHelper.addTypeMappings(
			indexName, StringUtil.read(getClass(), _MAPPINGS_RESOURCE_NAME));
	}

	@Override
	public int getPriority() {
		return 1;
	}

	@Override
	public void populate(IndexSettingsHelper indexSettingsHelper) {
	}

	private static final String _MAPPINGS_RESOURCE_NAME =
		"/META-INF/search/autocomplete-type-mappings.json";

}
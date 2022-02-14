package fi.soveltia.liferay.autocomplete.rest.internal.graphql.servlet.v1_0;

import com.liferay.portal.vulcan.graphql.servlet.ServletData;

import fi.soveltia.liferay.autocomplete.rest.internal.graphql.mutation.v1_0.Mutation;
import fi.soveltia.liferay.autocomplete.rest.internal.graphql.query.v1_0.Query;
import fi.soveltia.liferay.autocomplete.rest.resource.v1_0.SuggestionResource;

import javax.annotation.Generated;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;

/**
 * @author liferay
 * @generated
 */
@Component(immediate = true, service = ServletData.class)
@Generated("")
public class ServletDataImpl implements ServletData {

	@Activate
	public void activate(BundleContext bundleContext) {
		Query.setSuggestionResourceComponentServiceObjects(
			_suggestionResourceComponentServiceObjects);
	}

	@Override
	public Mutation getMutation() {
		return new Mutation();
	}

	@Override
	public String getPath() {
		return "/autocomplete-rest-graphql/v1_0";
	}

	@Override
	public Query getQuery() {
		return new Query();
	}

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<SuggestionResource>
		_suggestionResourceComponentServiceObjects;

}
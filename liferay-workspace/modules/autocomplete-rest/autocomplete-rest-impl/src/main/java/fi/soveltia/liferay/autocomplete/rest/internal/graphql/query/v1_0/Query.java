package fi.soveltia.liferay.autocomplete.rest.internal.graphql.query.v1_0;

import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.pagination.Page;

import fi.soveltia.liferay.autocomplete.rest.dto.v1_0.Suggestion;
import fi.soveltia.liferay.autocomplete.rest.dto.v1_0.Suggestions;
import fi.soveltia.liferay.autocomplete.rest.resource.v1_0.SuggestionResource;

import java.util.Map;
import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author liferay
 * @generated
 */
@Generated("")
public class Query {

	public static void setSuggestionResourceComponentServiceObjects(
		ComponentServiceObjects<SuggestionResource>
			suggestionResourceComponentServiceObjects) {

		_suggestionResourceComponentServiceObjects =
			suggestionResourceComponentServiceObjects;
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {suggestions(keywords: ___, languageId: ___, size: ___){suggestions}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public Suggestions suggestions(
			@GraphQLName("keywords") String keywords,
			@GraphQLName("languageId") String languageId,
			@GraphQLName("size") Long size)
		throws Exception {

		return _applyComponentServiceObjects(
			_suggestionResourceComponentServiceObjects,
			this::_populateResourceContext,
			suggestionResource -> suggestionResource.getSuggestions(
				keywords, languageId, size));
	}

	@GraphQLName("SuggestionPage")
	public class SuggestionPage {

		public SuggestionPage(Page suggestionPage) {
			actions = suggestionPage.getActions();

			items = suggestionPage.getItems();
			lastPage = suggestionPage.getLastPage();
			page = suggestionPage.getPage();
			pageSize = suggestionPage.getPageSize();
			totalCount = suggestionPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<Suggestion> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(SuggestionResource suggestionResource)
		throws Exception {

		suggestionResource.setContextAcceptLanguage(_acceptLanguage);
		suggestionResource.setContextCompany(_company);
		suggestionResource.setContextHttpServletRequest(_httpServletRequest);
		suggestionResource.setContextHttpServletResponse(_httpServletResponse);
		suggestionResource.setContextUriInfo(_uriInfo);
		suggestionResource.setContextUser(_user);
		suggestionResource.setGroupLocalService(_groupLocalService);
		suggestionResource.setRoleLocalService(_roleLocalService);
	}

	private static ComponentServiceObjects<SuggestionResource>
		_suggestionResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private com.liferay.portal.kernel.model.Company _company;
	private BiFunction<Object, String, Filter> _filterBiFunction;
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;

}
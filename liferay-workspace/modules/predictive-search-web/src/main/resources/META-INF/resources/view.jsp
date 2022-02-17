<%@ include file="/init.jsp" %>

<%
String keywords = (String)request.getAttribute("keywords");

String keywordsParameterName = (String)request.getAttribute("keywordsParameterName");
%>

<portlet:resourceURL id="get_suggestions" var="resourceURL" />

<aui:fieldset cssClass="search-bar">
	<div class="input-group search-bar-simple">
		<div class="input-group-item search-bar-keywords-input-wrapper">
			<input
				autofocus
				autocomplete="off"
				class="form-control input-group-inset input-group-inset-after search-bar-keywords-input"
				data-qa-id="searchInput"
				id="<portlet:namespace />inputField"
				name="<%= keywordsParameterName %>"
				placeholder="<liferay-ui:message key="keywords" />"
				title="<liferay-ui:message key="search" />"
				type="text"
				value="<%= keywords %>"
			/>

			<div class="input-group-inset-item input-group-inset-item-after" id="<portlet:namespace />submit">
				<button aria-label="<liferay-ui:message key="submit" />" class="btn btn-unstyled" type="submit">
					<clay:icon symbol="search" />
				</button>
			</div>
		</div>
	</div>
</aui:fieldset>

<aui:script require="predictive-search-web@1.0.0 as main">
	main.default('<%=keywordsParameterName %>', '<portlet:namespace />', '<%= resourceURL %>');
</aui:script>
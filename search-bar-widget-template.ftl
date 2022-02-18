<style>

	.autocomplete-suggestions { 
		background: #fff; 
		border: none;
		box-shadow: 0 1px 10px -1px rgba(109, 109, 109, 0.3);
		max-height: none!important;
		overflow: auto; 
		padding: 25px;
	}

	.autocomplete-group { 
		font-weight: 700;
		padding: 0px 0 10px 0;
	}

	.autocomplete-suggestion { 
		margin: 0 0 5px 0;
		padding: 5px;
		white-space: normal;
	}

	.autocomplete-suggestion .title {
		overflow-x: hidden;
		text-overflow: ellipsis;			
		white-space: nowrap;
	}

	.autocomplete-selected {
		background: #F0F0F0; 
	}

</style>

<@liferay_aui.fieldset cssClass="search-bar">

	<#assign inputFieldId = namespace + stringUtil.randomId() />

	<div class="input-group search-bar-simple">
		<div class="input-group-item search-bar-keywords-input-wrapper">
			<input
				autofocus
				autocomplete="off"
				class="form-control input-group-inset input-group-inset-after search-bar-keywords-input"
				data-qa-id="searchInput"
				id="${inputFieldId}"
				name="${htmlUtil.escape(searchBarPortletDisplayContext.getKeywordsParameterName())}"
				placeholder="${searchBarPortletDisplayContext.getInputPlaceholder()}"
				title="${languageUtil.get(locale, "search")}"
				type="text"
				value="${htmlUtil.escape(searchBarPortletDisplayContext.getKeywords())}"
			/>

			<div class="input-group-inset-item input-group-inset-item-after">
				<button aria-label="${languageUtil.get(locale, "submit")}" class="btn btn-unstyled" type="submit">
					<@clay.icon symbol="search" />
				</button>
			</div>

			<@liferay_aui.input
				name=htmlUtil.escape(searchBarPortletDisplayContext.getScopeParameterName())
				type="hidden"
				value=searchBarPortletDisplayContext.getScopeParameterValue()
			/>
		</div>
	</div>
</@>

<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.devbridge-autocomplete/1.4.11/jquery.autocomplete.min.js" integrity="sha512-uxCwHf1pRwBJvURAMD/Gg0Kz2F2BymQyXDlTqnayuRyBFE7cisFCh2dSb1HIumZCRHuZikgeqXm8ruUoaxk5tA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<script type="text/javascript">

	Liferay.Portlet.ready(function() {
	
		const containerInstance = 'autocomplete-suggestions-${stringUtil.randomId()}';

		const inputElement = $('#${inputFieldId}');

		function doSearch(keywords) {
			window.location.href = getCurrentBaseURL() + "?q=" + encodeURIComponent(keywords);
		}

		function getCurrentBaseURL() {
			let currentURL = window.location.href;

			if (currentURL.indexOf('?') > 0) {
				return currentURL.split('?')[0];
			}
			return currentURL;
		}	

		function initAutocomplete() {
			$(inputElement).devbridgeAutocomplete({
				containerClass: 'autocomplete-suggestions hide ' + containerInstance,
				dataType: 'json',
				deferRequestBy: 50,
				formatResult: function (suggestion, currentValue) {
					return suggestion.value;
				},
				onSelect: function (suggestion) {
					doSearch(suggestion.data.text);
				},
				paramName: 'keywords',
				preserveInput: true,
				preventBadQueries: false,
				serviceUrl: function(currentValue) {
					let serviceURL = new URL('/o/autocomplete-rest/v1.0/suggestions', Liferay.ThemeDisplay.getPortalURL());

					serviceURL.searchParams.append('languageId', themeDisplay.getLanguageId());
					serviceURL.searchParams.append('p_auth', Liferay.authToken);
					serviceURL.searchParams.append('size', 10);
					return serviceURL;	    
				},				
				transformResult: function(response) {

					if (response) {
						return {
							suggestions: function() {
								return $.map(response.suggestions, function(item) {
									return {
										value: '<div title="' + item.text + '" class="title">' + item.text + '</div>', 
										data: {
											text: item.text
										}
									};
								});
							}()
						};
					}
				},
				triggerSelectOnValidInput: false,
				type: "GET"		
			});
		}
		
		function initListeners() {
			$(inputElement).on('keyup', function(event) {
		  	$('.' + containerInstance).removeClass('hide');
			});
		}

		initAutocomplete();
	   	
		initListeners();
	});

</script>
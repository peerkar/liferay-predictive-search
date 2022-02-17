import jQuery from 'jquery';
import devbridgeAutocomplete from 'devbridge-autocomplete';

export default function (keywordsParameterName, portletNamespace, resourceURL) {

    const inputElement = jQuery('#' + portletNamespace + 'inputField');

    const instance = 'autocomplete-suggestions-' + portletNamespace;

    const minChars = 2;

    function doSearch() {

        let keywords = jQuery(inputElement).val();

        if (keywords.length >= minChars) {
            window.location.href = getCurrentBaseURL() + "?" +
            	keywordsParameterName + "=" + encodeURIComponent(keywords);
        }
    }

    function getCurrentBaseURL() {
        let currentURL = window.location.href;
        if (currentURL.indexOf('?') > 0) {
            return currentURL.split('?')[0];
        }
        return currentURL;
    }

    function getAttribute(item, name) {

        let obj = item.attributes.find(o => o.key === name);

        if (obj == null) {
            return '';
        }
        return obj.value;
    }

    function handleKeyUp(event) {

        var keycode = (event.keyCode ? event.keyCode : event.which);

        if (keycode === 13) {

            if (!jQuery('.autocomplete-selected').length) {
                doSearch();
            }
        }
    }

    function initAutocomplete() {
        jQuery(inputElement).devbridgeAutocomplete({
            containerClass: 'autocomplete-suggestions hide ' + instance,
            dataType: 'json',
            deferRequestBy: 50,
            formatResult: function (suggestion, currentValue) {
                return suggestion.value;
            },
            groupBy: 'type',
            minChars: minChars,
            onSelect: function (suggestion) {
                location.href = suggestion.data.url;
            },
            paramName: portletNamespace + 'keywords',
            preserveInput: true,
            preventBadQueries: false,
            serviceUrl: resourceURL,
            transformResult: function (response) {

                if (response) {
                    return {
                        suggestions: function () {
                            return jQuery.map(response.suggestions, function (item) {
                                let url = getAttribute(item, 'url');
                                return {
                                    value: '<div title="' + item.text + 
                                    	'" class="title"><a href="' + url + '">' + item.text + '</a></div>',
                                    data: {
                                        type: getAttribute(item, 'type'),
                                        url: url
                                    }
                                };
                            });
                        }().sort(sortByType)
                    };
                }
            },
            triggerSelectOnValidInput: false,
            type: "GET"
        });
    }

    function initListeners() {

        jQuery(inputElement).on('keyup', function (event) {
            handleKeyUp(event);
            jQuery('.' + instance).removeClass('hide');
        });

        jQuery('#' + portletNamespace + 'submit').on('click', function (event) {
            doSearch();
        });

    }

    function sortByType(A, B) {

        return ((A.data.type == B.data.type) ? 0 : ((A.data.type > B.data.type) ? 1 : -1));
    }

    initAutocomplete();

    initListeners();
}
'use strict';

var NewsPagination = function () {
};

NewsPagination.prototype = function () {
    var newsDiv = $('#news-section'),
        loaderDiv = $('#news-loader'),
        apiUrl = 'mocks/news-list.json.php',
        newsTemplate = 'templates/news-item.html',
        msgNoNews = 'Currently, there are no news.',
        apiVar = 'stories',
        newsCache = 'newsCache',
        timeout = 25000,
        options = {
            itemsPerPage: 7,
            // available variables (will be space-separated): year, month, day, fullHour
            unixDatesFormat: ['day', 'month', 'year', 'fullHour']
        },

        init = function () {

            loaderDiv.show();
            loadTemplateFromFile(newsTemplate)
                .done(function (newsTemplate) {
                    var cache = sessionStorage.getItem(newsCache);
                    if (cache) {
                        newsDiv.customPagination(newsTemplate, JSON.parse(cache), options);
                        loaderDiv.hide();
                    } else {
                        getApiContent()
                            .done(function (apiData) {
                                newsDiv.customPagination(newsTemplate, apiData[apiVar], options);
                                sessionStorage.setItem(newsCache, JSON.stringify(apiData[apiVar]));
                            })
                            .fail(function () {
                                newsDiv.text(msgNoNews).addClass('centered');
                            })
                            .always(function () {
                                loaderDiv.hide();
                            });
                    }
                });
        },

        getApiContent = function () {
            return $.ajax({
                url: apiUrl,
                dataType: 'json',
                timeout: timeout
            });
        },

        loadTemplateFromFile = function (path) {
            return $.ajax({
                url: path,
                async: false,
                dataType: 'text',
                cache: false
            });
        };

    return {
        init: init
    };


}();
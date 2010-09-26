/*
{"data":[{"name":"To Kill a Mockingbird",
"category":"Book","id":"105709882797285",
"created_time":"2010-09-22T22:19:18+0000"},
{"name":"Cricket","category":"Sport",
"id":"103992339636529","created_time":"2010-09-22T22:19:18+0000"}]}
 */
function getSearchText(dataArray) {
    var searchText = '';

    $.each(jsonParse(dataArray).data, function(i, item) {
        if (i > 0) {
            searchText += ' ';
        }
        searchText += item.name;
    });
    return searchText;
}

function doSearch(searchText) {
    url = 'http://www.toysrus.com/search/index.jsp?kw=' + $.URLEncode(searchText);    
    var html = '<b>Keywords:</b>' + searchText;
    html += '<br><a href="' + url + '">Go to ToysRUs search</a>';
    $('#likes').html(html);
}

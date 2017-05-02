var BACKEND_PATH = "http://localhost:8080/api";
var MAX_PRIORITY = 4;
var ROUTES = {
    index: "/",
    login: "/",
    projects: "projects.jsp",
    settings: "settings.jsp",
    administration: "administration.jsp"
};

$.postJSON = function(url, data, callback) {
    return jQuery.ajax({
        'type': 'POST',
        'url': url,
        'contentType': 'application/json; charset=utf-8',
        'data': JSON.stringify(data),
        'dataType': 'json',
        'success': callback
    });
};

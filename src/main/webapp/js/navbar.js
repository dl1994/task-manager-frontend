var me = {};
var initializer = {
    successCallback: function () {}
};

function initialize() {
    $.get(BACKEND_PATH + "/users/me", {}, function (data) {
        me = data;
        $("#user-login-text").text("Welcome, " + me.firstName + " " + me.lastName);
        initializer.successCallback();
    }).fail(function (response) {
        if (response.status === 401) {
            window.location.href = ROUTES["login"];
        }
    });
}

function logout() {
    $.get(BACKEND_PATH + "/logout", {}, function () {
        me = {};
        window.location.href = ROUTES["login"];
    });
}

window.onload = initialize;

function showNotifications() {
    $("#notifications").toggle();
}

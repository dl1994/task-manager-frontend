var me = {};
var selectedButton = {
    hasClass: function() {
        return false;
    }, addClass: function() {}
};
var initializer = {
    successCallback: function() {}
};

function initialize() {
    $.get(BACKEND_PATH + "/users/me", {}, function(data) {
        me = data;

        if (me.role === "ROLE_ADMIN") {
            $("#nav-administration").show();
        }

        $("#user-login-text").text("Welcome, " + me.firstName + " " + me.lastName);
        // TODO load notifications
        initializer.successCallback();
    }).fail(function(response) {
        if (response.status === 401) {
            window.location.href = ROUTES["login"];
        }
    });
}

function logout() {
    $.get(BACKEND_PATH + "/logout", {}, function() {
        me = {};
        window.location.href = ROUTES["login"];
    }).fail(function() {
        // TODO show logout error
    });
}

function goTo(page) {
    window.location.href = ROUTES[page]; // TODO use AJAX
}

function selectButton(navButtonId) {
    if (selectedButton.hasClass("nav-button-selected")) {
        selectedButton.removeClass("nav-button-selected");
        selectedButton.addClass("nav-button");
    }

    selectedButton = $(navButtonId);
    selectedButton.addClass("nav-button-selected");
    selectedButton.removeClass("nav-button");
}

function selectButtonOnLoad(navButtonId) {
    return function() {
        selectButton(navButtonId);
    }
}

function showNotifications() {
    $("#notifications").toggle();
}

$(window).on("load", initialize);

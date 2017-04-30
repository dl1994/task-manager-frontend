function initialize() {
    $.get(BACKEND_PATH + "/users/me", {}, function () {
        window.location.href = ROUTES["projects"];
    });
}

window.onload = initialize;

function login() {
    $.post(BACKEND_PATH + "/login", $("#login-form").serialize(), function () {
        window.location.href = ROUTES["projects"];
    }).fail(function (response) {
        if (response.status === 401) {
            var fields = $(".form-field");

            if (!fields.hasClass("form-error")) {
                fields.addClass("form-error");
            }

            for (var i = 0; i < fields.length; i++) {
                fields[i].value = "";
            }

            $("#error-message").show();
        }
    });
}

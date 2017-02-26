function login() {
    $.post(BACKEND_PATH + "/login", "data");

    var fields = $(".form-field");

    if (!fields.hasClass(".form-error")) {
        fields.addClass("form-error");
    }

    for (var i = 0; i < fields.length; i++) {
        fields[i].value = "";
    }

    $(".error-message").show();
}

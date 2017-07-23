// initializer.successCallback = fillPersonalInfoForm;
// $(document).ready(selectButtonOnLoad("#nav-settings"));

// function fillPersonalInfoForm() {
//     var form = $("#edit-personal-info-form");
//
//     form.find('[name="firstName"]').val(me.firstName);
//     form.find('[name="lastName"]').val(me.lastName);
// }

// function editPersonalInformation() {
//     $("#pi-edit").hide();
//     $("#pi-edit-cancel").show();
//     $("#edit-personal-info-form").find("input").removeAttr("disabled");
// }

// function cancelPersonalInformationEdit() {
//     $("#edit-personal-info-form").find("input").attr("disabled", "disabled");
//     fillPersonalInfoForm();
//     $("#pi-edit-cancel").hide();
//     $("#pi-edit").show();
// }

// function savePersonalInformation() {
//     var form = $("#edit-personal-info-form");
//     var req = {
//         firstName: form.find('[name="firstName"]').val(),
//         lastName: form.find('[name="lastName"]').val()
//     };
//
//     form.find("input").attr("disabled", "disabled");
//     $.postJSON(BACKEND_PATH + "/users/me/change-info", req, function(data) {
//         me = data;
//         cancelPersonalInformationEdit();
//         setWelcomeText();
//     }).fail(function() {
//         // TODO handle this, show error
//         cancelPersonalInformationEdit();
//     });
// }

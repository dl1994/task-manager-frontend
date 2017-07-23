// var me = {};
// var selectedButton = {
//     hasClass: function() {
//         return false;
//     }, addClass: function() {
//     }
// };
// var initializer = {
//     successCallback: function() {
//     }
// };

// function setWelcomeText() {
//     $("#user-login-text").text(me.firstName + " " + me.lastName);
// }

// function initialize() {
//     $.get(BACKEND_PATH + "/users/me", {}, function(data) {
//         me = data;
//
//         if (me.role === "ROLE_ADMIN") {
//             $("#nav-administration").show();
//         }
//
//         setWelcomeText();
//         setNotificationCount();
//         initializer.successCallback();
//     }).fail(function(response) {
//         if (response.status === 401) {
//             window.location.href = ROUTES["login"];
//         } else {
//             // TODO handle other errors
//         }
//     });
// }

// function setNotificationCount() {
//     $.get(BACKEND_PATH + "/notifications/unseen-count", {}, function(count) {
//         $("#notifications-count").text(count);
//
//         if (count > 0) {
//             $("#notifications-div").addClass("notifications-div-highlight");
//         }
//     }).fail(function() {
//         // TODO handle this
//     });
// }

// function logout() {
//     $.get(BACKEND_PATH + "/logout", {}, function() {
//         me = {};
//         window.location.href = ROUTES["login"];
//     }).fail(function() {
//         // TODO show logout error
//     });
// }

// function goTo(page) {
//     window.location.href = ROUTES[page]; // TODO use AJAX
// }

// function selectButton(navButtonId) {
//     if (selectedButton.hasClass("nav-button-selected")) {
//         selectedButton.removeClass("nav-button-selected");
//         selectedButton.addClass("nav-button");
//     }
//
//     selectedButton = $(navButtonId);
//     selectedButton.addClass("nav-button-selected");
//     selectedButton.removeClass("nav-button");
// }

// function selectButtonOnLoad(navButtonId) {
//     return function() {
//         selectButton(navButtonId);
//     }
// }

// function showNotifications() {
//     $("#notifications").toggle();
//
//     // TODO load notifications
//
//     /*setTimeout(function() {
//      $("#no-new-notifications").hide();
//      }, 3000);*/
// }

// function dismissNotification(id) {
//     // TODO backend
//
//     $("#notification-" + id).remove();
//
//     var table = $("#notifications-table");
//
//     if (table.find("tr").length === 0) {
//         table.hide();
//         $("#no-new-notifications").show();
//     }
// }

// $(window).on("load", initialize);

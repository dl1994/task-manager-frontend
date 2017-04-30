$(document).ready(function() {
    $("#modal").click(function() {
        $(this).hide();
    }).children().click(function() {
        return false;
    });
});

function showModal() {
    $("#modal").show();
}

function hideModal() {
    $("#modal").hide();
}

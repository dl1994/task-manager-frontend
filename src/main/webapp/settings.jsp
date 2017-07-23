<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="partial/head-includes.html" %>
        <script src="js/settings.js" type="text/javascript"></script>
        <script type="text/javascript">
            Settings.init()
        </script>
        <link rel="stylesheet" type="text/css" href="css/settings.css"/>
        <title>Settings</title>
    </head>
    <body class="standard-theme">
        <%@ include file="partial/navbar.html" %>
        <div id="body-div" class="body-div">
            <div class="contents">
                <h1>Settings</h1>
                <div class="header-with-button">
                    <h2 class="inline-header button-header">Personal Information</h2>
                    <button id="pi-edit" class="button confirm-button medium-button" title="Edit"
                            onclick="Settings.editPersonalInformation();">
                        <img height="12" src="img/edit.svg" alt="Edit"/>
                    </button>
                    <button id="pi-edit-cancel" class="button danger-button medium-button"
                            title="Cancel" onclick="Settings.cancelPersonalInformationEdit();" style="display: none;">
                        <img height="12" src="img/delete.svg" alt="Cancel"/>
                    </button>
                </div>
                <form id="edit-personal-info-form" class="editable-form" action="javascript:void(0);"
                      onsubmit="Settings.savePersonalInformation();" autocomplete="off">
                    <h4 class="inline-header">First Name:</h4>
                    <input type="text" class="form-field editable-field" name="firstName" placeholder="First Name"
                           value="" disabled/><br/>
                    <h4 class="inline-header">Last Name:</h4>
                    <input type="text" class="form-field editable-field" name="lastName" placeholder="Last Name"
                           value="" disabled/><br/>
                    <input type="submit" class="button confirm-button medium-button form-button editable-form-button"
                           value="Save" disabled/>
                </form>
                <br/><br/>
                <button class="button confirm-button large-button" title="Change Password" onclick="">
                    <img class="large-button-img" height="15" src="img/lock.svg" alt="Change Password"/>Change Password
                </button>
                <h2>Theme</h2>
                <div class="outer-theme-div outer-theme-div-selected">
                    <div class="theme-div standard-theme">
                        <h3 class="theme-name">Standard Theme</h3>
                        <div class="theme-color-square theme-tile-1">Nav.</div>
                        <div class="theme-color-square theme-tile-2">Nav. btn.</div>
                        <div class="theme-color-square theme-tile-3">Notif.</div>
                        <div class="theme-color-square theme-tile-4">Succ. btn.</div>
                        <div class="theme-color-square theme-tile-5">Conf. btn.</div>
                        <div class="theme-color-square theme-tile-6">Dang. btn.</div>
                        <div class="theme-color-square theme-tile-7">Tab. head.</div>
                    </div>
                </div>
                <div class="outer-theme-div">
                    <div class="theme-div standard-theme-dark">
                        <h3 class="theme-name">Standard Theme (Dark)</h3>
                        <div class="theme-color-square theme-tile-1">Nav.</div>
                        <div class="theme-color-square theme-tile-2">Nav. btn.</div>
                        <div class="theme-color-square theme-tile-3">Notif.</div>
                        <div class="theme-color-square theme-tile-4">Succ. btn.</div>
                        <div class="theme-color-square theme-tile-5">Conf. btn.</div>
                        <div class="theme-color-square theme-tile-6">Dang. btn.</div>
                        <div class="theme-color-square theme-tile-7">Tab. head.</div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

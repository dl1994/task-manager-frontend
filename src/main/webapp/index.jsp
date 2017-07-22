<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Login</title>
        <script src="config.js" type="text/javascript"></script>
        <script src="js/src.js" type="text/javascript"></script>
        <script>
            window.onload = Index.init;
        </script>
        <link rel="stylesheet" type="text/css" href="css/theme/standard-theme.css"/>
        <link rel="stylesheet" type="text/css" href="css/shared.css"/>
        <link rel="stylesheet" type="text/css" href="css/index.css"/>
        <link rel="stylesheet" type="text/css" href="css/message.css"/>
        <link rel="icon" type="image/x-icon" href="img/favicon.ico">
    </head>
    <body class="standard-theme">
        <%@ include file="partial/message.html" %>
        <div class="outer-div">
            <h1>Sign In</h1>
            <form id="login-form" action="javascript:void(0);" onsubmit="Index.login();">
                <input class="form-field" name="username" placeholder="Username"/><br/>
                <input type="password" class="form-field" name="password" placeholder="Password"/><br/>
                <input type="submit" class="button confirm-button medium-button form-button" value="Login"/>
            </form>
        </div>
    </body>
</html>

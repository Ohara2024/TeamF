<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ログイン</title>
</head>
<body>
    <h2>ログイン</h2>
    <form action="/TeamF/FrontController" method="post">
        <input type="hidden" name="action" value="executeLogin">
        ID: <input type="text" name="id"><br>
        パスワード: <input type="password" name="password"><br>
        <input type="submit" value="ログイン">
    </form>
    <% if (request.getAttribute("error") != null) { %>
        <p style="color:red;"><%= request.getAttribute("error") %></p>
    <% } %>
</body>
</html>
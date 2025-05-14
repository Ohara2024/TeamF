<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>ログイン</title>
</head>
<body>
    <h2>ログイン</h2>
    <form action="FrontController" method="post">
        <input type="hidden" name="action" value="executeLogin">
        <div>ID: <input type="text" name="id"></div>
        <div>パスワード: <input type="password" name="password"></div>
        <div><input type="submit" value="ログイン"></div>
    </form>
    <% if (request.getAttribute("error") != null) { %>
        <p style="color:red;"><%= request.getAttribute("error") %></p>
    <% } %>
</body>
</html>
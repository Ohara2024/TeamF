<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>メニュー</title>
</head>
<body>
    <h2>メニュー</h2>
    <p>ようこそ、<%= session.getAttribute("teacherName") %> さん！</p>
    <form action="/TeamF/FrontController" method="post">
        <input type="hidden" name="action" value="logout">
        <input type="submit" value="ログアウト">
    </form>
</body>
</html>
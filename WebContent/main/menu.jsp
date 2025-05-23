<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>メニュー</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            display: flex;
            height: 100vh;
        }
        .sidebar {
            width: 200px;
            background-color: #fff;
            padding: 20px;
            border-right: 1px solid #ccc;
        }
        .sidebar ul {
            list-style-type: none;
            padding: 0;
        }
        .sidebar ul li {
            margin-bottom: 10px;
        }
        .sidebar ul li a {
            text-decoration: none;
            color: #007bff;
            display: block;
            padding: 10px;
            background-color: #f8d7da;
            border-radius: 5px;
            text-align: center;
        }
        .sidebar ul li a:hover {
            background-color: #f1aeb5;
        }
        .main-content {
            flex-grow: 1;
            padding: 20px;
            text-align: center;
        }
        .card {
            display: inline-block;
            width: 200px;
            padding: 20px;
            margin: 10px;
            background-color: #d1e7dd;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        .card.logout {
            background-color: #cce5ff;
        }
        .card.logout input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        .card.logout input[type="submit"]:hover {
            background-color: #0056b3;
        }
        .footer {
            text-align: center;
            padding: 10px;
            background-color: #e9ecef;
            position: fixed;
            bottom: 0;
            width: 100%;
            color: #666;
        }
    </style>
</head>
<body>
    <div class="sidebar">
        <ul>
            <li><a href="#">メニュー</a></li>
            <li><a href="#">学生管理</a></li>
            <li><a href="#">成績管理</a></li>
            <li><a href="#">成績登録</a></li>
            <li><a href="/TeamF/testmanager/list">成績参照</a></li>
        </ul>
    </div>
    <div class="main-content">
        <h2>メニュー</h2>
        <p>ようこそ、<%= session.getAttribute("teacherName") %> さん！</p>
        <div class="card logout">
            <form action="/TeamF/FrontController" method="post">
                <input type="hidden" name="action" value="logout">
                <input type="submit" value="ログアウト">
            </form>
        </div>
    </div>
    <div class="footer">
        © 2023 TIC 大原学園
    </div>
</body>
</html>
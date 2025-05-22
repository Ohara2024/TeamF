<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>科目登録完了</title>
<style>
    body { margin: 0; font-family: sans-serif; }
    .layout-container { display: flex; min-height: calc(100vh - 80px); }
    .sidebar-area { width: 200px; background-color: #e9e9e9; padding: 10px; box-sizing: border-box; flex-shrink: 0; }
    .main-content-area { flex-grow: 1; padding: 20px; box-sizing: border-box; }
    .message-box { padding: 20px; background-color: #e6ffe6; border: 1px solid #66cc66; border-radius: 5px; text-align: center; margin-top: 30px;}
    .message-box p { font-size: 1.2em; color: #333; }
    .message-box button { padding: 8px 15px; margin-top: 15px; background-color: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer; }
    .message-box button:hover { background-color: #0056b3; }
</style>
</head>
<body>
    <%@ include file="header.jsp" %>
    <div class="layout-container">
        <div class="sidebar-area">
            <%@ include file="sidebar.jsp" %>
        </div>
        <div class="main-content-area">
            <h1>科目登録完了</h1>
            <div class="message-box">
                <p>登録が完了しました</p>
                <button type="button" onclick="location.href='SubjectList'">科目一覧</button>
            </div>
        </div>
    </div>
    <%@ include file="footer.jsp" %>
</body>
</html>
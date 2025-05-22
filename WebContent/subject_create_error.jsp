<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>科目登録エラー</title>
<style>
    body { margin: 0; font-family: sans-serif; }
    .layout-container { display: flex; min-height: calc(100vh - 80px); }
    .sidebar-area { width: 200px; background-color: #e9e9e9; padding: 10px; box-sizing: border-box; flex-shrink: 0; }
    .main-content-area { flex-grow: 1; padding: 20px; box-sizing: border-box; }
    .error-message-box { padding: 20px; background-color: #ffe6e6; border: 1px solid #cc6666; border-radius: 5px; margin-top: 30px; text-align: center;}
    .error-message-box p { font-size: 1.2em; color: #cc0000; }
    .error-message-box ul { color: #cc0000; list-style-type: disc; margin-left: 20px; text-align: left;}
    .error-message-box button { padding: 8px 15px; margin-top: 15px; background-color: #6c757d; color: white; border: none; border-radius: 4px; cursor: pointer; }
    .error-message-box button:hover { background-color: #5a6268; }
</style>
</head>
<body>
    <%@ include file="header.jsp" %>
    <div class="layout-container">
        <div class="sidebar-area">
            <%@ include file="sidebar.jsp" %>
        </div>
        <div class="main-content-area">
            <h1>科目登録エラー</h1>
            <%
                List<String> errors = (List<String>) request.getAttribute("errors");
            %>
            <div class="error-message-box">
                <% if (errors != null && !errors.isEmpty()) { %>
                    <p>エラーが発生しました:</p>
                    <ul>
                        <% for (String error : errors) { %>
                            <li><%= error %></li>
                        <% } %>
                    </ul>
                <% } else { %>
                    <p>不明なエラーが発生しました。</p>
                <% } %>
                <button type="button" onclick="location.href='SubjectCreate'">戻る</button>
            </div>
        </div>
    </div>
    <%@ include file="footer.jsp" %>
</body>
</html>
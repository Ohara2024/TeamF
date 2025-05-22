<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Subject" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>科目登録</title>
<style>
    body {
        margin: 0;
        font-family: sans-serif;
    }
    .layout-container {
        display: flex;
        min-height: calc(100vh - 80px);
    }
    .sidebar-area {
        width: 200px;
        background-color: #e9e9e9;
        padding: 10px;
        box-sizing: border-box;
        flex-shrink: 0;
    }
    .main-content-area {
        flex-grow: 1;
        padding: 20px;
        box-sizing: border-box;
    }
    /* 追加スタイル */
    .form-row { margin-bottom: 10px; }
    .form-row label { display: inline-block; width: 100px; }
    .form-row input[type="text"] { padding: 5px; border: 1px solid #ccc; border-radius: 3px; }
    .buttons { margin-top: 20px; }
    .buttons button { padding: 8px 15px; margin-right: 10px; border: none; border-radius: 4px; cursor: pointer; }
    .buttons button[type="submit"] { background-color: #007bff; color: white; }
    .buttons button[type="submit"]:hover { background-color: #0056b3; }
    .buttons button[type="button"] { background-color: #6c757d; color: white; }
    .buttons button[type="button"]:hover { background-color: #5a6268; }
    .error-list { color: red; margin-bottom: 15px; list-style-type: disc; margin-left: 20px; }
</style>
</head>
<body>

    <%@ include file="header.jsp" %>

    <div class="layout-container">

        <div class="sidebar-area">
            <%@ include file="sidebar.jsp" %>
        </div>

        <div class="main-content-area">

            <h1>科目登録</h1>

            <%
                List<String> errors = (List<String>) request.getAttribute("errors");
                if (errors != null && !errors.isEmpty()) {
            %>
                <div style="color: red; margin-bottom: 15px;">
                    <p>エラー:</p>
                    <ul class="error-list">
                        <% for (String error : errors) { %>
                            <li><%= error %></li>
                        <% } %>
                    </ul>
                </div>
            <%
                }
            %>

            <form action="SubjectCreate" method="post">

                <div class="form-row">
                    <label>学校コード:</label>
                    <span><%= request.getAttribute("schoolCd") != null ? request.getAttribute("schoolCd") : "取得できませんでした" %></span>
                </div>

                <div class="form-row">
                    <label for="subjectCd">科目コード:</label>
                    <input type="text" id="subjectCd" name="subjectCd" value="<%= request.getAttribute("subject") != null ? ((Subject)request.getAttribute("subject")).getCd() : "" %>" maxlength="3" required>
                    <span>(半角3文字)</span>
                </div>
                <div class="form-row">
                    <label for="subjectName">科目名:</label>
                    <input type="text" id="subjectName" name="subjectName" value="<%= request.getAttribute("subject") != null ? ((Subject)request.getAttribute("subject")).getName() : "" %>" maxlength="20" required>
                    <span>(20文字以内)</span>
                </div>

                <div class="buttons">
                    <button type="submit">登録</button>
                    <button type="button" onclick="location.href='SubjectList'">キャンセル</button>
                </div>

            </form>

        </div>
    </div>

    <%@ include file="footer.jsp" %>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Get potential error message and pre-filled values from request attributes
    String error = (String) request.getAttribute("error");
    String cd = (String) request.getAttribute("cd");
    String name = (String) request.getAttribute("name");

    // Ensure values are not null for display
    if (cd == null) {
        cd = "";
    }
    if (name == null) {
        name = "";
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>科目登録</title>
</head>
<body>
    <h1>科目登録</h1>

    <%
        // Display error message if present
        if (error != null) {
    %>
        <p style="color: red;"><%= error %></p>
    <%
        }
    %>

    <form action="<%= request.getContextPath() %>/subject/create" method="post">
        <div>
            <label for="cd">科目コード:</label>
            <input type="text" id="cd" name="cd" value="<%= cd %>">
        </div>
        <div>
            <label for="name">科目名:</label>
            <input type="text" id="name" name="name" value="<%= name %>">
        </div>
        <div>
            <button type="submit">登録</button>
        </div>
    </form>

    <p><a href="subject_list.jsp">科目一覧に戻る</a></p> <%-- Assuming subject_list.jsp exists --%>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, bean.Student" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生一覧</title>
    <!-- スタイルは省略（元コードのままでOK） -->
    <style>
        /* ここに元のCSS */
    </style>
</head>
<body>
    <div class="container">
        <h1>学生一覧</h1>

        <%
            List<Student> students = (List<Student>) request.getAttribute("students");
        %>

        <table>
            <tr>
                <th>学生番号</th>
                <th>学生名</th>
                <th>入学年</th>
                <th>クラス</th>
                <th>在籍状況</th>
                <th>学校コード</th>
            </tr>
            <%
                if (students != null && !students.isEmpty()) {
                    for (Student s : students) {
            %>
            <tr>
                <td><%= s.getNo() %></td>
                <td><%= s.getName() %></td>
                <td><%= s.getEntYear() %></td>
                <td><%= s.getClassNum() %></td>
                <td class="<%= s.isAttend() ? "status-attend" : "status-absent" %>">
                    <%= s.isAttend() ? "在籍" : "退学" %>
                </td>
                <td><%= s.getSchoolCd() %></td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="6" class="error">学生データがありません。</td>
            </tr>
            <% } %>
        </table>

        <p>
            <a href="<%= request.getContextPath() %>/scoremanager/main/student_create.jsp">新規登録</a>
        </p>
    </div>
</body>
</html>

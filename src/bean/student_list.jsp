<%@ page import="bean.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>学生一覧</title>
    <style>
        table { border-collapse: collapse; width: 50%; }
        th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }
        th { background-color: #eee; }
    </style>
</head>
<body>
    <h2>学生一覧</h2>

    <%
        List<Student> studentList = (List<Student>) request.getAttribute("studentList");
        if (studentList == null || studentList.isEmpty()) {
    %>
        <p>学生データがありません。</p>
    <%
        } else {
    %>
        <table>
            <thead>
                <tr>
                    <th>ID</th><th>名前</th><th>得点</th>
                </tr>
            </thead>
            <tbody>
                <% for (Student s : studentList) { %>
                    <tr>
                        <td><%= s.getNo() %></td>
                        <td><%= s.getName() %></td>
                        <td><%= s.getScore() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    <% } %>

</body>
</html>

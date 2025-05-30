<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<!-- ← 実際のパッケージ名に変更 -->
<%@ page import="bean.Student" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生一覧</title>
    <style>
        body {
            font-family: 'Noto Sans JP', 'Comic Sans MS', Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background: linear-gradient(to bottom, #fce2e6, #e6f3ff);
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }
        .container {
            background-color: #fff;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 6px 20px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 900px;
        }
        h2 {
            text-align: center;
            color: #ff6b81;
            font-size: 28px;
            margin-bottom: 20px;
            text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.1);
        }
        table {
            border-collapse: separate;
            border-spacing: 0;
            width: 100%;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.05);
        }
        th, td {
            padding: 12px;
            text-align: center;
            font-size: 14px;
            border-bottom: 1px solid #eee;
        }
        th {
            background-color: #ffb6c1;
            color: #fff;
            font-weight: bold;
            text-transform: uppercase;
        }
        tr:nth-child(even) {
            background-color: #fff0f5;
        }
        tr:nth-child(odd) {
            background-color: #ffffff;
        }
        tr:hover {
            background-color: #e6f3ff;
            transition: background-color 0.3s;
        }
        .status-attend {
            color: #4CAF50;
            font-weight: bold;
        }
        .status-absent {
            color: #d32f2f;
            font-weight: bold;
        }
        .error {
            color: #d32f2f;
            font-size: 14px;
            text-align: center;
            margin-top: 20px;
        }
        a {
            display: inline-block;
            padding: 6px 12px;
            background-color: #81d4fa;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            font-size: 13px;
            transition: background-color 0.3s, transform 0.2s;
        }
        a:hover {
            background-color: #4fc3f7;
            transform: scale(1.05);
        }
    </style>
</head>
<body>
<div class="container">
    <h2>学生一覧</h2>
    <%
        List<Student> students = (List<Student>) request.getAttribute("students");
        if (students != null && !students.isEmpty()) {
    %>
    <table>
        <tr>
            <th>ID</th>
            <th>名前</th>
            <th>年齢</th>
            <th>学科</th>
            <th>在籍</th>
            <th>操作</th>
        </tr>
        <%
            for (Student s : students) {
        %>
        <tr>
      <td><%= s.getId() %></td>
            <td><%= s.getName() %></td>
            <td><%= s.getAge() %></td>
            <td><%= s.getDepartment() %></td>
            <td class="<%= s.isAttend() ? "status-attend" : "status-absent" %>">
                <%= s.isAttend() ? "在籍" : "退学" %>
            </td>
            <td>
                <a href="<%=request.getContextPath()%>/student/edit?id=<%= s.getId() %>">編集</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
    <% } else { %>
    <p class="error">学生データが見つかりませんでした。</p>
    <% } %>

    <p style="text-align:center; margin-top: 20px;">
        <a href="<%=request.getContextPath()%>/student/create">新規登録</a>
    </p>
</div>
</body>
</html>

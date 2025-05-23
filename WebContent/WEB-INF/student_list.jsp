<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="bean.Student" %>
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
        h1 {
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
            text-align: left;
            font-size: 14px;
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
            padding: 10px 20px;
            background-color: #81d4fa;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
            font-size: 14px;
            transition: background-color 0.3s, transform 0.2s;
        }
        a:hover {
            background-color: #4fc3f7;
            transform: scale(1.05);
        }
        @media (max-width: 600px) {
            .container {
                padding: 20px;
            }
            table {
                font-size: 12px;
            }
            th, td {
                padding: 8px;
            }
            a {
                width: 100%;
                text-align: center;
            }
        }
    </style>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@400;700&display=swap" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1>学生一覧</h1>

        <%
            List<Student> students = (List<Student>) request.getAttribute("students");
            if (students == null || students.isEmpty()) {
        %>
            <p>学生情報がありません。</p>
        <%
            } else {
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
                List<Student> students = (List<Student>) request.getAttribute("students");

               <td><%= s.getSchoolCd() %>
            </tr>
            <% } %>
        </table>
        <% } %>

        <p><a href="<%=request.getContextPath()%>/scoremanager/main/student_create.jsp">新規登録</a></p>
    </div>
</body>
</html>

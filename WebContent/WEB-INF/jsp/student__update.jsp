<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="bean.Student" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生情報編集</title>
    <style>
        form {
            width: 400px;
            margin: 40px auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 8px;
            background-color: #fefefe;
        }
        label {
            display: block;
            margin-top: 10px;
            font-weight: bold;
        }
        input[type="text"], input[type="number"] {
            width: 100%;
            padding: 6px 8px;
            margin-top: 4px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            margin-top: 20px;
            padding: 8px 15px;
            background-color: #e74c3c;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 14px;
        }
        input[type="submit"]:hover {
            background-color: #c0392b;
        }
        a {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #3498db;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
        h2 {
            text-align: center;
            color: #e74c3c;
            margin-top: 40px;
        }
    </style>
</head>
<body>
<h2>学生情報編集</h2>

<%
    Student s = (Student)request.getAttribute("student");
    if (s == null) {
%>
<p style="text-align:center; color:red;">学生情報が見つかりません。</p>
<%
    } else {
%>

<form action="<%=request.getContextPath()%>/student/update" method="post">
  <td><%= s.getId() %></td>
    <label for="name">名前:</label>
    <input type="text" id="name" name="name" value="<%= s.getName() %>" required>

    <label for="age">年齢:</label>
    <input type="number" id="age" name="age" value="<%= s.getAge() %>" required min="0">

    <label for="department">学科:</label>
    <input type="text" id="department" name="department" value="<%= s.getDepartment() %>" required>

    <input type="submit" value="更新">
</form>

<p><a href="<%=request.getContextPath()%>/student/list">一覧に戻る</a></p>

<%
    }
%>

</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // パラメータ取得
    String idStr = request.getParameter("id");
    int id = 0;
    if (idStr != null) {
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            id = 0; // デフォルト値など
        }
    }

    // TODO: ここでDAO呼び出しなどをして student情報を取得してもよい
    // 例: Student student = StudentDAO.findById(id);
    // ただしJSPで直接DBアクセスはあまり推奨されません。

%>
<!DOCTYPE html>
<html>
<head>
<title>学生情報更新</title>
</head>
<body>
<h1>学生情報編集(ID: <%= id %>)</h1>

<form action="student_update_process.jsp" method="post">
    <input type="hidden" name="id" value="<%= id %>">
    名前: <input type="text" name="name" value=""><br>
    年齢: <input type="text" name="age" value=""><br>
    <input type="submit" value="更新">
</form>

</body>
</html>

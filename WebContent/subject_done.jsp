<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録完了</title>
</head>
<body>
    <h1>登録完了</h1>
    <p>科目の登録が完了しました。</p>
    <p><a href="<%= request.getContextPath() %>/subject/list">科目一覧に戻る</a></p> <%-- Assuming subject_list.jsp exists --%>
    <p><a href="<%= request.getContextPath() %>/menu.jsp">メニューに戻る</a></p>
</body>
</html>
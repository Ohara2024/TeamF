<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head><title>成績登録完了</title></head>
<body>
<h2>成績登録完了</h2>

<%
    Boolean result = (Boolean) request.getAttribute("result");
    if (result != null && result) {
%>
    <p>成績の登録が完了しました。</p>
<%
    } else {
%>
    <p style="color:red;">登録に失敗しました。</p>
<%
    }
%>

<a href="Menu.action">メニューに戻る</a>

</body>
</html>

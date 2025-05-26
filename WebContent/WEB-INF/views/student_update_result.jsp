<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生情報更新結果</title>
    <link rel="stylesheet" href="/TeamF/css/style.css">
</head>
<body>
    <h2>学生情報更新結果</h2>

    <p>
        <%
            // リクエスト属性 "message" に更新結果メッセージがセットされている想定
            String message = (String) request.getAttribute("message");
            if (message != null) {
                out.print(message);
            } else {
                out.print("更新処理が完了しました。");
            }
        %>
    </p>

    <p><a href="<%= request.getContextPath() %>/StudentListAction.action">学生一覧へ戻る</a></p>
</body>
</html>

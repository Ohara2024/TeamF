<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%-- ヘッダーのインクルード --%>
<%@ include file="header.jsp" %>
<%
    // Servletから渡されたエラーメッセージリストを取得
    List<String> errors = (List<String>) request.getAttribute("errors");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>エラー</title>
<%-- 必要であればここにCSSを追加 --%>
</head>
<body>

    <%-- メインコンテンツのラッパー（任意） --%>
    <div style="padding: 20px;">

        <h1>エラー</h1>

        <div style="color: red;">
            <p>以下のエラーが発生しました。</p>
            <% if (errors != null && !errors.isEmpty()) { %>
                <ul>
                    <% for (String error : errors) { %>
                        <li><%= error %></li>
                    <% } %>
                </ul>
            <% } else { %>
                <p>不明なエラーが発生しました。</p>
            <% } %>
        </div>

        <%-- 画面設計書_科目登録エラー.csv に戻るボタンがあるため、実装 --%>
        <p><button type="button" onclick="history.back()">戻る</button></p>

    </div><%-- /メインコンテンツラッパー --%>

    <%-- フッターのインクルード --%>
    <%@ include file="footer.jsp" %>

</body>
</html>
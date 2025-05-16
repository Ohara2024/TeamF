<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Subject" %>
<%@ page import="java.util.List" %>
<%-- ヘッダーのインクルード --%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>科目登録</title>
<%-- 必要であればここにCSSを追加 --%>
</head>
<body>

    <%-- メインコンテンツのラッパー（任意） --%>
    <div style="padding: 20px;">

        <h1>科目登録</h1>

        <%
            // エラーメッセージ表示（エラー画面からフォワードされてきた場合）
            List<String> errors = (List<String>) request.getAttribute("errors");
            if (errors != null && !errors.isEmpty()) {
        %>
            <div style="color: red;">
                <p>エラー:</p>
                <ul>
                    <% for (String error : errors) { %>
                        <li><%= error %></li>
                    <% } %>
                </ul>
            </div>
        <%
            }
        %>

        <form action="SubjectCreate" method="post"> <%-- サーブレットのマッピング名に合わせる --%>

            <%-- 画面設計書によると、学校コードはログイン情報から取得し、表示のみ --%>
            <p>学校コード: <%= request.getAttribute("schoolCd") != null ? request.getAttribute("schoolCd") : "取得できませんでした" %></p>

            <p>科目コード:
                <input type="text" name="subjectCd" value="<%= request.getAttribute("subject") != null ? ((Subject)request.getAttribute("subject")).getCd() : "" %>" maxlength="3" required>
                 (半角3文字)
            </p>
            <p>科目名:
                <input type="text" name="subjectName" value="<%= request.getAttribute("subject") != null ? ((Subject)request.getAttribute("subject")).getName() : "" %>" maxlength="20" required>
                 (20文字以内)
            </p>

            <button type="submit">登録</button>
            <%-- キャンセルボタンは科目管理一覧画面へのリンク --%>
            <button type="button" onclick="location.href='SubjectList'">キャンセル</button> <%-- 科目一覧サーブレット/JSPのパスに修正 --%>

        </form>

    </div><%-- /メインコンテンツラッパー --%>

    <%-- フッターのインクルード --%>
    <%@ include file="footer.jsp" %>

</body>
</html>
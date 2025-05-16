<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- ヘッダーのインクルード --%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録完了</title>
<%-- 必要であればここにCSSを追加 --%>
</head>
<body>

    <%-- メインコンテンツのラッパー（任意） --%>
    <div style="padding: 20px;">

        <h1>科目登録完了</h1>

        <p>科目の登録が完了しました。</p>

        <%-- 画面設計書_科目登録完了.csv に戻るボタン（リンク）の指示がないため、一覧画面へ遷移するリンクを設置 --%>
        <p><a href="SubjectList">科目管理一覧へ戻る</a></p> <%-- 科目一覧サーブレット/JSPのパスに修正 --%>

    </div><%-- /メインコンテンツラッパー --%>

    <%-- フッターのインクルード --%>
    <%@ include file="footer.jsp" %>

</body>
</html>
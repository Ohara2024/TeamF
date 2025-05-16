<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // SubjectCreateExecuteActionからフォワードされた場合に、
    // リクエスト属性に設定されたエラーメッセージや入力値を取得します。
    String error = (String) request.getAttribute("error");
    String cd = (String) request.getAttribute("cd");
    String name = (String) request.getAttribute("name");

    if (cd == null) {
        cd = "";
    }
    if (name == null) {
        name = "";
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>科目登録 - 得点管理システム</title> <%-- タイトルを修正 --%>
<style>
    /* シンプルなレイアウトのためのCSS */
    body { margin: 0; font-family: sans-serif; display: flex; flex-direction: column; min-height: 100vh; }
    .content-area { display: flex; flex: 1; } /* ヘッダーとフッターの間を flexbox でレイアウト */
    .main-content { flex: 1; padding: 20px; } /* メインコンテンツが領域を埋めるように */
</style>
</head>
<body>

    <%-- ヘッダーのインクルード --%>
    <%@ include file="/header.jsp" %>

    <div class="content-area">
        <%-- メニューのインクルード --%>
        <%@ include file="/menu.jsp" %>

        <div class="main-content">
            <h2>科目情報登録</h2> <%-- 画面タイトル --%>

            <%-- エラーメッセージがあれば表示 --%>
            <% if (error != null) { %>
                <p style="color: red;"><%= error %></p>
            <% } %>

            <%-- 科目登録フォーム --%>
            <form action="<%= request.getContextPath() %>/subject/create" method="post">
                <div style="margin-bottom: 10px;">
                    <label for="cd" style="display: inline-block; width: 100px;">科目コード:</label> <%-- ラベル幅を調整 --%>
                    <input type="text" id="cd" name="cd" value="<%= cd %>" style="padding: 5px;">
                </div>
                <div style="margin-bottom: 10px;">
                    <label for="name" style="display: inline-block; width: 100px;">科目名:</label> <%-- ラベル幅を調整 --%>
                    <input type="text" id="name" name="name" value="<%= name %>" style="padding: 5px;">
                </div>
                <div style="margin-top: 15px;">
                    <button type="submit" style="padding: 8px 15px;">登録</button>
                    <%-- 「戻る」リンク。前の画面（科目一覧など）に戻る想定 --%>
                    <%-- リンク先は必要に応じてFrontController経由などに修正してください --%>
                    <a href="<%= request.getContextPath() %>/subject/list" style="margin-left: 15px;">戻る</a>
                </div>
            </form>
        </div>
    </div>

    <%-- フッターのインクルード --%>
    <%@ include file="/footer.jsp" %>

</body>
</html>
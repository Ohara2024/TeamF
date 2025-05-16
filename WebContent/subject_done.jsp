<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録完了 - 得点管理システム</title>
<style>
    /* シンプルなレイアウトのためのCSS */
    body { margin: 0; font-family: sans-serif; display: flex; flex-direction: column; min-height: 100vh; }
    .content-area { display: flex; flex: 1; } /* ヘッダーとフッターの間を flexbox でレイアウト */
    .main-content { flex: 1; padding: 20px; } /* メインコンテンツが領域を埋めるように */
</style>
</head>
<body>

    <%-- ヘッダーのインクルードディレクティブ (ファイル名は header.jsp に変更) --%>
    <%@ include file="/header.jsp" %>

    <div class="content-area">
        <%-- メニューのインクルード (ファイル名は _menu.jspf のままを想定) --%>
        <%@ include file="/menu.jsp" %>

        <div class="main-content">
            <h2>登録完了</h2>
            <p>科目の登録が完了しました。</p>
            <%-- 科目一覧画面へのリンク（仮） --%>
            <p><a href="<%= request.getContextPath() %>/subject/list">科目一覧に戻る</a></p>
            <%-- メニューに戻るリンク --%>
             <p><a href="<%= request.getContextPath() %>/menu.jsp">メニューに戻る</a></p>
        </div>
    </div>

    <%-- フッターのインクルードディレクティブ (ファイル名は footer.jsp に変更) --%>
    <%@ include file="/footer.jsp" %>

</body>
</html>
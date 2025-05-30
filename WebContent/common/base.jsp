<%-- 共通テンプレート base.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  このJSPファイルは他のJSPから<c:import>で読み込まれ、
  ページ全体の共通レイアウト（ヘッダー／ナビゲーション／フッター）を提供します。
  動的にタイトル、スクリプト、メインコンテンツを挿入できる仕組みになっています。
--%>

<!DOCTYPE html>
<html lang="ja">
<head>
  <%-- 文字コードとレスポンシブ対応のメタ情報 --%>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <%-- Bootstrap 5 のCSSをCDN経由で読み込み --%>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
        crossorigin="anonymous">

  <%-- ページタイトルは呼び出し元JSPから渡されるparam.titleを使用 --%>
  <title>${param.title}</title>

  <%-- jQueryの読み込み（動的なUIやAjax処理用） --%>
  <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

  <%-- 必要に応じて呼び出し元から渡されるJSを挿入 --%>
  ${param.scripts}
</head>

<body>
  <div id="wrapper" class="container">

    <%-- ▼ ヘッダーエリア：共通ヘッダJSPを読み込み --%>
    <header class="d-flex flex-wrap justify-content-center py-3 px-5 mb-4 border-bottom border-2 bg-primary bg-opacity-10 bg-gradient">
      <c:import url="/common/header.jsp" />
    </header>

    <%-- ▼ メインの表示領域（ナビゲーション＋コンテンツ） --%>
    <div class="row justify-content-center">

      <%-- ユーザーがログインしているかで表示内容を分岐 --%>
      <c:choose>

        <%-- ● ログイン済みの場合：ナビゲーション＋メインコンテンツ表示 --%>
        <c:when test="${user.isAuthenticated()}">
          <%-- 左側にナビゲーションメニュー --%>
          <nav class="col-3" style="height:40rem;">
            <c:import url="/common/navigation.jsp" />
          </nav>

          <%-- 右側に呼び出し元ページのメインコンテンツ --%>
          <main class="col-9 border-start">
            ${param.content}
          </main>
        </c:when>

        <%-- ● 未ログインの場合：ナビゲーションなしでコンテンツだけ表示 --%>
        <c:otherwise>
          <main class="col-8">
            ${param.content}
          </main>
        </c:otherwise>

      </c:choose>
    </div>

    <%-- ▼ フッターエリア：共通フッタJSPを読み込み --%>
    <footer class="py-2 my-4 bg-dark bg-opacity-10 border-top border-3 align-bottom">
      <c:import url="/common/footer.jsp" />
    </footer>

  </div>
</body>
</html>

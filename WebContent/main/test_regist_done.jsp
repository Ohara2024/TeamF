<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  このJSPファイルは、成績登録が完了した際に表示される完了メッセージ画面です。
  - contentType：レスポンスのMIMEタイプ（HTML＋UTF-8文字コード）を指定。
  - JSTLのコアタグライブラリを使用するための宣言。
--%>

<%-- 共通テンプレート「base.jsp」を読み込み、その中にコンテンツを挿入する形を取っている --%>
<c:import url="/common/base.jsp">

  <%-- ページタイトルとして渡す値。base.jspで<title>タグなどに使われる --%>
  <c:param name="title">
    得点管理システム
  </c:param>

  <%-- 実際にこのページで表示するメインコンテンツ --%>
  <c:param name="content">

    <%-- メインの表示領域（全体を囲む） --%>
    <div id="wrap_box">

      <%-- 画面見出し --%>
      <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2">成績管理</h2>

      <div id="wrap_box">

        <%-- 成績登録完了メッセージ（中央揃え＋背景色あり） --%>
        <p class="text-center" style="background-color:#8cc3a9">登録が完了しました</p>

        <br><br><br> <%-- スペース調整のための空行。CSSによる余白管理が推奨されるが簡易的に使用 --%>

        <%-- 成績登録画面に戻るためのリンク --%>
        <a href="TestRegist.action">戻る</a>

        <%-- 空白スペース用のあいまいなスペーサー。CSSで対応すべきところを文字で空白作成 --%>
        <a>　　　　　</a>

        <%-- 成績参照（検索）画面へのリンク --%>
        <a href="TestList.action">成績参照</a>
      </div>
    </div>

  </c:param>
</c:import>

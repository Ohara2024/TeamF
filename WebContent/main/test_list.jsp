<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- JSPページの基本設定。JSTLタグライブラリを使うための宣言も含まれる --%>

<%-- 共通のレイアウト（base.jsp）を読み込む。import時にパラメータでタイトルやスクリプト、コンテンツを渡す --%>
<c:import url="/common/base.jsp">

  <%-- タイトル部分 --%>
  <c:param name="title">
    得点管理システム
  </c:param>

  <%-- 必要なスクリプトがあればここに挿入できる（今回は空） --%>
  <c:param name="scripts"></c:param>

  <%-- メインのページコンテンツ --%>
  <c:param name="content">

    <section class="me=4">
      <%-- ページの見出し --%>
      <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>

      <%-- 検索フォーム全体を囲むボックス --%>
      <div class="border border-bottom mx-3 mb-2 px-3 py-2 align-items-center rounded" id="filter">

        <%-- ■ 科目情報で検索するフォーム ■ --%>
        <form action="TestListSubjectExecute.action" method="get">
          <div class="row">

            <%-- セクション見出し --%>
            <div class="col-2 mt-4" style="text-align:center">
              <p>科目情報</p>
            </div>

            <%-- ▼ 入学年度の選択 ▼ --%>
            <div class="col-2">
              <label class="form-label" for="subject-f1-select">入学年度</label>
              <select class="form-select" id="subject-f1-select" name="f1">
                <option value="0">--------</option>
                <%-- entYearSetリストから年度を取得し、選択肢を生成 --%>
                <c:forEach var="year" items="${entYearSet}">
                  <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
                </c:forEach>
              </select>
            </div>

            <%-- ▼ クラス番号の選択 ▼ --%>
            <div class="col-2">
              <label class="form-label" for="student-f2-select">クラス</label>
              <select class="form-select" id="student-f2-select" name="f2">
                <option value="0">--------</option>
                <%-- cNumlistリストからクラス番号を取得し、選択肢を生成 --%>
                <c:forEach var="num" items="${cNumlist}">
                  <option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
                </c:forEach>
              </select>
            </div>

            <%-- ▼ 科目の選択 ▼ --%>
            <div class="col-4">
              <label class="form-label" for="student-f2-select">科目</label>
              <select class="form-select" id="student-f2-select" name="f3">
                <option value="0">--------</option>
                <%-- listリストから科目を取得し、選択肢を生成（subject.cdが値、subject.nameが表示名） --%>
                <c:forEach var="subject" items="${list}">
                  <option value="${subject.cd}" <c:if test="${subject.cd == f3}">selected</c:if>>${subject.name}</option>
                </c:forEach>
              </select>
            </div>

            <%-- 検索ボタン --%>
            <div class="col-2 mt-3 text-center">
              <%-- フラグ用の隠しパラメータ（sj: subject） --%>
              <input type="hidden" name="f" value="sj">
              <button class="btn btn-secondary" id="subject-button">検索</button>
            </div>

            <%-- 入力エラー表示（例：必須項目が未入力など） --%>
            <div class="mt-2 text-warning">${errors.get("1")}</div>
          </div>
        </form>

        <%-- 区切り線 --%>
        <div class="row border-bottom mx-0 mb-3 py-2 align-items-center"></div>

        <%-- ■ 学生番号で検索するフォーム ■ --%>
        <form action="TestListStudentExecute.action" method="get">
          <div class="row">

            <%-- セクション見出し --%>
            <div class="col-2 mt-3" style="text-align:center">
              <p>学生情報</p>
            </div>

            <%-- ▼ 学生番号入力欄 ▼ --%>
            <div class="col-4">
              <label class="form-label" for="student-f4-select">学生番号</label>
              <input class="form-control" type="text" id="student-f4-select"
                     name="f4" value="${f4}" required maxlength="10"
                     placeholder="学生番号を入力してください"/>
            </div>

            <%-- 検索ボタン --%>
            <div class="col-2 mt-3 text-center">
              <%-- フラグ用の隠しパラメータ（st: student） --%>
              <input type="hidden" name="f" value="st">
              <button class="btn btn-secondary" id="student-button">検索</button>
            </div>
          </div>
        </form>
      </div>

      <%-- 検索方法の案内メッセージ --%>
      <p>
        <font color="5accf2">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</font>
      </p>
    </section>

  </c:param>
</c:import>

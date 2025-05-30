<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/common/base.jsp">

  <c:param name="title">
    得点管理システム
  </c:param>

  <%-- スクリプトの挿入エリア（今回は空） --%>
  <c:param name="scripts"></c:param>

  <%-- メインのコンテンツエリア --%>
  <c:param name="content">
    <section>

      <%-- ページ見出し --%>
      <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>

      <%-- フィルター（検索条件）フォーム --%>
      <form method="get">
        <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">

          <%-- ▼ 入学年度の選択肢 ▼ --%>
          <div class="col-2">
            <label class="form-label" for="student-f1-select">入学年度</label>
            <select class="form-select" id="student-f1-select" name="f1">
              <option value="0">--------</option>
              <c:forEach var="year" items="${entYearList}">
                <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
              </c:forEach>
            </select>
          </div>

          <%-- ▼ クラスの選択肢 ▼ --%>
          <div class="col-2">
            <label class="form-label" for="student-f2-select">クラス</label>
            <select class="form-select" id="student-f2-select" name="f2">
              <option value="0">--------</option>
              <c:forEach var="num" items="${cNumList}">
                <option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
              </c:forEach>
            </select>
          </div>

          <%-- ▼ 科目の選択肢 ▼ --%>
          <div class="col-4">
            <label class="form-label" for="student-f2-select">科目</label>
            <select class="form-select" id="student-f2-select" name="f3">
              <option value="0">--------</option>
              <c:forEach var="subject" items="${list}">
                <option value="${subject.cd}" <c:if test="${subject.cd == f3}">selected</c:if>>${subject.name}</option>
              </c:forEach>
            </select>
          </div>

          <%-- ▼ 回数の選択肢（1回目のテスト、2回目など） ▼ --%>
          <div class="col-2">
            <label class="form-label" for="student-f2-select">回数</label>
            <select class="form-select" id="student-f2-select" name="f4">
              <option value="0">--------</option>
              <c:forEach var="num" items="${countList}">
                <option value="${num}" <c:if test="${num == f4}">selected</c:if>>${num}</option>
              </c:forEach>
            </select>
          </div>

          <%-- 検索ボタン --%>
          <div class="col-2 text-center">
            <button class="btn btn-secondary" id="filter-button">検索</button>
          </div>

          <%-- 入力エラー表示（例：項目未選択など） --%>
          <div class="mt-2 text-warning">${errors.get("a")}</div>
        </div>
      </form>

      <%-- 成績登録用フォーム（検索結果表示後、得点入力 → 登録用） --%>
      <form action="TestRegistExecute.action" method="get">

        <%-- 検索結果が存在する場合にテーブルを表示 --%>
        <c:choose>
          <c:when test="${testlist.size() > 0}">

            <%-- 検索された科目名と回数を表示 --%>
            <div>科目：${subject_name}（${f4}回）</div>

            <%-- ▼ 成績一覧表 ▼ --%>
            <table class="table table-hover">
              <tr>
                <th>入学年度</th>
                <th>クラス</th>
                <th>学生番号</th>
                <th>氏名</th>
                <th>点数</th>
              </tr>

              <%-- 受講生1人ずつ行を作成 --%>
              <c:forEach var="test" items="${testlist}" varStatus="st">
                <tr>
                  <td>${test.student.entYear}</td>
                  <td>${test.classNum}</td>
                  <td>${test.student.no}</td>
                  <td>${test.student.name}</td>

                  <%-- 点数入力欄。すでに得点がある場合はvalue属性に表示 --%>
                  <td>
                    <input type="text" name="point_${test.student.no}"
                      <c:if test="${test.no != 0}">value="${test.point}"</c:if> value="">
                    <div class="mt-2 text-warning">${errors.get(st.count)}</div>
                  </td>
                </tr>

                <%-- 学生ごとのhiddenフィールド（識別用） --%>
                <input type="hidden" name="regist" value="${test.student.no}">
              </c:forEach>
            </table>

            <%-- 科目コードと回数をhiddenで送信（保存時に必要） --%>
            <input type="hidden" name="count" value="${f4}">
            <input type="hidden" name="subject" value="${f3}">

            <%-- 成績を登録して終了するボタン --%>
            <div class="col-2 text-center">
              <button class="btn btn-secondary" id="filter-button">登録して終了</button>
            </div>

          </c:when>
        </c:choose>
      </form>
    </section>
  </c:param>
</c:import>
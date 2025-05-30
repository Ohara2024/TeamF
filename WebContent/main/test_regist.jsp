<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="UTF-8">
  <title>得点管理システム</title>
  <!-- 必要ならCSSやJSをここに追加 -->
</head>
<body>
  <section>
    <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>

    <!-- フィルター（検索条件）フォーム -->
    <form method="get">
      <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">

        <!-- 入学年度の選択肢 -->
        <div class="col-2">
          <label class="form-label" for="student-f1-select">入学年度</label>
          <select class="form-select" id="student-f1-select" name="f1">
            <option value="0">--------</option>
            <c:forEach var="year" items="${entYearList}">
              <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
            </c:forEach>
          </select>
        </div>

        <!-- クラスの選択肢 -->
        <div class="col-2">
          <label class="form-label" for="student-f2-select">クラス</label>
          <select class="form-select" id="student-f2-select" name="f2">
            <option value="0">--------</option>
            <c:forEach var="num" items="${cNumList}">
              <option value="${num}" <c:if test="${num == f2}">selected</c:if>>${num}</option>
            </c:forEach>
          </select>
        </div>

        <!-- 科目の選択肢 -->
        <div class="col-4">
          <label class="form-label" for="student-f3-select">科目</label>
          <select class="form-select" id="student-f3-select" name="f3">
            <option value="0">--------</option>
            <c:forEach var="subject" items="${list}">
              <option value="${subject.cd}" <c:if test="${subject.cd == f3}">selected</c:if>>${subject.name}</option>
            </c:forEach>
          </select>
        </div>

        <!-- 回数の選択肢 -->
        <div class="col-2">
          <label class="form-label" for="student-f4-select">回数</label>
          <select class="form-select" id="student-f4-select" name="f4">
            <option value="0">--------</option>
            <c:forEach var="num" items="${countList}">
              <option value="${num}" <c:if test="${num == f4}">selected</c:if>>${num}</option>
            </c:forEach>
          </select>
        </div>

        <!-- 検索ボタン -->
        <div class="col-2 text-center">
          <button class="btn btn-secondary" id="filter-button">検索</button>
        </div>
        <!-- 入力エラー表示 -->
        <div class="mt-2 text-warning">${errors.get("a")}</div>
      </div>
    </form>

    <!-- 成績登録用フォーム -->
    <form action="TestRegistExecute.action" method="get">
      <c:choose>
        <c:when test="${testlist.size() > 0}">
          <div>科目：${subject_name}（${f4}回）</div>
          <table class="table table-hover">
            <tr>
              <th>入学年度</th>
              <th>クラス</th>
              <th>学生番号</th>
              <th>氏名</th>
              <th>点数</th>
            </tr>
            <c:forEach var="test" items="${testlist}" varStatus="st">
              <tr>
                <td>${test.student.entYear}</td>
                <td>${test.classNum}</td>
                <td>${test.student.no}</td>
                <td>${test.student.name}</td>
                <td>
                  <input type="text" name="point_${test.student.no}"
                    <c:if test="${test.no != 0}">value="${test.point}"</c:if> value="">
                  <div class="mt-2 text-warning">${errors.get(st.count)}</div>
                </td>
              </tr>
              <input type="hidden" name="regist" value="${test.student.no}">
            </c:forEach>
          </table>
          <input type="hidden" name="count" value="${f4}">
          <input type="hidden" name="subject" value="${f3}">
          <div class="col-2 text-center">
            <button class="btn btn-secondary" id="filter-button">登録して終了</button>
          </div>
        </c:when>
      </c:choose>
    </form>
  </section>
</body>
</html>
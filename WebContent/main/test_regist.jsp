<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<body>
    <div class="main-wrapper">
        <div class="menu-bar">
            <!-- ...メニュー省略... -->
        </div>
        <div class="content-area">
            <div class="header">
                <h1>成績登録・変更</h1>
                <span class="user-info">
                    <c:if test="${not empty sessionScope.teacher}">${sessionScope.teacher.name}さん</c:if>
                    <c:if test="${empty sessionScope.teacher}">ゲストさん</c:if>
                    <a href="${pageContext.request.contextPath}/login/logout" class="logout-link">ログアウト</a>
                </span>
            </div>

            <c:if test="${not empty requestScope.errorMessage}">
                <p class="error-message"><c:out value="${requestScope.errorMessage}"/></p>
            </c:if>
            <c:if test="${not empty requestScope.infoMessage && empty requestScope.errorMessage}">
                <p class="info-message"><c:out value="${requestScope.infoMessage}"/></p>
            </c:if>

            <div class="search-section">
                <h2>検索条件</h2>
                <form id="searchForm" action="${pageContext.request.contextPath}/main/TestRegist.action" method="post">
                    <input type="hidden" name="action" value="search_students_for_score">
                    <div class="form-group">
                        <label for="fEntYear">入学年度:</label>
                        <select id="fEntYear" name="fEntYear">
                            <option value="">選択してください</option>
                            <c:forEach var="year" items="${requestScope.entYearSet}">
                                <option value="${year}" <c:if test="${year == requestScope.fEntYear}">selected</c:if>>${year}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="fClassNum">クラス:</label>
                        <select id="fClassNum" name="fClassNum">
                            <option value="">選択してください</option>
                            <c:forEach var="classVal" items="${requestScope.classNumSet}">
                                <option value="${classVal}" <c:if test="${classVal == requestScope.fClassNum}">selected</c:if>>${classVal}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="fSubjectCd">科目:</label>
                        <select id="fSubjectCd" name="fSubjectCd">
                            <option value="">選択してください</option>
                            <c:forEach var="subject" items="${requestScope.subjectList}">
                                <option value="${subject.cd}" <c:if test="${subject.cd == requestScope.fSubjectCd}">selected</c:if>>${subject.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="fTestNo">回数:</label>
                        <select id="fTestNo" name="fTestNo">
                            <option value="">選択してください</option>
                            <option value="1" <c:if test="${'1' == requestScope.fTestNo}">selected</c:if>>1</option>
                            <option value="2" <c:if test="${'2' == requestScope.fTestNo}">selected</c:if>>2</option>
                        </select>
                    </div>
                    <div class="button-group">
                        <button type="submit">検索</button>
                    </div>
                </form>
            </div>

            <c:if test="${not empty requestScope.students}">
                <div class="results-section">
                    <h2>成績入力:
                        <c:out value="${requestScope.searchedSubject.name}"/>
                        (第 <c:out value="${requestScope.searchedTestNo}"/> 回)
                    </h2>
                    <form id="scoreForm" action="${pageContext.request.contextPath}/main/TestRegist.action" method="post">
                        <input type="hidden" name="action" value="register_scores">
                        <input type="hidden" name="hidden_fEntYear" value="${requestScope.fEntYear}">
                        <input type="hidden" name="hidden_fClassNum" value="${requestScope.fClassNum}">
                        <input type="hidden" name="hidden_fSubjectCd" value="${requestScope.fSubjectCd}">
                        <input type="hidden" name="hidden_fTestNo" value="${requestScope.searchedTestNo}">

                        <table class="table">
                            <thead>
                                <tr>
                                    <th>学生番号</th>
                                    <th>氏名</th>
                                    <th>点数 (0-100)</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="student" items="${requestScope.students}">
                                    <tr>
                                        <td><c:out value="${student.no}"/></td>
                                        <td><c:out value="${student.name}"/></td>
                                        <td>
                                            <input type="number" name="point_${student.no}" min="0" max="100"
                                                   value="${requestScope.pointsMap[student.no]}" placeholder="点数">
                                            <input type="hidden" name="studentNos" value="${student.no}">
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </form>
                </div>
            </c:if>
            <c:if test="${empty requestScope.students && not empty requestScope.fEntYear && not empty requestScope.fClassNum && not empty requestScope.fSubjectCd && not empty requestScope.fTestNo && empty requestScope.errorMessage && empty requestScope.infoMessage}">
                 <p class="info-message">指定された条件に合致する学生情報は見つかりませんでした。検索条件を変更して再度お試しください。</p>
            </c:if>
        </div>
    </div>

    <c:if test="${not empty requestScope.students}">
        <div class="fixed-action-button-container">
            <button type="button" onclick="document.getElementById('scoreForm').submit();">登録/更新</button>
        </div>
    </c:if>

    <div class="footer">
        © <%= java.time.Year.now().getValue() %> TIC<br>
        大原学園
    </div>
</body>
</html>
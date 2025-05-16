<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>科目別成績一覧</title>
</head>
<body>
    <h2>科目別成績一覧</h2>

    <c:if test="${not empty errorMessage}">
        <p style="color:red;">${errorMessage}</p>
    </c:if>

    <c:if test="${not empty testList}">
        <table border="1">
            <thead>
                <tr>
                    <th>入学年度</th>
                    <th>学生番号</th>
                    <th>学生氏名</th>
                    <th>クラス番号</th>
                    <c:forEach begin="1" end="${testList[0].points.size()}" var="i">
                        <th>第${i}回</th>
                    </c:forEach>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="test" items="${testList}">
                    <tr>
                        <td>${test.entYear}</td>
                        <td>${test.studentNo}</td>
                        <td>${test.studentName}</td>
                        <td>${test.classNum}</td>
                        <c:forEach begin="1" end="${test.points.size()}" var="i">
                            <td>${test.getPoint(i)}</td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <a href="index.html">戻る</a>  </body>
</html>
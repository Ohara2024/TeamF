<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>学生別成績一覧</title>
</head>
<body>
    <h2>学生別成績一覧</h2>

    <c:if test="${not empty errorMessage}">
        <p style="color:red;">${errorMessage}</p>
    </c:if>

    <c:if test="${not empty student}">
        <h3>学生情報</h3>
        <p>学生番号: ${student.no}</p>
        <p>氏名: ${student.name}</p>
        <p>学校名: ${student.school.name}</p>

        <h3>成績一覧</h3>
        <table border="1">
            <thead>
                <tr>
                    <th>科目名</th>
                    <th>回数</th>
                    <th>得点</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="test" items="${testList}">
                    <tr>
                        <td>${test.subject.name}</td>
                        <td>${test.no}</td>
                        <td>${test.point}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

    <a href="index.html">戻る</a>
</body>
</html>
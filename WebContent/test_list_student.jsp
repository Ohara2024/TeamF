<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!DOCTYPE html>
<html>
<head>
    <title>学生別成績一覧</title>
</head>
<body>
    <h1>学生別成績一覧</h1>

    <logic:messagesPresent key="errorMessage">
        <div style="color:red;">
            <html:errors key="errorMessage"/>
        </div>
    </logic:messagesPresent>

    <html:form action="/TestListStudent">
        学生番号: <html:text property="studentId"/>
        <html:submit value="検索"/>
    </html:form>

    <logic:present name="student">
        <h2><%= ((bean.Student)request.getAttribute("student")).getStudentName() %> さんの成績</h2>
        <p>学年: <%= ((bean.Student)request.getAttribute("student")).getClassNum().getSchool().getSchoolId() %>年
           クラス: <%= ((bean.Student)request.getAttribute("student")).getClassNum().getClassName() %></p>

        <logic:present name="testList">
            <table border="1">
                <thead>
                    <tr>
                        <th>科目名</th>
                        <th>得点</th>
                    </tr>
                </thead>
                <tbody>
                    <logic:iterate id="test" name="testList">
                        <tr>
                            <td><%= ((bean.Test)test).getSubject().getSubjectName() %></td>
                            <td><%= ((bean.Test)test).getScore() %></td>
                        </tr>
                    </logic:iterate>
                </tbody>
            </table>
        </logic:present>
        <logic:notPresent name="testList">
            <p>成績データはありません。</p>
        </logic:notPresent>
    </logic:present>
</body>
</html>
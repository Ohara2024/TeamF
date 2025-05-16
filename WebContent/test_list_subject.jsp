<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!DOCTYPE html>
<html>
<head>
    <title>科目別成績一覧</title>
</head>
<body>
    <h1>科目別成績一覧</h1>

    <logic:messagesPresent key="errorMessage">
        <div style="color:red;">
            <html:errors key="errorMessage"/>
        </div>
    </logic:messagesPresent>

    <html:form action="/TestListSubject">
        学年:
        <html:select property="schoolId">
            <html:option value="">選択してください</html:option>
            <%-- TODO: 学校リストをDBから取得して表示 --%>
            <html:option value="1">1</html:option>
            <html:option value="2">2</html:option>
            <html:option value="3">3</html:option>
        </html:select>
        クラス:
        <html:select property="classId">
            <html:option value="">選択してください</html:option>
            <%-- TODO: クラスリストをDBから取得して表示 --%>
            <html:option value="1">A</html:option>
            <html:option value="2">B</html:option>
        </html:select>
        科目:
        <html:select property="subjectId">
            <html:option value="">選択してください</html:option>
            <%-- TODO: 科目リストをDBから取得して表示 --%>
            <html:option value="1">国語</html:option>
            <html:option value="2">数学</html:option>
            <html:option value="3">英語</html:option>
        </html:select>
        <html:submit value="検索"/>
    </html:form>

    <logic:present name="subject">
        <h2><%= ((bean.Subject)request.getAttribute("subject")).getSubjectName() %> の成績一覧</h2>

        <logic:present name="testList">
            <table border="1">
                <thead>
                    <tr>
                        <th>学生名</th>
                        <th>クラス</th>
                        <th>得点</th>
                    </tr>
                </thead>
                <tbody>
                    <logic:iterate id="test" name="testList">
                        <tr>
                            <td><%= ((bean.Test)test).getStudent().getStudentName() %></td>
                            <td><%= ((bean.Test)test).getStudent().getClassNum().getClassName() %></td>
                            <td><%= ((bean.Test)test).getScore() %></td>
                        </tr>
                    </logic:iterate>
                </tbody>
            </table>
        </logic:present>
        <logic:notPresent name="testList">
            <p>該当する成績データはありません。</p>
        </logic:notPresent>
    </logic:present>
</body>
</html>
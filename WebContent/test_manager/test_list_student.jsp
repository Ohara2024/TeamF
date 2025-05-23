<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="/main/header.jsp"></jsp:include>
<jsp:include page="/main/side_menu.jsp"></jsp:include>

<form action="${pageContext.request.contextPath}/testmanager/studentexe" method="post" style="margin-bottom: 15px;">
    <fieldset style="border: none; padding: 10px 0;">
        <legend style="font-weight: bold; margin-bottom: 5px;">学生情報</legend>

        <label for="studentNo" style="margin-right: 10px;">学生番号</label>
        <input type="text" name="studentNo" id="studentNo" placeholder="学生番号を入力してください" style="width: 200px; padding: 5px;" />

        <button type="submit" style="margin-left: 10px; padding: 5px 15px;">検索</button>
    </fieldset>
</form>

<p style="color: #007bff; margin-top: 10px;">
    学生番号を入力して検索ボタンをクリックしてください。
</p>

<jsp:include page="/main/footer.jsp"></jsp:include>
<%-- 一応使わない --%>
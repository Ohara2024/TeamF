<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div style="width: 180px; padding: 10px; border-right: 1px solid #ccc;">
    <div><strong>メニュー</strong></div>
    <ul style="list-style: none; padding: 0;">
        <li><a href="<%= request.getContextPath() %>/student/list">学生管理</a></li> <%-- 仮のリンク先 --%>
        <li><strong>成績管理</strong></li>
        <ul style="list-style: none; padding-left: 15px;">
            <li><a href="<%= request.getContextPath() %>/score/register">成績登録</a></li> <%-- 仮のリンク先 --%>
            <li><a href="<%= request.getContextPath() %>/score/reference">成績参照</a></li> <%-- 仮のリンク先 --%>
        </ul>
        <li><strong>登録</strong></li> <%-- イメージでは「登録」が独立していますが、科目管理の子要素のような位置づけにします --%>
         <ul style="list-style: none; padding-left: 15px;">
             <li><a href="<%= request.getContextPath() %>/subject/create">科目登録</a></li> <%-- 科目登録画面へのリンク --%>
         </ul>
        <li><a href="<%= request.getContextPath() %>/subject/list">科目管理</a></li> <%-- 科目一覧画面へのリンク（仮） --%>
    </ul>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    bean.Teacher loggedInTeacher = (bean.Teacher) session.getAttribute("user");
    String teacherName = "";
    if (loggedInTeacher != null) {
        teacherName = loggedInTeacher.getName();
    }
%>
<div style="background-color: #f0f0f0; padding: 10px; border-bottom: 1px solid #ccc; display: flex; justify-content: space-between; align-items: center;">
    <div style="font-size: 1.5em; font-weight: bold;">得点管理システム</div>
    <div>
        <%= teacherName %> &nbsp;&nbsp; <a href="<%= request.getContextPath() %>/logout" style="text-decoration: none; color: #007bff;">ログアウト</a>
    </div>
</div>
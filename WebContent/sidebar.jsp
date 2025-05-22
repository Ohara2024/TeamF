<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div style="padding: 10px;">
    <h4>メニュー</h4>
    <ul style="list-style: none; padding: 0;">
        <li style="margin-bottom: 10px;">
            <a href="<%= request.getContextPath() %>/MainMenu" style="text-decoration: none; color: #007bff;">メインメニュー</a>
        </li>
        <li style="margin-bottom: 10px;">
            <a href="<%= request.getContextPath() %>/StudentList" style="text-decoration: none; color: #007bff;">学生管理</a>
        </li>
        <li style="margin-bottom: 10px;">
            <a href="<%= request.getContextPath() %>/SubjectList" style="text-decoration: none; color: #007bff;">科目管理</a>
        </li>
        <li style="margin-bottom: 10px;">
            <a href="<%= request.getContextPath() %>/ScoreManagement" style="text-decoration: none; color: #007bff;">成績管理</a>
        </li>
    </ul>
</div>
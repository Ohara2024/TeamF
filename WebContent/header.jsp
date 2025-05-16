<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    // セッションからログイン中の教員情報を取得
    bean.Teacher loggedInTeacher = (bean.Teacher) session.getAttribute("user");
    String teacherName = "";
    if (loggedInTeacher != null) {
        // ログイン中の場合は教員名を取得（Teacher Bean に getName() がある想定）
        teacherName = loggedInTeacher.getName();
    } else {
        // ログインしていない場合でも表示用の名前を設定（例: 空文字）
        teacherName = ""; // あるいは "ゲスト" など表示したい名前
    }
%>
<%-- ヘッダー部分 --%>
<div style="background-color: #f0f0f0; padding: 10px; border-bottom: 1px solid #ccc; display: flex; justify-content: space-between; align-items: center;">
    <%-- タイトル --%>
    <div style="font-size: 1.5em; font-weight: bold;">得点管理システム</div>
    <%-- ユーザー情報とログアウトリンク --%>
    <div>
        <%-- ログインしているかに関わらず、ユーザー名とログアウトリンクの表示エリアを確保 --%>
        <%-- ログインしていない場合は teacherName は空文字になります --%>
        <%= teacherName %> &nbsp;&nbsp; <a href="<%= request.getContextPath() %>/logout" style="text-decoration: none; color: #007bff;">ログアウト</a>
    </div>
</div>
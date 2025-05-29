<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.sql.*" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生情報の編集</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<h2>学生情報の編集</h2>
<%
    String idStr = request.getParameter("id");
    if (idStr == null || idStr.isEmpty()) {
%>
    <p>学生IDが指定されていません。</p>
<%
    } else {
        int id = 0;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
%>
    <p>無効な学生IDです。</p>
<%
        }

        String url = "jdbc:mysql://localhost:3306/your_db?serverTimezone=UTC";
        String user = "root";
        String password = "password123";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            pstmt = conn.prepareStatement("SELECT * FROM students WHERE id = ?");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
%>
    <form action="student_update_done.jsp" method="post">
        <input type="hidden" name="id" value="<%= id %>">
        名前: <input type="text" name="name" value="<%= rs.getString("name") %>"><br>
        年齢: <input type="text" name="age" value="<%= rs.getInt("age") %>"><br>
        学科: <input type="text" name="department" value="<%= rs.getString("department") %>"><br>
        <input type="submit" value="更新">
    </form>
<%
            } else {
%>
    <p>該当する学生が見つかりませんでした。</p>
<%
            }
        } catch(Exception e) {
%>
    <p>データベース接続エラー: <%= e.getMessage() %></p>
<%
        } finally {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(pstmt != null) pstmt.close(); } catch(Exception e) {}
            try { if(conn != null) conn.close(); } catch(Exception e) {}
        }
    }
%>
</body>
</html>

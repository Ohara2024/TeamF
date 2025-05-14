<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.sql.*" %>
<html>
<head><title>科目情報の編集</title></head>
<body>
<h2>科目情報の編集</h2>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    String url = "jdbc:mysql://localhost:3306/your_db";
    String user = "your_user";
    String password = "your_password";

    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection conn = DriverManager.getConnection(url, user, password);
    PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM subjects WHERE id = ?");
    pstmt.setInt(1, id);
    ResultSet rs = pstmt.executeQuery();

    if (rs.next()) {
%>
    <form action="subject_update_done.jsp" method="post">
        <input type="hidden" name="id" value="<%= id %>">
        科目名: <input type="text" name="name" value="<%= rs.getString("name") %>"><br>
        単位数: <input type="text" name="credit" value="<%= rs.getInt("credit") %>"><br>
        <input type="submit" value="更新">
    </form>
<%
    }
    rs.close();
    pstmt.close();
    conn.close();
%>
</body>
</html>

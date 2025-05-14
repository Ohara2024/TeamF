<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.sql.*" %>
<html>
<head><title>更新完了</title></head>
<body>
<h2>更新完了</h2>
<%
    request.setCharacterEncoding("UTF-8");
    int id = Integer.parseInt(request.getParameter("id"));
    String name = request.getParameter("name");
    int credit = Integer.parseInt(request.getParameter("credit"));

    String url = "jdbc:mysql://localhost:3306/your_db";
    String user = "your_user";
    String password = "your_password";

    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection conn = DriverManager.getConnection(url, user, password);
    PreparedStatement pstmt = conn.prepareStatement("UPDATE subjects SET name = ?, credit = ? WHERE id = ?");
    pstmt.setString(1, name);
    pstmt.setInt(2, credit);
    pstmt.setInt(3, id);
    int result = pstmt.executeUpdate();

    if (result > 0) {
        out.println("科目情報を更新しました。<br>");
    } else {
        out.println("更新に失敗しました。<br>");
    }

    pstmt.close();
    conn.close();
%>
<a href="subject_list.jsp">科目一覧に戻る</a>
</body>
</html>

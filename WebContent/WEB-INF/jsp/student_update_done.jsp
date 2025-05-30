<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.sql.*" %>
<%
    request.setCharacterEncoding("UTF-8");

    int id = Integer.parseInt(request.getParameter("id"));
    String name = request.getParameter("name");
    int age = Integer.parseInt(request.getParameter("age"));
    String department = request.getParameter("department");

    Connection conn = null;
    PreparedStatement pstmt = null;

    try {
        Class.forName("org.h2.Driver");
        conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/exam", "sa", "");

        pstmt = conn.prepareStatement(
            "UPDATE STUDENT SET NAME = ?, AGE = ?, DEPARTMENT = ? WHERE NO = ?"
        );
        pstmt.setString(1, name);
        pstmt.setInt(2, age);
        pstmt.setString(3, department);
        pstmt.setInt(4, id); // `NO`カラムをIDとして使用

        int result = pstmt.executeUpdate();

        if (result > 0) {
            out.println("<p>学生情報を更新しました。</p>");
        } else {
            out.println("<p>更新に失敗しました。</p>");
        }

    } catch (Exception e) {
        out.println("<p>エラー: " + e.getMessage() + "</p>");
    } finally {
        if (pstmt != null) try { pstmt.close(); } catch (Exception ignored) {}
        if (conn != null) try { conn.close(); } catch (Exception ignored) {}
    }
%>

<a href="studentlist.jsp">学生一覧に戻る</a>

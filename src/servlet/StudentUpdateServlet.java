package servlet;

import java.io.IOException;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/student/update")
public class StudentUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String department = request.getParameter("department");

        String url = "jdbc:mysql://localhost:3306/your_db?serverTimezone=UTC";
        String user = "root";
        String password = "password123";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement("UPDATE students SET name = ?, age = ?, department = ? WHERE id = ?")) {

            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, department);
            pstmt.setInt(4, id);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new ServletException(e);
        }

        // 更新完了後、一覧画面へリダイレクト
        response.sendRedirect(request.getContextPath() + "/student/list");
    }
}

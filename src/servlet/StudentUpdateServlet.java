package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/student/update")
public class StudentUpdateServlet extends HttpServlet {

    // GETリクエスト対応（エラーメッセージ or フォーム表示など）
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // エラーメッセージを返す（または編集フォームへ遷移など）
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().println("このURLはPOSTメソッドでのみ使用できます。");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String department = request.getParameter("department");

        String url = "jdbc:mysql://localhost:3306/your_db?serverTimezone=UTC";
        String user = "root";
        String password = "password123";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(
                     "UPDATE students SET name = ?, age = ?, department = ? WHERE id = ?")) {

            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, department);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new ServletException(e);
        }

        response.sendRedirect(request.getContextPath() + "/student/list");
    }
}

package servlet;

import java.io.IOException;
import java.sql.Connection;  // ← ここを修正
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;

@WebServlet("/student/edit")
public class StudentEditServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if(idStr == null) {
            response.sendRedirect(request.getContextPath() + "/student/list");
            return;
        }

        int id = Integer.parseInt(idStr);
        System.out.println("編集画面にアクセスした学生ID: " + id); // ログ出力

        String url = "jdbc:mysql://localhost:3306/your_db?serverTimezone=UTC";
        String user = "root";
        String password = "password123";

        Student s = null;

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM students WHERE id = ?")) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()) {
                    s = new Student();
                    s.setId(rs.getInt("id"));
                    s.setName(rs.getString("name"));
                    s.setAge(rs.getInt("age"));
                    s.setDepartment(rs.getString("department"));
                }
            }

        } catch (SQLException e) {
            throw new ServletException(e);
        }

        if(s == null) {
            request.getSession().setAttribute("errorMessage", "指定された学生が存在しません。");
            response.sendRedirect(request.getContextPath() + "/student/list");
            return;
        }

        request.setAttribute("student", s);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/student_update.jsp");
        dispatcher.forward(request, response);
    }
}

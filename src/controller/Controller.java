package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updateStudent")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String department = request.getParameter("department");

        String url = "jdbc:mysql://localhost:3306/your_db";
        String user = "your_user";
        String password = "your_password";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE students SET name = ?, age = ?, department = ? WHERE id = ?"
            );

            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, department);
            pstmt.setInt(4, id);

            int result = pstmt.executeUpdate();
            if (result > 0) {
                out.println("<h2>学生情報を更新しました。</h2>");
            } else {
                out.println("<h2>更新に失敗しました。</h2>");
            }

            pstmt.close();
            conn.close();
        } catch (Exception e) {
            out.println("<h2>エラーが発生しました：" + e.getMessage() + "</h2>");
            e.printStackTrace(out);
        }

        out.println("<a href='student_list.jsp'>学生一覧に戻る</a>");
    }
}

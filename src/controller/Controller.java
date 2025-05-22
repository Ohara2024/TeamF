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

    // DB接続情報はここにまとめておくと分かりやすいです（実務では外部設定ファイルに切り出すのが望ましい）
    private static final String DB_URL = "jdbc:mysql://localhost:3306/student_db?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 文字コード設定
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        try {
            // パラメータ取得（数値のパースは例外処理しておくのが安全）
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            int age = Integer.parseInt(request.getParameter("age"));
            String department = request.getParameter("department");

            // JDBCドライバ読み込み
            Class.forName("com.mysql.cj.jdbc.Driver");

            // DB接続
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(
                    "UPDATE students SET name = ?, age = ?, department = ? WHERE id = ?")) {

                pstmt.setString(1, name);
                pstmt.setInt(2, age);
                pstmt.setString(3, department);
                pstmt.setInt(4, id);

                int updated = pstmt.executeUpdate();

                if (updated > 0) {
                    out.println("<h2>学生情報を更新しました。</h2>");
                } else {
                    out.println("<h2>更新に失敗しました。IDが存在しない可能性があります。</h2>");
                }
            }

        } catch (NumberFormatException e) {
            out.println("<h2>IDや年齢は数値で入力してください。</h2>");
        } catch (Exception e) {
            out.println("<h2>エラーが発生しました: " + e.getMessage() + "</h2>");
            e.printStackTrace(out);
        }

        out.println("<a href='student_list.jsp'>学生一覧に戻る</a>");
    }
}

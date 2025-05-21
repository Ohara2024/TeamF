package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class StudentUpdateAction implements Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));

        // データベースから学生情報を取得（例: SELECT * FROM students WHERE id = ?）
        // Student student = StudentDAO.findById(id); // DAO例
        // request.setAttribute("student", student);

        return "student_update.jsp"; // 編集画面に遷移
    }
}

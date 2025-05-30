package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao; // DAOパッケージのimportが必要
import tool.Action;

public class StudentUpdateAction implements Action {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));

        // 学生情報をDBから取得
        Student student = StudentDao.findById(id);
        request.setAttribute("student", student);

        // 編集画面に遷移
        request.getRequestDispatcher("WEB-INF/views/student_update.jsp").forward(request, response);
    }
}

package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;
import tool.Action;

public class StudentUpdateExecuteAction implements Action {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String grade = request.getParameter("grade");
        String email = request.getParameter("email");

        // DB更新処理
        StudentDao.update(id, name, grade, email);

        // 一覧画面にリダイレクト（POST-Redirect-GETパターン推奨）
        response.sendRedirect("StudentList.action");
    }
}

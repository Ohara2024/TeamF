package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int credit = Integer.parseInt(request.getParameter("credit"));
        String teacher = request.getParameter("teacher");

        // DB更新処理
        SubjectDao.update(id, name, credit, teacher);

        // 一覧画面にリダイレクト
        response.sendRedirect("SubjectList.action");
    }
}

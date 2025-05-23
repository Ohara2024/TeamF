package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class SubjectUpdateAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));

        // 科目情報をDBから取得（例）
        // Subject subject = SubjectDAO.findById(id);
        // request.setAttribute("subject", subject);

        // 編集画面にフォワード
        request.getRequestDispatcher("WEB-INF/views/subject_update.jsp").forward(request, response);
    }
}

package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class SubjectUpdateExecuteAction implements Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int credit = Integer.parseInt(request.getParameter("credit"));
        String teacher = request.getParameter("teacher");

        // DB更新処理（例: UPDATE subjects SET name=?, credit=?, teacher=? WHERE id=?）
        // 更新成功したら一覧画面へ遷移
        return "subject_list.jsp";
    }
}

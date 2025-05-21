package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class SubjectUpdateAction implements Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        // データベースから科目情報を取得してリクエスト属性に設定する処理（省略）
        // request.setAttribute("subject", subject);
        return "subject_update.jsp";
    }
}

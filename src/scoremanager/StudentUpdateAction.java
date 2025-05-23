package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class StudentUpdateAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // ここで更新処理などを実装（例）
        request.setAttribute("message", "学生情報更新完了");
        request.getRequestDispatcher("/studentUpdateResult.jsp").forward(request, response);
    }
}

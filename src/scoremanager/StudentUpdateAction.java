package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class StudentUpdateAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 更新処理のサンプル（実装は必要に応じて）
        request.setAttribute("message", "学生情報更新完了");
        request.getRequestDispatcher("/scoremanager/main/student_update_result.jsp").forward(request, response);
    }
}

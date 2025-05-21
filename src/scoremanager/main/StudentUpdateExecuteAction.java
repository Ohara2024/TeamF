package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class StudentUpdateExecuteAction implements Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String grade = request.getParameter("grade");
        String email = request.getParameter("email");

        // DB更新処理（例: UPDATE students SET name=?, grade=?, email=? WHERE id=?）
        // StudentDAO.update(id, name, grade, email); // DAO使用例

        return "student_list.jsp"; // 一覧画面に遷移
    }
}

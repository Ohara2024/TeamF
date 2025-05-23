package scoremanager.main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentListAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            StudentDao studentDao = new StudentDao();
            List<Student> students = studentDao.findAll();

            // 取得した学生リストをリクエスト属性にセット
            req.setAttribute("students", students);

            // JSPへフォワード（表示用ページへ遷移）
            req.getRequestDispatcher("/scoremanager/main/student_list.jsp").forward(req, res);

        } catch (Exception e) {
            e.printStackTrace();
            // エラーページにリダイレクトなどの処理も考慮
        }
    }
}

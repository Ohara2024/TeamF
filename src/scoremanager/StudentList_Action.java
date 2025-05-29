package scoremanager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentList_Action implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            // 文字コード設定（GET/POST共通ならここに書くことも多い）
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            // StudentDaoから学生リストを取得
            StudentDao dao = new StudentDao();
            List<Student> studentList = dao.findAll();

            // リクエスト属性にセット
            request.setAttribute("studentList", studentList);

            // JSPにフォワード
            request.getRequestDispatcher("/WEB-INF/views/studentList.jsp").forward(request, response);

        } catch (Exception e) {
            // エラー時はスタックトレースをコンソールに出してServletExceptionに包む
            e.printStackTrace();
            throw new Exception("StudentList_Action実行時にエラーが発生しました", e);
        }
    }
}

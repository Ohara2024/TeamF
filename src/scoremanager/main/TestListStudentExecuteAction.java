package scoremanager.main;

import java.io.IOException;
import java.util.List;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import bean.TestListStudent;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;

public class TestListStudentExecuteAction implements Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // 学校情報をセッションから取得
        School school = (School) req.getSession().getAttribute("school");

        // パラメータ取得
        String subjectCd = req.getParameter("subjectCd");

        // 科目情報の取得
        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.get(subjectCd, school);

        if (subject == null) {
            req.setAttribute("error", "指定された科目は存在しません。");
            req.getRequestDispatcher("jsp/test_list_student.jsp").forward(req, res);
            return;
        }

        // 学生一覧を取得（必要なら条件付きでも可能）
        StudentDao studentDao = new StudentDao();
        List<Student> studentList = studentDao.filter(school, null, null, true);

        // 該当科目の成績一覧取得
        TestListStudentDao testListStudentDao = new TestListStudentDao();
        List<TestListStudent> testList = testListStudentDao.filter(subject, studentList);

        // データをリクエストスコープに保存
        req.setAttribute("subject", subject);
        req.setAttribute("studentList", studentList);
        req.setAttribute("testList", testList);

        // 成績表示ページに遷移
        req.getRequestDispatcher("jsp/test_list_student.jsp").forward(req, res);
    }
}
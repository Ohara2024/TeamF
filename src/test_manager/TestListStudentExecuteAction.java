package test_manager;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.StudentDao;
import dao.TestListStudentDao;
import tool.Action;

@WebServlet(urlPatterns = {"/testmanager/studentexe"})
public class TestListStudentExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession(); // Lấy session
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        if (teacher == null) {
            req.setAttribute("errorMessage", "ログインしてください。");
            req.getRequestDispatcher("/login/login.jsp").forward(req, res);
            return;
        }

        School school = teacher.getSchool();
        if (school == null) {
            req.setAttribute("errorMessage", "学校情報がありません。");
            req.getRequestDispatcher("/test_manager/test_list.jsp").forward(req, res);
            return;
        }

        String studentNo = req.getParameter("studentNo");
        if (studentNo == null || studentNo.isEmpty()) {
            req.setAttribute("errorMessage", "学生番号を入力してください。");
            req.getRequestDispatcher("../test_manager/test_list.jsp").forward(req, res);
            return;
        }

        StudentDao studentDao = new StudentDao();
        Student student = studentDao.get(studentNo);

        if (student == null) {
            req.setAttribute("errorMessage", "該当の学生が存在しません。");
            req.getRequestDispatcher("../test_manager/test_list.jsp").forward(req, res);
            return;
        }

        TestListStudentDao testListStudentDao = new TestListStudentDao();
        List<TestListStudent> resultList = testListStudentDao.filter(student);

        req.setAttribute("student", student);
        req.setAttribute("resultList", resultList);

        req.getRequestDispatcher("../test_manager/test_list_student_result.jsp").forward(req, res);
    }
}

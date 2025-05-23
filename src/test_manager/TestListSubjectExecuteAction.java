package test_manager;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import bean.TestListSubject;
import dao.SchoolDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import dao.TestListSubjectDao;
import tool.Action;

@WebServlet(urlPatterns = {"/testmanager/subjectexe"})
public class TestListSubjectExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();

        Teacher teacher=(Teacher)session.getAttribute("teacher");
        School sch=teacher.getSchool();

        String schoolCd = sch.getCd();
        if (schoolCd == null || schoolCd.isEmpty()) {
            req.setAttribute("errorMessage", "学校情報が取得できません。");
            req.getRequestDispatcher("/test_manager/test_list.jsp").forward(req, res);
            return;
        }

        SchoolDao schoolDao = new SchoolDao();
        School school = schoolDao.get(schoolCd);

        String entYearStr = req.getParameter("entranceYear");
        String classNum = req.getParameter("classNum");
        String subjectCd = req.getParameter("subject");
        String studentNo = req.getParameter("studentNo");

        Integer entYear = null;
        if (entYearStr != null && !entYearStr.isEmpty()) {
            entYear = Integer.parseInt(entYearStr);
        }

        StudentDao studentDao = new StudentDao();
        SubjectDao subjectDao = new SubjectDao();
        TestListStudentDao testListStudentDao = new TestListStudentDao();
        TestListSubjectDao testListSubjectDao = new TestListSubjectDao();

        if (studentNo != null && !studentNo.isEmpty()) {
            Student student = studentDao.get(studentNo);
            if (student == null) {
                req.setAttribute("errorMessage", "該当の学生が存在しません。");
                req.getRequestDispatcher("/test_manager/test_list.jsp").forward(req, res);
                return;
            }

            List<TestListStudent> resultList = testListStudentDao.filter(student);
            req.setAttribute("resultList", resultList);
        } else {

            Subject subject = null;
            if (subjectCd != null && !subjectCd.isEmpty()) {
                subject = subjectDao.get(subjectCd, school);
                if (subject == null) {
                    req.setAttribute("errorMessage", "該当の科目が存在しません。");
                    req.getRequestDispatcher("/test_manager/test_list.jsp").forward(req, res);
                    return;
                }
            }

            if (entYear == null) entYear = 0;
            if (classNum == null) classNum = "";

            List<TestListSubject> resultList = testListSubjectDao.filter(entYear, classNum, subject, school);
            req.setAttribute("resultList", resultList);
        }

        req.getRequestDispatcher("/test_manager/test_list_result.jsp").forward(req, res);
    }
}

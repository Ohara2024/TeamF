package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Subject;
import bean.Test;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;

@WebServlet("/TestListStudentServlet")
public class TestListStudentServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String studentId = request.getParameter("studentId");

        if (studentId == null || studentId.trim().isEmpty()) {
            request.setAttribute("errorMessage", "学生番号を入力してください。");
            request.getRequestDispatcher("/test_list_student.jsp").forward(request, response);
            return;
        }

        try {
            StudentDao studentDao = new StudentDao();
            Student student = studentDao.get(studentId);

            if (student == null) {
                request.setAttribute("errorMessage", "該当する学生が見つかりませんでした。");
                request.getRequestDispatcher("/test_list_student.jsp").forward(request, response);
                return;
            }

            TestDao testDao = new TestDao();
            List<Test> testList = testDao.findByStudent(student);

            SubjectDao subjectDao = new SubjectDao();
            for (Test test : testList) {
                Subject subject = subjectDao.get(test.getSubject().getCd(), student.getSchool());
                test.setSubject(subject);
            }

            request.setAttribute("student", student);
            request.setAttribute("testList", testList);

            request.getRequestDispatcher("/test_list_student.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace(); // ログ出力などを行う
            request.setAttribute("errorMessage", "処理中にエラーが発生しました。");
            request.getRequestDispatcher("/test_list_student.jsp").forward(request, response);
        }
    }
}
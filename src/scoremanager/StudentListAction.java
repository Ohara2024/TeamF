package scoremanager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;

public class StudentListAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            StudentDao studentDao = new StudentDao();
            List<Student> students = studentDao.findAll();
            req.setAttribute("students", students);
            req.getRequestDispatcher("/scoremanager/main/student_list.jsp").forward(req, res);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}

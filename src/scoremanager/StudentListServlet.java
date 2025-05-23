package scoremanager;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import bean.Student;
import dao.StudentDao;

@WebServlet("/studentList")
public class StudentListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        try {
            StudentDao dao = new StudentDao();
            List<Student> students = dao.findAll();
            request.setAttribute("students", students);
            request.getRequestDispatcher("/scoremanager/main/student_list.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}

package scoremanager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentListAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        StudentDao dao = new StudentDao();
        List<Student> students = dao.findAll();
        request.setAttribute("students", students);
        request.getRequestDispatcher("/scoremanager/main/student_list.jsp").forward(request, response);
    }
}

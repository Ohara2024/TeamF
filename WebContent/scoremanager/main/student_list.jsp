package scoremanager;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tool.Action;
import bean.Student;
import dao.StudentDao;

public class StudentListAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        StudentDao studentDao = new StudentDao();
        List<Student> students = studentDao.findAll();
        request.setAttribute("students", students);
        request.getRequestDispatcher("/scoremanager/main/student_list.jsp").forward(request, response);
    }
}

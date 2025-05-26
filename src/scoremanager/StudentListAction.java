package scoremanager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentListAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        StudentDao studentDao = new StudentDao();
        List<Student> students = studentDao.findAll();
        req.setAttribute("students", students);
        req.getRequestDispatcher("/WEB-INF/views/studentList.jsp").forward(req, res);
    }
}

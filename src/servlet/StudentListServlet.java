package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;

@WebServlet("/student/list")
public class StudentListServlet extends HttpServlet {

    private StudentDao dao;

    @Override
    public void init() throws ServletException {
        dao = new StudentDao(); // DAOインスタンス作成
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Student> studentList = dao.findAll(); // DAOから学生一覧を取得

        request.setAttribute("students", studentList); // JSPにデータを渡す

        request.getRequestDispatcher("/WEB-INF/jsp/student_list.jsp") // JSPファイルへフォワード
               .forward(request, response);
    }
}

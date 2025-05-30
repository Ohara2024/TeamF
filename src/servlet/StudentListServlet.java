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

@WebServlet("/student-list")
public class StudentListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        try {
            StudentDao dao = new StudentDao();
            List<Student> studentList = dao.findAll();

            request.setAttribute("studentList", studentList);

            // JSPファイル名の大文字小文字を正しく
            request.getRequestDispatcher("/WEB-INF/jsp/StudentList.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("データ取得エラー", e);
        }
    }
}

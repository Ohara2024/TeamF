package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.TestListSubject;
import dao.TestListSubjectDao;

@WebServlet("/TestListSubjectServlet")
public class TestListSubjectServlet extends HttpServlet {

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

        int entYear = Integer.parseInt(request.getParameter("entYear"));
        String classNum = request.getParameter("classNum");
        String subjectCd = request.getParameter("subjectCd");
        String schoolCd = request.getParameter("schoolCd");

        // バリデーションは省略

        try {
            TestListSubjectDao dao = new TestListSubjectDao();
            List<TestListSubject> testList = dao.filter(entYear, classNum, subjectCd, schoolCd);

            request.setAttribute("testList", testList);
            request.getRequestDispatcher("/test_list_subject.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace(); // ログ出力などを行う
            request.setAttribute("errorMessage", "処理中にエラーが発生しました。");
            request.getRequestDispatcher("/test_list_subject.jsp").forward(request, response);
        }
    }
}
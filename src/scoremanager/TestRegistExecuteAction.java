package scoremanager;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
public class TestRegistExecuteAction extends tool.Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        // 検索条件
        int entYear = Integer.parseInt(request.getParameter("entYear"));
        String classNum = request.getParameter("classNum");
        String subjectCd = request.getParameter("subjectCd");
        int no = Integer.parseInt(request.getParameter("no"));

        School school = (School) request.getSession().getAttribute("school");
        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.get(subjectCd, school);

        // 生徒番号一覧
        String[] studentNos = request.getParameterValues("studentNos");

        // 登録用Testリストを作る
        List<Test> tests = new ArrayList<>();
        StudentDao studentDao = new StudentDao();
        for (String studentNo : studentNos) {
            Student student = studentDao.get(studentNo);

            // テキストボックス名: point_学籍番号
            String pointParam = "point_" + studentNo;
            int point = Integer.parseInt(request.getParameter(pointParam));

            Test test = new Test();
            test.setStudent(student);
            test.setSubject(subject);
            test.setSchool(school);
            test.setNo(no);
            test.setPoint(point);
            test.setClassNum(classNum);

            tests.add(test);
        }

        // 一括登録・更新
        TestDao testDao = new TestDao();
        testDao.save(tests);

        // 完了後は再検索して一覧画面に戻る（リダイレクトでも可）
        // ここでは同じ条件で一覧を再表示
        request.setAttribute("message", "登録が完了しました");
        // 検索条件をセットしてforward
        request.getRequestDispatcher("/TestRegist.action?entYear=" + entYear + "&classNum=" + classNum + "&subjectCd=" + subjectCd + "&no=" + no)
            .forward(request, response);
    }
}
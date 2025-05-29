package main;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import bean.Subject;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;

public class TestRegistAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            School school = (School) request.getSession().getAttribute("school");

            // --- プルダウン用リスト取得 ---
            StudentDao studentDao = new StudentDao();
            Set<Integer> entYearSet = new LinkedHashSet<>(studentDao.getEntYearList(school)); // 年度
            request.setAttribute("entYearSet", entYearSet);

            ClassNumDao classNumDao = new ClassNumDao();
            Set<String> classNumSet = new LinkedHashSet<>(classNumDao.filter(school)); // クラス
            request.setAttribute("classNumSet", classNumSet);

            SubjectDao subjectDao = new SubjectDao();
            List<Subject> subjectList = subjectDao.filter(school); // 科目
            request.setAttribute("subjectList", subjectList);

            // --- 検索条件取得 ---
            String fEntYear = request.getParameter("fEntYear");
            String fClassNum = request.getParameter("fClassNum");
            String fSubjectCd = request.getParameter("fSubjectCd");
            String fTestNo = request.getParameter("fTestNo");
            request.setAttribute("fEntYear", fEntYear);
            request.setAttribute("fClassNum", fClassNum);
            request.setAttribute("fSubjectCd", fSubjectCd);
            request.setAttribute("fTestNo", fTestNo);

            // --- 検索実行（生徒リスト） ---
            List<Student> students = null;
            Map<String, Integer> pointsMap = new HashMap<>();
            Subject searchedSubject = null;
            String searchedTestNo = null;

            if (fEntYear != null && !fEntYear.isEmpty()
                    && fClassNum != null && !fClassNum.isEmpty()
                    && fSubjectCd != null && !fSubjectCd.isEmpty()
                    && fTestNo != null && !fTestNo.isEmpty()) {
                // 生徒リスト取得
                students = studentDao.filter( school,Integer.parseInt(fEntYear), fClassNum,school);
                request.setAttribute("students", students);

                // 科目取得
                searchedSubject = subjectDao.get(fSubjectCd, school);
                request.setAttribute("searchedSubject", searchedSubject);
                searchedTestNo = fTestNo;
                request.setAttribute("searchedTestNo", searchedTestNo);

                // 生徒ごとの得点（既存データがあればセット。ここはTestDaoのfilterで取得する想定）
                // TestDao testDao = new TestDao();
                // List<Test> tests = testDao.filter(...);
                // for (Test t : tests) {
                //     pointsMap.put(t.getStudent().getNo(), t.getPoint());
                // }
                // 上記のようなロジックでpointsMapをセット
                // 仮に空でもJSPでnullチェックできるように
                request.setAttribute("pointsMap", pointsMap);
            }

            // メッセージ処理（必要に応じて）
            // request.setAttribute("errorMessage", ...);
            // request.setAttribute("infoMessage", ...);

            // フォワード
            RequestDispatcher rd = request.getRequestDispatcher("/main/test_regist.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            // エラー時の処理
            request.setAttribute("errorMessage", "システムエラーが発生しました: " + e.getMessage());
            RequestDispatcher rd = request.getRequestDispatcher("/main/test_regist.jsp");
            rd.forward(request, response);
        }
    }

    // GETリクエストも同じ画面を表示したい場合はこちらもオーバーライド
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
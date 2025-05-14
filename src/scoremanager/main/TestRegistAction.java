package scoremanager.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.TestListSubject;
import dao.SubjectDao;
import dao.TestListSubjectDao;

public class TestRegistAction implements Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // 学校情報をセッションから取得
        School school = (School) req.getSession().getAttribute("school");

        // 科目一覧を取得
        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjectList = subjectDao.filter(school);
        req.setAttribute("subjectList", subjectList);

        // リクエストから登録するテスト情報を取得
        String subjectCd = req.getParameter("subjectCd");
        String testName = req.getParameter("testName");
        String testDate = req.getParameter("testDate");

        // 入力値のバリデーション（簡易）
        List<String> errors = new ArrayList<>();
        if (subjectCd == null || subjectCd.isEmpty()) {
            errors.add("科目コードが入力されていません。");
        }
        if (testName == null || testName.isEmpty()) {
            errors.add("テスト名が入力されていません。");
        }
        if (testDate == null || testDate.isEmpty()) {
            errors.add("テスト日が入力されていません。");
        }

        // エラーがあればJSPに戻す
        if (!errors.isEmpty()) {
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("jsp/test_regist.jsp").forward(req, res);
            return;
        }

        // 登録するテストのエンティティ作成
        TestListSubject test = new TestListSubject();
        test.setSubjectCd(subjectCd);
        test.setName(testName);
        test.setTestDate(testDate);
        test.setSchool(school);

        // DAOを使ってデータベースに登録
        TestListSubjectDao testDao = new TestListSubjectDao();
        boolean result = testDao.post(test);

        if (!result) {
            errors.add("登録に失敗しました。");
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("jsp/test_regist.jsp").forward(req, res);
            return;
        }

        // 登録成功後の遷移先（登録完了ページ）
        req.getRequestDispatcher("jsp/test_regist_don.jsp").forward(req, res);
    }
}

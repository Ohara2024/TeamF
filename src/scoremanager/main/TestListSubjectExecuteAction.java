package scoremanager.main;

import java.io.IOException;
import java.util.List;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClassNum;
import bean.School;
import bean.TestListSubject;
import dao.SubjectDao;
import dao.TestListSubjectDao;

public class TestListSubjectExecuteAction implements tool.Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        // 学校情報をセッションから取得
        School school = (School) req.getSession().getAttribute("school");

        // パラメータの取得
        String classNum = req.getParameter("class_num");
        String enterYearStr = req.getParameter("enteryear");

        // パラメータの検証
        if (classNum == null || enterYearStr == null) {
            req.setAttribute("error", "学年またはクラスが指定されていません。");
            req.getRequestDispatcher("jsp/test_list_subject.jsp").forward(req, res);
            return;
        }

        int enterYear = Integer.parseInt(enterYearStr);

        // クラス情報の生成
        ClassNum cn = new ClassNum();
        cn.setClass_num(classNum);
        cn.setSchool(school);

        // 科目一覧を取得（このクラスに属する科目）
        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjectList = subjectDao.filter(school);

        // テスト一覧取得（科目ごとの平均点など）
        TestListSubjectDao testListSubjectDao = new TestListSubjectDao();
        List<TestListSubject> testList = testListSubjectDao.filter(enterYear, classNum, school);

        // リクエストスコープに保存
        req.setAttribute("enterYear", enterYear);
        req.setAttribute("classNum", classNum);
        req.setAttribute("subjectList", subjectList);
        req.setAttribute("testList", testList);

        // 表示ページへフォワード
        req.getRequestDispatcher("jsp/test_list_subject.jsp").forward(req, res);
    }
}

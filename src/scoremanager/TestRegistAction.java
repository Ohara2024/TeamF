package scoremanager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッションの取得（ログインしている教員情報を取得するため）
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // ログインしていなければlogin画面へリダイレクト
        if (teacher == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        // 教員が所属する学校情報を取得
        School teacherSchool = teacher.getSchool();
        if (teacherSchool == null) {
        	Map<String, String> errors = new HashMap<>();
        	errors.put("school", "学校情報が取得できません");
        	req.setAttribute("errors", errors);
        }

        // 入力値や内部処理用の変数定義
        String entYearStr = req.getParameter("f1");
        String classNum = req.getParameter("f2");
        String subject = req.getParameter("f3");
        String countStr = req.getParameter("f4");

        int entYear = 0;
        int count = 0;
        Map<String, String> errors = new HashMap<>();

        // パラメータの前処理（null→空文字）
        if (classNum == null) classNum = "";
        if (subject == null) subject = "";

        // 各種DAOの初期化
        ClassNumDao cNumDao = new ClassNumDao();
        SubjectDao subjectDao = new SubjectDao();
        TestDao testDao = new TestDao();

        // DBから現在の学校に属するクラス・科目情報を取得
        List<String> cNumlist = cNumDao.filter(teacherSchool);
        if (cNumlist == null) cNumlist = new ArrayList<>();
        List<Subject> list = subjectDao.filter(teacherSchool);
        if (list == null) list = new ArrayList<>();

        // 入力された文字列から数値に変換（未入力の場合はスキップ）
        try {
            if (entYearStr != null && !entYearStr.isEmpty()) {
                entYear = Integer.parseInt(entYearStr);
            }
        } catch (NumberFormatException e) {
            errors.put("entYear", "入学年度が不正です");
        }
        try {
            if (countStr != null && !countStr.isEmpty()) {
                count = Integer.parseInt(countStr);
            }
        } catch (NumberFormatException e) {
            errors.put("count", "回数が不正です");
        }

        // 入学年度選択用リスト作成（現在年から±10年分）
        int year = LocalDate.now().getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year + 10; i++) {
            entYearSet.add(i);
        }

        // 回数選択用リスト作成（1～2回）
        List<Integer> countSet = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            countSet.add(i);
        }

        // 検索実行フラグ
        boolean doSearch = false;
        if (entYear > 0 && !classNum.isEmpty() && !classNum.equals("0")
                && !subject.isEmpty() && !subject.equals("0") && count > 0) {
            doSearch = true;
        }

        // 検索処理
        if (doSearch) {
            Subject selectedSubject = subjectDao.get(subject, teacherSchool);
            if (selectedSubject != null) {
                List<Test> testlist = testDao.filter(entYear, classNum, selectedSubject, count, teacherSchool);
                req.setAttribute("testlist", testlist);
                req.setAttribute("subject_name", selectedSubject.getName());
            } else {
                errors.put("subject", "科目が正しく選択されていません");
            }
        } else if (entYearStr != null || classNum != null || subject != null || countStr != null) {
            // 何かしら条件が入力されているのに検索条件がそろっていない場合
            errors.put("a", "入学年度とクラスと科目と回数を選択してください");
        }

        // 入力情報やリスト類を画面に渡す
        req.setAttribute("f1", entYearStr != null ? entYearStr : "");
        req.setAttribute("f2", classNum);
        req.setAttribute("f3", subject);
        req.setAttribute("f4", countStr != null ? countStr : "");
        req.setAttribute("entYearList", entYearSet);
        req.setAttribute("cNumList", cNumlist);
        req.setAttribute("list", list);
        req.setAttribute("countList", countSet);
        req.setAttribute("errors", errors);

        // JSPへ画面遷移
        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}
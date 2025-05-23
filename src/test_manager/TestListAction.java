package test_manager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

@WebServlet(urlPatterns = {"/testmanager/list"})
public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        // セッションにログイン情報がなければログインページへリダイレクト
        if (teacher == null) {
            res.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        // DAOの初期化
        ClassNumDao cNumDao = new ClassNumDao();
        SubjectDao subjectDao = new SubjectDao();

        // 教員が所属する学校を取得
        School teacherSchool = teacher.getSchool();

        // 現在の年から10年幅で入学年度を作成
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year + 10; i++) {
            entYearSet.add(i);
        }

        // DBから必要なリストを取得
        List<String> cNumlist = cNumDao.filter(teacherSchool);
        List<Subject> list = subjectDao.filter(teacherSchool);

        // 画面に渡す値を設定
        req.setAttribute("cNumlist", cNumlist);
        req.setAttribute("list", list);
        req.setAttribute("entYearSet", entYearSet);

        // JSPへフォワード
        req.getRequestDispatcher("../test_manager/test_list.jsp").forward(req, res);
    }
}

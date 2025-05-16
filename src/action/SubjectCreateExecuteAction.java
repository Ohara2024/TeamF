package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;


public class SubjectCreateExecuteAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        if (teacher == null) {
            // ログインしていない場合はログインページへリダイレクト
            return "redirect:login.jsp";
        }

        // Teacher オブジェクトから関連付けられた School オブジェクトを取得し、その School オブジェクトから学校コードを取得
        // Teacher Bean に getSchool() メソッドがあり、かつ bean.School に getCd() メソッドがあることを想定
        School teacherSchool = teacher.getSchool();

        if (teacherSchool == null) {
            // Teacher オブジェクトに School オブジェクトがセットされていない場合のハンドリング
            request.setAttribute("error", "教員に学校情報が紐づいていません。");
            // 入力値を保持して画面に戻る（任意）
            request.setAttribute("cd", request.getParameter("cd"));
            request.setAttribute("name", request.getParameter("name"));
            return "subject_create.jsp"; // エラー画面に戻るか、別のページに遷移
        }

        String schoolCd = teacherSchool.getCd(); // School Bean に getCd() があると仮定

        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        // 入力値検証
        if (cd == null || cd.isEmpty()) {
            request.setAttribute("error", "科目コードを入力してください");
            request.setAttribute("name", name);
            return "subject_create.jsp";
        }
        if (name == null || name.isEmpty()) {
            request.setAttribute("error", "科目名を入力してください");
            request.setAttribute("cd", cd);
            return "subject_create.jsp";
        }

        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);

        // SubjectDao.save() は Subject オブジェクトに School オブジェクトがセットされていることを期待
        // 取得した teacherSchool オブジェクトを Subject にセット
        subject.setSchool(teacherSchool);

        // SubjectDao を使用して科目をデータベースに保存
        SubjectDao subjectDao = new SubjectDao();
        boolean success = subjectDao.save(subject); // save メソッドが Exception をスローする可能性あり

        if (success) {
            // 登録成功の場合、"redirect:subject_done.jsp" という文字列を返す
            // FrontController がこの文字列を見て、subject_done.jsp にリダイレクトする
            return "redirect:subject_done.jsp";
        } else {
            // 登録失敗の場合（主に科目コード重複）
            request.setAttribute("error", "科目コードが重複しています");
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);
            return "subject_create.jsp"; // 科目登録画面にフォワード
        }
    }
}
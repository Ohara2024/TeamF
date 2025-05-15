package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// TeacherDao が bean.Teacher を使用しているため、こちらも bean.Teacher をインポート
import bean.Teacher;
import dao.TeacherDao;
// もし School クラスが必要な場合はインポート (LoginExecuteActionでは直接は使わない想定ですが念のため)
// import bean.School;


public class LoginExecuteAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id"); // ログインフォームからIDを取得
        String password = request.getParameter("password"); // ログインフォームからパスワードを取得

        // TeacherDao を使用して認証を行う
        TeacherDao teacherDao = new TeacherDao();
        // TeacherDao.login() メソッドを呼び出し、認証結果として Teacher オブジェクトを取得
        // LoginExecuteAction の execute メソッドが Exception をスローするため、login() の例外をそのまま伝播
        Teacher teacher = teacherDao.login(id, password);

        if (teacher != null) {
            // 認証成功の場合
            // セッションを取得（存在しない場合は新しく作成）
            HttpSession session = request.getSession();
            // セッションに認証済みの Teacher オブジェクトを "user" という名前で保存
            session.setAttribute("user", teacher);
            // メニュー画面にリダイレクト（FrontControllerで処理される "redirect:" プレフィックスを使用）
            return "redirect:menu.jsp";
        } else {
            // 認証失敗の場合
            // リクエスト属性にエラーメッセージを設定
            request.setAttribute("error", "IDまたはパスワードが確認できませんでした");
            // リクエスト属性に入力されたIDを保持し、ログイン画面で再表示できるようにする
            request.setAttribute("id", id);
            // ログイン画面にフォワード
            return "login.jsp";
        }
    }
}
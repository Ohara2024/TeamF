package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.LoginAction;
import action.LoginExecuteAction;
import action.LogoutAction;
import action.SubjectCreateExecuteAction;
// 必要に応じて他のActionクラスをインポート

@WebServlet({"/login", "/logout", "/subject/*"}) // ログイン、ログアウト、subject以下のパスをマッピング
public class FrontController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath(); // /login, /logout, /subject
        String pathInfo = request.getPathInfo(); // /subject の後のパス情報 (例: /create)

        Action action = null;
        String view = null;

        if ("/login".equals(servletPath)) {
            // GET /login -> ログイン画面表示
            action = new LoginAction();
        } else if ("/logout".equals(servletPath)) {
            // GET /logout -> ログアウト処理
             action = new LogoutAction();
        } else if ("/subject".equals(servletPath)) {
            if ("/create".equals(pathInfo)) {
                // GET /subject/create -> 科目登録画面表示
                // SubjectCreateAction（表示用）を作ることもできますが、
                // 今回は直接JSPにフォワードする、またはActionで必要なデータ設定だけ行う形とします
                 view = "/subject_create.jsp"; // 直接JSPを指定
            }
            // TODO: /subject/list など、他のGETリクエストの振り分けを追加
        } else {
             // マッピングされていないGETリクエスト
             response.sendError(HttpServletResponse.SC_NOT_FOUND);
             return;
        }

        if (action != null) {
             try {
                 view = action.execute(request, response);
             } catch (Exception e) {
                 e.printStackTrace();
                 throw new ServletException(e); // エラーハンドリングを適切に行う
             }
        }

        if (view != null) {
            if (view.startsWith("redirect:")) {
                // リダイレクトの場合
                response.sendRedirect(request.getContextPath() + view.substring("redirect:".length()));
            } else {
                // フォワードの場合
                request.getRequestDispatcher(view).forward(request, response);
            }
        } else if (action != null) {
             // Actionがnullではないがviewがnullの場合は、Action内でレスポンスが完了しているとみなす
             // （例: リダイレクトをAction自身が行う場合など）
             // 今回のAction実装ではviewを返しているので、基本的にはここには来ない想定
        } else {
             // Actionもviewもnullの場合（エラーなど）
             // すでに上記で404を返しているため、基本ここには来ない
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath(); // /login, /subject など
        String pathInfo = request.getPathInfo(); // /subject の後のパス情報 (例: /create)

        Action action = null;
        String view = null;

        if ("/login".equals(servletPath)) {
            // POST /login -> ログイン実行
            action = new LoginExecuteAction();
        } else if ("/subject".equals(servletPath)) {
            if ("/create".equals(pathInfo)) {
                 // POST /subject/create -> 科目登録実行
                 action = new SubjectCreateExecuteAction();
            }
            // TODO: /subject/update, /subject/delete など、他のPOSTリクエストの振り分けを追加
        }
         // 必要に応じて他のPOSTリクエストの振り分けを追加

        if (action != null) {
            try {
                view = action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException(e); // エラーハンドリングを適切に行う
            }
        }

         if (view != null) {
            if (view.startsWith("redirect:")) {
                // リダイレクトの場合
                response.sendRedirect(request.getContextPath() + view.substring("redirect:".length()));
            } else {
                // フォワードの場合
                request.getRequestDispatcher(view).forward(request, response);
            }
        } else if (action != null) {
            // Actionがnullではないがviewがnullの場合は、Action内でレスポンスが完了しているとみなす
            // （例: リダイレクトをAction自身が行う場合など）
             // 今回のAction実装ではviewを返しているので、基本的にはここには来ない想定
        } else {
            // マッピングされていないPOSTリクエスト
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
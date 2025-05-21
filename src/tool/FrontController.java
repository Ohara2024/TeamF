package tool;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // actionパラメータの取得
        String actionName = request.getParameter("action");

        if (actionName == null || actionName.isEmpty()) {
            actionName = "Default"; // 例: DefaultActionを使う
        }

        String className = "scoremanager.main." + actionName + "Action";

        try {
            // クラスを動的にロード
            Class<?> actionClass = Class.forName(className);

            // インスタンス化（引数なしコンストラクタを想定）
            Object actionInstance = actionClass.getDeclaredConstructor().newInstance();

            // もしActionインターフェースなど共通メソッドがあればキャストして呼び出し
            if (actionInstance instanceof Action) {
                Action action = (Action) actionInstance;
                // 例えばexecuteメソッドにリクエストとレスポンスを渡す想定
                action.execute(request, response);
            } else {
                // Actionインターフェース未実装ならエラー
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        "Actionクラスが不正です");
                return;
            }

        } catch (ClassNotFoundException e) {
            // クラスが見つからなかった場合の処理
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "指定されたアクションが存在しません");
        } catch (Exception e) {
            // その他の例外処理
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "内部エラーが発生しました");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // POSTもGETと同じ処理にする場合
        doGet(request, response);
    }
}

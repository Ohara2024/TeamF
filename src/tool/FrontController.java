package tool;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// *.action という拡張子のURLをすべて受け付けるFrontControllerサーブレット
@WebServlet(urlPatterns = { "*.action" })
public class FrontController extends HttpServlet {

	// GETリクエストを受け取ったときの処理
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			// 例: "/TestRegist.action" → "TestRegist.action" にする（先頭の "/" を削除）
			String path = req.getServletPath().substring(1);

			// ファイル名の ".action" を "Action" に変換し、"/" を "." に変換して
			// クラス名のパッケージ表記に変換する
			// 例: "TestRegist.action" → "TestRegistAction"
			// 例: "scoremanager/main/TestRegist.action" → "scoremanager.main.TestRegistAction"
			String name = path.replace(".a", "A").replace('/', '.');

			// クラス名からActionクラスのインスタンスを生成
			// newInstance() は引数なしのコンストラクタを呼び出す
			Action action = (Action) Class.forName(name).getDeclaredConstructor().newInstance();

			// 生成したActionインスタンスのexecuteメソッドを呼び出し、
			// リクエストとレスポンスを処理させる
			action.execute(req, res);

		} catch (Exception e) {
			// 例外発生時はスタックトレースをコンソールに表示
			e.printStackTrace();
			// エラーページ（error.jsp）にフォワードしてエラー画面を表示
			req.getRequestDispatcher("/error.jsp").forward(req, res);
		}
	}

	// POSTリクエストはGETの処理に委譲する（GETとPOSTで同じ処理を行う）
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}

package scoremanager.main; // パッケージ宣言

import java.io.IOException; // 入出力例外を扱うためのインポート
import java.sql.Connection; // データベース接続のためのインポート
import java.sql.DriverManager; // JDBCドライバ管理のためのインポート
import java.sql.PreparedStatement; // プリペアードステートメントのためのインポート
import java.sql.SQLException; // SQL例外を扱うためのインポート

import javax.servlet.ServletException; // サーブレット例外を扱うためのインポート
import javax.servlet.annotation.WebServlet; // WebServletアノテーションのためのインポート
import javax.servlet.http.HttpServlet; // HttpServletクラスを継承するためのインポート
import javax.servlet.http.HttpServletRequest; // HTTPリクエストを扱うためのインポート
import javax.servlet.http.HttpServletResponse; // HTTPレスポンスを扱うためのインポート

/**
 * 科目作成処理を実行するサーブレットクラスです。
 * 確認画面からのデータを受け取り、データベースに科目を登録します。
 */
@WebServlet("/scoremanager/SubjectCreateExecute.action") // または /TeamF/scoremanager/SubjectCreateExecute.action
public class SubjectCreateExecuteAction extends HttpServlet {
    // シリアルバージョンUID（警告を抑制するため）
    private static final long serialVersionUID = 1L;

    // データベース接続設定
    // ※DAOクラス（例: SubjectDao）を使用する場合は、これらの設定はDAO側に移動するのが一般的です。
    //    現在は直接JDBC接続を行うため、ここに定義しています。
    private static final String JDBC_URL = "jdbc:h2:~/yajima"; // H2データベースのURL
    private static final String DB_USER = "sa"; // データベースユーザー名
    private static final String DB_PASS = ""; // データベースパスワード

    /**
     * HTTP POSTリクエストを処理します。
     * 科目のIDと名称を取得し、データベースに新規科目として挿入します。
     * 登録結果に応じて、成功または失敗のJSPページにリダイレクトまたはフォワードします。
     *
     * @param request  HTTPリクエストオブジェクト
     * @param response HTTPレスポンスオブジェクト
     * @throws ServletException サーブレットがリクエストを処理できなかった場合
     * @throws IOException      入出力エラーが発生した場合
     */
    @Override // HttpServletのdoPostメソッドをオーバーライドしていることを明示
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // リクエストの文字エンコーディングをUTF-8に設定
        request.setCharacterEncoding("UTF-8");

        // 確認画面から送られてきたデータを取得
        String subjectId = request.getParameter("subjectId");   // 科目ID
        String subjectName = request.getParameter("subjectName"); // 科目名

        // データベース接続およびステートメントのための変数
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // --- データベース接続処理 ---
            // JDBCドライバを読み込み
            Class.forName("org.h2.Driver");
            // データベースへのコネクションを確立
            conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

            // --- SQL実行処理 ---
            // SQL文（INSERT文）を定義
            // SUBJECTテーブルにSUBJECT_IDとSUBJECT_NAMEを挿入
            String sql = "INSERT INTO SUBJECT (CD, NAME) VALUES (?, ?)"; // テーブル名、カラム名が一致しているか確認
            pstmt = conn.prepareStatement(sql);
            // プレースホルダに値をセット
            pstmt.setString(1, subjectId);
            pstmt.setString(2, subjectName);

            // SQLを実行し、更新された行数を取得
            int result = pstmt.executeUpdate();

            // --- 結果に応じた処理 ---
            if (result > 0) {
                // 登録成功 → 完了画面へリダイレクト
                // ContextPathを考慮して絶対パスでリダイレクト
                response.sendRedirect(request.getContextPath() + "/subject/subject_success.jsp");
            } else {
                // 登録失敗 → 確認画面（エラーメッセージ付き）へフォワード
                request.setAttribute("errorMessage", "登録に失敗しました。指定された科目IDは既に存在するか、入力内容に誤りがあります。");
                request.getRequestDispatcher("/subject/subject_confirm.jsp").forward(request, response);
            }

        } catch (SQLException | ClassNotFoundException e) {
            // データベース関連、またはJDBCドライバが見つからない場合の例外処理
            e.printStackTrace(); // 開発中はスタックトレースを出力して詳細を確認
            request.setAttribute("errorMessage", "データベースエラーが発生しました: " + e.getMessage());
            request.getRequestDispatcher("/subject/subject_confirm.jsp").forward(request, response);

        } finally {
            // --- リソースのクローズ処理 ---
            // 使用したリソース（PreparedStatement, Connection）を必ずクローズ
            try {
                if (pstmt != null) {
                    pstmt.close(); // PreparedStatementをクローズ
                }
                if (conn != null) {
                    conn.close(); // Connectionをクローズ
                }
            } catch (SQLException e) {
                e.printStackTrace(); // クローズ時の例外も出力
            }
        }
    }
}
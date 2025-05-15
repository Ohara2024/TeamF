package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Protect specific URLs that require login
// Adjust urlPatterns based on which pages/servlets need login
@WebFilter({"/subject/*", "/menu.jsp", "/subject_list.jsp"}) // 例: 科目関連パス、メニューページ、一覧ページを保護
public class LoginRequiredFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false); // セッションが存在しなければ作成しない

        // ログイン状態のチェック (セッションがあり、かつユーザー属性が存在するか)
        // セッション属性名 "user" は LoginExecuteAction と合わせる
        if (session != null && session.getAttribute("user") != null) {
            // ログイン済み -> リクエストを続行
            chain.doFilter(request, response);
        } else {
            // 未ログイン -> ログインページへリダイレクト
            // ただし、ログインページやログイン処理へのアクセスは許可しないと無限リダイレクトになる
            String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

            // ログイン関連のパスはフィルタリング対象外とする
            if (path.equals("/login.jsp") || path.startsWith("/login")) {
                 chain.doFilter(request, response); // ログインページまたはログイン処理へのアクセスを許可
            } else {
                 // それ以外の未ログイン状態でのアクセスはログインページへリダイレクト
                 httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
            }
        }
    }

    public void init(FilterConfig fConfig) throws ServletException {
        // 初期化処理
    }

    public void destroy() {
        // 終了処理
    }
}
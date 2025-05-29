package tool;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scoremanager.LogOutAction;
import scoremanager.LoginAction;
import scoremanager.MenuAction;
import scoremanager.TestRegistAction;

@WebServlet("/FrontController")
public class FrontController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if ("login".equals(action)) {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        } else if ("menu".equals(action)) {
            new MenuAction().execute(req, resp);
        } else if ("logout".equals(action)) {
            new LogOutAction().execute(req, resp);
        } else if ("executeLogin".equals(action)) {
            new LoginAction().execute(req, resp); // LoginActionを経由
        } else if ("TestRegist".equals(action)) {
            try {
                new TestRegistAction().execute(req, resp);
            } catch (Exception e) {
                throw new ServletException(e); // またはログ出力してエラーページへ
            }
        }else {
            req.getRequestDispatcher("/login.jsp").forward(req, resp); // デフォルトはログイン画面
        }
    }
}
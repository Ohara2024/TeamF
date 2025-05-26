package tool;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        processRequest(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        processRequest(req, res);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String actionName = req.getParameter("action");
        if (actionName == null || actionName.isEmpty()) {
            actionName = "Default"; // デフォルトアクション名
        }

        // パッケージ名やActionクラス名を決める
        String className;
        if ("StudentList".equals(actionName)) {
            className = "scoremanager." + actionName + "Action";
        } else {
            className = "scoremanager.main." + actionName + "Action";
        }

        try {
            Class<?> actionClass = Class.forName(className);
            Object actionInstance = actionClass.getDeclaredConstructor().newInstance();

            if (actionInstance instanceof Action) {
                Action action = (Action) actionInstance;
                action.execute(req, res);
            } else {
                res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Actionクラスが不正です");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_NOT_FOUND, "指定されたアクションが存在しません");
        } catch (Exception e) {
            e.printStackTrace();
            res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "内部エラーが発生しました");
        }
    }
}

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

        String actionName = request.getParameter("action");

        if (actionName == null || actionName.isEmpty()) {
            actionName = "Default"; // 例: DefaultActionを使う
        }

        String className = "scoremanager.main." + actionName + "Action";

        try {
            Class<?> actionClass = Class.forName(className);
            Object actionInstance = actionClass.getDeclaredConstructor().newInstance();

            if (actionInstance instanceof Action) {
                Action action = (Action) actionInstance;
                action.execute(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        "Actionクラスが不正です");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "指定されたアクションが存在しません");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "内部エラーが発生しました");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

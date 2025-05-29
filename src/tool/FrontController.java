package tool;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scoremanager.StudentList_Action;

@WebServlet("/front")
public class FrontController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String command = request.getParameter("command");
        if (command == null || command.isEmpty()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "commandパラメータが指定されていません");
            return;
        }

        // commandで振り分け
        if ("student_list".equals(command)) {
            StudentList_Action action = new StudentList_Action();
            try {
                action.execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException(e);
            }
        } else {
            // 旧方式のactionパラメータでの呼び出しもサポート（任意）
            String actionName = request.getParameter("action");
            if (actionName != null && !actionName.isEmpty()) {
                String className = "scoremanager." + actionName + "Action";
                try {
                    Class<?> clazz = Class.forName(className);
                    Object actionInstance = clazz.getDeclaredConstructor().newInstance();

                    if (actionInstance instanceof Action) {
                        ((Action) actionInstance).execute(request, response);
                        return;
                    } else {
                        response.sendError(500, "Actionクラスが不正です");
                        return;
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    response.sendError(404, "指定されたアクションが存在しません");
                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendError(500, "内部エラーが発生しました");
                }
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "不明なコマンドです");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

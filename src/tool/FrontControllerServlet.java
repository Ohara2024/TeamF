package tool;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scoremanager.StudentListAction;
import scoremanager.StudentUpdateAction;

@WebServlet("*.action")
public class FrontControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String uri = request.getRequestURI();
        String actionName = uri.substring(uri.lastIndexOf("/") + 1);

        Action action = null;

        switch (actionName) {
            case "StudentListAction.action":
                action = (Action) new StudentListAction();
                break;
            case "StudentUpdate.action":
                action = new StudentUpdateAction();
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
        }

        try {
            action.execute(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}

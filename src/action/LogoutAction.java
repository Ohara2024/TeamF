package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false); // Get existing session, don't create
        if (session != null) {
            session.invalidate(); // Invalidate the session
        }
        // Redirect to the login page (using redirect: prefix)
        return "redirect:login.jsp";
    }
}
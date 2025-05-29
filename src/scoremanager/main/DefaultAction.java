package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class DefaultAction implements Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        res.setContentType("text/html; charset=UTF-8");
        res.getWriter().println("<html><body><h1>DefaultAction executed.</h1></body></html>");
    }
}

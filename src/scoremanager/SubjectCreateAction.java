package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher; // Assuming Teacher bean exists
// import tool.Action; // Your base Action class
import tool.Action;

public class SubjectCreateAction extends Action {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        if (teacher == null) {
            // Redirect to login if user is not in session
            return "logout.jsp"; // Or your login page
        }

        // No initial data to load for a fresh subject creation form usually,
        // unless you have defaults or specific lists to populate.
        // Errors and previously entered values will be set by SubjectCreateExecuteAction if redirection occurs.

        return "subject_create.jsp";
    }
}
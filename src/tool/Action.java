package tool;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Action extends HttpServlet {
    public abstract void execute(
    		HttpServletRequest request, HttpServletResponse response
    		) throws Exception;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            execute(req, resp);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            execute(req, resp);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }
}

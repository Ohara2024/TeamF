package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

// 共通のActionインターフェースがある前提です
public class DefaultAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    if (request == null) {
	        System.out.println("request is null!");
	    } else {
	        System.out.println("request is OK!");
	    }

	    request.getRequestDispatcher("WEB-INF/views/index.jsp").forward(request, response);

	}

 }

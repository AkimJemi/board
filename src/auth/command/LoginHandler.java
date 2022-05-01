package auth.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.LoginService;
import auth.service.User;
import mvc.command.CommandHandler;

public class LoginHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/loginForm.jsp";
	private LoginService loginService = new LoginService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res); // 오류 발견 *processSubmit
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		String id = trim(req.getParameter("id"));
		String password = trim(req.getParameter("password"));
		boolean fp = false;
		if (req.getParameter("freepass") != null) {
			fp = true;
		}
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);

		if (id == null || id.isEmpty()) {
			errors.put("id", Boolean.TRUE);
		}

		if (password == null || password.isEmpty()) {
			errors.put("password", Boolean.TRUE);
		}

		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
	
	

		try {
			User user = loginService.login(id, password);
			req.getSession().setAttribute("authUser", user);
			if (fp) {
//				return "/article/list.do"; // 안되는 이유는?
				res.sendRedirect(req.getContextPath() + "/article/list.do");
			}
			else {
			res.sendRedirect(req.getContextPath() + "/index.jsp");
			}
			return null;
		} catch (Exception e) {
			errors.put("idOrPwNotMatch", Boolean.TRUE);
			return FORM_VIEW;
		}
		

	}

	private String trim(String str) {
		return str == null ? null : str.trim();
	}

}

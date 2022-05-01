package member.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.service.JoinRequest;
import member.service.JoinService;
import mvc.command.CommandHandler;

public class JoinHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/joinForm.jsp";
	private JoinService joinService = new JoinService();

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
		JoinRequest jr = new JoinRequest(req.getParameter("id"), req.getParameter("name"), req.getParameter("password"),
				req.getParameter("confirmPassword"));
//		JoinRequest jr = new JoinRequest();
//		jr.setId(req.getParameter("id"));
//		jr.setName(req.getParameter("name"));
//		jr.setPassword("password");
//		jr.setConfirmPassword("confirmPassword");

		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		jr.validate(errors);
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			joinService.join(jr);
			return "WEB-INF/view/joinSuccess.jsp";
		} catch (Exception e) {
			errors.put("duplicateId", Boolean.TRUE);
			return FORM_VIEW;
		}

	}

}

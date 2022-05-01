package article.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.model.Writer;
import article.service.WriteArticleService;
import article.service.WriteRequest;
import auth.service.User;
import mvc.command.CommandHandler;

public class WriteArticleHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/newArticleForm.jsp";
	private WriteArticleService writeService = new WriteArticleService();

	public WriteArticleHandler() {
	}

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
		int num = 1;
		boolean freepass = false;
		int pageNo=1;
		if (req.getParameter("freepass") != null && req.getParameter("num") != null && req.getParameter("pageNo") !=null) {
			pageNo = Integer.parseInt(req.getParameter("pageNo"));
			freepass = true;
			num = Integer.parseInt(req.getParameter("num"));
		}
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		User user = (User) req.getSession().getAttribute("authUser");
		WriteRequest wr = createWriteRequest(user, req);
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		if (freepass) {

			int newArticleNo = writeService.write(wr, num);
			return "list.do?pageNo="+pageNo;
		} else {
			int newArticleNo = writeService.write(wr, num);
			req.setAttribute("newArticleNo", newArticleNo);
			return "/WEB-INF/view/newArticleSuccess.jsp";
		}

	}

	private WriteRequest createWriteRequest(User user, HttpServletRequest req) {
		return new WriteRequest(new Writer(user.getId(), user.getName()), req.getParameter("title"),
				req.getParameter("content"));
	}
}

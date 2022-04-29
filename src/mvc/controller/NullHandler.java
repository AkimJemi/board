package mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

public class NullHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		System.out.println("[       NullHandler.class -  process()      ]");
		res.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}

}

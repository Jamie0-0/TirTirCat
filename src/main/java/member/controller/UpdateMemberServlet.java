package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import member.service.MemberService;
import member.service.MemberServiceImpl;

@WebServlet("/updatemember")
public class UpdateMemberServlet extends HttpServlet {

	private static final long serialVersionUID = 3851100788769669310L;
	private MemberService service;

	@Override
	public void init() throws ServletException {
		service = new MemberServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json;charset=utf-8");
		Gson gson = new Gson();

		String message = gson.toJson("");

		boolean update = service.edit(req.getReader());

		if (update) {
			message = "{\"status\": \"success\"}";
		} else {
			message = "{\"status\": \"error\"}";
		}
		resp.getWriter().write(message);
	}
}

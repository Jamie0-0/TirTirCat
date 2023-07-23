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
import member.vo.Member;

@WebServlet("/member/controller/updatemember")
public class UpdateMemberServlet extends HttpServlet {
	private MemberService service;

	@Override
	public void init() throws ServletException {
		service = new MemberServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		Member member = gson.fromJson(req.getReader(), Member.class);
		String message = gson.toJson("");
		
		boolean update = service.edit(member);
		System.out.println(member.getEmail());
		if(update) {
			 message = "{\"status\": \"success\"}";
		}
		else {
			message = "{\"status\": \"error\"}";
		}
		 resp.getWriter().write(message);
	}
}

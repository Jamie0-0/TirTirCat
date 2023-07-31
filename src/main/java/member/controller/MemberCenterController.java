package member.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import member.dao.MemberDao;
import member.dao.MemberDaoImpl;
import member.service.MemberService;
import member.service.MemberServiceImpl;
import member.vo.Member;

@WebServlet("/memberCenterController")
public class MemberCenterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService service;
	private MemberDao memberDao;

	@Override
	public void init() throws ServletException {
		service = new MemberServiceImpl();
		memberDao = new MemberDaoImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		String base64 = "";
		Gson gson = new Gson();
		Member member = gson.fromJson(request.getReader(), Member.class);
		String message = gson.toJson("");
		if (session != null && session.getAttribute("username") != null) {
			// 從 Session 中獲取會員的 name
			String username = (String) session.getAttribute("username");
			// 從數據庫中獲取會員的當前資訊
			member = memberDao.selectByUserName(username);
			session.setAttribute("username", username);
			String about = member.getAbout();
			if(member.getuPic() != null) {
				base64 = Base64.getEncoder().encodeToString(member.getuPic());
			}else {
				base64 = "";
			}
			message = "{\"status\": \"true\",\"about\":" + gson.toJson(about) + ",\"username\":"+gson.toJson(username)+ ",\"uPic\":"+gson.toJson(base64)+"}";
			response.getWriter().write(message);
		}
	}

}

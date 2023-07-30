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

@WebServlet("/showmember")
public class ShowMemberController extends HttpServlet {
	private MemberService service;
	private MemberDao memberDao;

	@Override
	public void init() throws ServletException {
		service = new MemberServiceImpl();
		memberDao = new MemberDaoImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json;charset=utf-8");
		Gson gson = new Gson();
		String base64 = "";
		Member member = gson.fromJson(req.getReader(), Member.class);
		String message = gson.toJson("");
		if (session != null && session.getAttribute("username") != null) {
			// 從數據庫中獲取會員的當前資訊
			String username = (String) session.getAttribute("username");
			member = memberDao.selectByUserName(username);
			String email = member.getEmail();
			String phone = member.getPhone();
			String password = member.getPassword();
			Date birth = member.getBirth();
			if(member.getuPic() != null) {
				base64 = Base64.getEncoder().encodeToString(member.getuPic());
			}else {
				base64 = "";
			}
			
			message = "{\"status\": \"true\",\"email\":" + gson.toJson(email) + ", \"username\":"
					+ gson.toJson(username) + ",\"password\":" + gson.toJson(password) + ", \"phone\":"
					+ gson.toJson(phone) + ", \"birth\":" + gson.toJson(birth) + ", \"uPic\":"+gson.toJson(base64)+"}";
			resp.getWriter().write(message);
		}
		else {
			message = "{\"status\": \"error\"}";
			resp.getWriter().write(message);
		}
	}
}

package member.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.service.MemberService;
import member.service.MemberServiceImpl;

@WebServlet("/member/controller/MemberController")
public class MemberController extends HttpServlet {
	private MemberService service;
	

	@Override
	public void init() throws ServletException {
		service = new MemberServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession(true);
//	        // 获取 session 创建时间
//	        Date createTime = new Date(session.getCreationTime());
//	        // 获取该网页的最后一次访问时间
//	        Date lastAccessTime = new Date(session.getLastAccessedTime());
//	         
//	        //设置日期输出的格式  
//	        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			req.setCharacterEncoding("UTF-8");
			
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			String username = req.getParameter("username");
			String isAuthenticated = service.login(email, password);
			if(isAuthenticated != null) {				
				session.setAttribute("email", email);
				session.setAttribute("password", password);
				session.setAttribute("username", isAuthenticated);
				resp.getWriter().write("true");
			}
			else {
				resp.getWriter().write("false");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}

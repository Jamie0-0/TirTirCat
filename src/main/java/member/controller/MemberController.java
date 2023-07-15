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
		try {
			req.setCharacterEncoding("UTF-8");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			if(email != null && password != null) {
				resp.sendRedirect("/TirTirCat/member_center.html");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession session = req.getSession(true);
	        // 获取 session 创建时间
	        Date createTime = new Date(session.getCreationTime());
	        // 获取该网页的最后一次访问时间
	        Date lastAccessTime = new Date(session.getLastAccessedTime());
	         
	        //设置日期输出的格式  
	        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			req.setCharacterEncoding("UTF-8");
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			if(email != null && password != null) {
				
				session.setAttribute("email", email);
				session.setAttribute("password", password);
//				resp.sendRedirect("/member_center.html");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean isMember(String email, String password) {	
		
		return email != null && !email.isEmpty() && email.equals("member123");
	}
}

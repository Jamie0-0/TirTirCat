package member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import member.service.MemberService;
import member.service.MemberServiceImpl;
import member.vo.Member;

@WebServlet("/loginController")
public class LoginController extends HttpServlet {
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

			req.setCharacterEncoding("UTF-8");
			resp.setContentType("application/json;charset=utf-8");
			Gson gson = new Gson();
			Member member = gson.fromJson(req.getReader(), Member.class);

//			String email = member.getEmail();
//			String password = member.getPassword();

			String message = gson.toJson("");
			member = service.login(member);
//			System.out.println(member.getUid());
			List<String> errorMsg = service.getErrorMsgs();
			if (member != null) {
				session.setAttribute("email", member.getEmail());
				session.setAttribute("password", member.getPassword());
				session.setAttribute("username", member.getName());
				Integer uid = member.getUid();
				message = "{\"status\": \"true\",\"errorMsgs\": " + gson.toJson(errorMsg) + ",\"uid\": "
						+ gson.toJson(uid) + "}";
				resp.getWriter().write(message);
//				System.out.println(message);
			} else {
				message = "{\"status\": \"error\",\"errorMsgs\": " + gson.toJson(errorMsg) + "}";
				resp.getWriter().write(message);
//				System.out.println(gson.toJson(errorMsg));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

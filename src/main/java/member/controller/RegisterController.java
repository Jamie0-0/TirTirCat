package member.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import member.service.MemberService;
import member.service.MemberServiceImpl;
import member.vo.Member;

@WebServlet("/member/controller/registerController")
public class RegisterController extends HttpServlet {
	private MemberService service;

	public void init() throws ServletException {
		service = new MemberServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		Member member = gson.fromJson(req.getReader(), Member.class);

		String email = member.getEmail();
	    String password = member.getPassword();
	    String name = member.getName();
	    String phone = member.getPhone();
	    String gender = member.getGender();
	    Date birth = null;
		try {
			birth = (Date)(member.getBirth());
		} catch (IllegalArgumentException  e) {
			e.printStackTrace();
		}
	    String addr = member.getAddr();

		boolean isValid = service.validate(email, password, name, phone, gender, birth, addr);
		String message = gson.toJson("");
		List<String> errorMsg = service.getErrorMsgs();
		if (!isValid) {			
			message = "{\"status\": \"error\",\"errorMsgs\": " + gson.toJson(errorMsg) + "}";
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(message);
			return;
		}
		message = "{\"status\": \"success\"}";
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(message);
	}
}

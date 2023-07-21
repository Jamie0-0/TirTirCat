package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

@WebServlet("/member/controller/memberCenterController")
public class memberCenterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		String username = (String) session.getAttribute("username");

		System.out.println(username);
		Gson gson = new Gson();
		String json = gson.toJson(username);
		// 告訴前端response為json格式
		response.setContentType("application/json");
		// 設定編碼
		response.setCharacterEncoding("UTF-8");

		// 寫出
		response.getWriter().write(json);
	}

}

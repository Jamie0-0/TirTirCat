package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@WebServlet("/showusername")
public class ShowUsernameController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=utf-8");
		Gson gson = new Gson();

		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");

		JsonObject responseJson = new JsonObject();
		responseJson.addProperty("status", "success");
		responseJson.addProperty("username", username);

		resp.getWriter().write(responseJson.toString());
	}
}

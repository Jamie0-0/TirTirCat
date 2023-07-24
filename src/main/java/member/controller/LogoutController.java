package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/logout")
public class LogoutController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		String message = gson.toJson("");
		
		if(req.getSession().getAttribute("email") != null) {
			req.getSession().invalidate();
			message = "{\"status\": \"success\"}";
			resp.getWriter().write(message);
			System.out.println(req.getSession().getAttribute("email"));
		}
	}
}

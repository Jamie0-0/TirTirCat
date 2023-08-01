package core.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class LoginFilter extends HttpFilter {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String[] urls = { "/login.html", "/assets/", "/css/", "/sign-up.html", "/loginController",
				"/registerController", "/restigermastercontroller" };
		String url = request.getServletPath();
		String url1 = request.getContextPath() + "/assets";

		System.out.println(url);
		System.out.println(url1);

		HttpSession session = request.getSession();
		Object user = session.getAttribute("username");

		for (String u : urls) {
			if (url.contains(u) || url1.contains(u)) {
				chain.doFilter(request, response);
				return;
			}
		}

		if (user == null) {
			session.setAttribute("loginNotice", true);
//			String ttttt = url1 + "login.html";
			response.sendRedirect(request.getContextPath() + "/" + "login.html");
		} else {
			chain.doFilter(request, response);
		}
	}
}

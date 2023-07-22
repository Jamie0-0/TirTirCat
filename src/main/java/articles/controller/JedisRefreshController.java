package articles.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.support.DaoSupport;

import articles.service.ArticlesService;
import articles.service.ArticlesServiceImpl;

@WebServlet("/refresh")
public class JedisRefreshController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticlesService service;
	
	public void init() throws ServletException {
		service = new ArticlesServiceImpl();
	}
	
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> list = null;
		
		service.jedisRefresh();
	}
}

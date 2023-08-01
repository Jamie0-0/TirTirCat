package gb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import gb.dao.GBDao;
import gb.dao.GbDAOImpl;
import gb.vo.GbAndProductVO;

@WebServlet("/choise")
public class ChoiceServlet extends HttpServlet {

	private static final long serialVersionUID = 5314977473749785269L;

	private GBDao dao;

	@Override
	public void init() throws ServletException {
		dao = new GbDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("application/json; charset=UTF-8");
		
		Gson gson = new Gson();
		
		String how = req.getParameter("how");
		String keywords = req.getParameter("keywords");
		
		System.out.println(how);
		System.out.println(keywords);
		
		List<GbAndProductVO> list = dao.selectByKeyWords(how, keywords);
		System.out.println(list);
		 String message = gson.toJson("");
		 
		 message = "{\"status\":\"true\",\"selectByKeyWords\":" + gson.toJson(list) + "}";
		 resp.getWriter().write(message);
		
		 System.out.println(message);

	}
}

//篩選器servlet
//protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//resp.setContentType("application/json");
//resp.setCharacterEncoding("UTF-8");
//req.setCharacterEncoding("UTF-8");
//
//Gson gson = new Gson();
//
//String how = req.getParameter("how");
//String keywords = req.getParameter("keywords");
//
//var list = service.selectByKeyWords(how, keywords);
//
//String message = gson.toJson("");
//
//message = "{\"status\":\"true\",\"selectByKeyWords\":" + gson.toJson(list) + "}";
//resp.getWriter().write(message);
//
//System.out.println(message);

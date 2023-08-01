package pet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pet.service.PetService;
import pet.service.PetServiceImpl;


@MultipartConfig
@WebServlet("/petcontroller")
public class InsertPetController extends HttpServlet {

	private static final long serialVersionUID = 122L;
	private PetService service;

	@Override
	public void init() throws ServletException {
		service = new PetServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json; charset=utf-8");
		try {
			service.savePets(req.getReader());
			resp.getWriter().write("{\"status\": \"success\"}");
		} catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().write("{\"status\": \"error\"}");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}

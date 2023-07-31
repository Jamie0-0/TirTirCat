package master.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import master.service.MasterService;
import master.vo.Master;

@WebServlet("/restigermastercontroller")
public class RestigerMasterController extends HttpServlet{
	
	private MasterService service;
	
	@Override
	public void init() throws ServletException {
		service = new MasterService();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json;charset=utf-8");
		Gson gson = new Gson();
		
		Master master = gson.fromJson(req.getReader(), Master.class);
		
		
		if(service.insert(master) != null) {
			resp.getWriter().write("success");
		}
		else {
			resp.getWriter().write("false");
		}
	}
}

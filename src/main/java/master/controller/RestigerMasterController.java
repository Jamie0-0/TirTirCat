package master.controller;

import java.io.IOException;
import java.util.List;

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
	
	private static final long serialVersionUID = 8489823284156283021L;
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
		String message = gson.toJson("");
		Master master = gson.fromJson(req.getReader(), Master.class);
		
	
		if(service.insert(master) != null) {
			message="{\"status\": \"success\"}";
			resp.getWriter().write(message);
		}
		else {
			List<String> errorMsg = service.getErrorMsgs();
			message="{\"status\": \"error\",\"errorMsgs\": " + gson.toJson(errorMsg) + "}";
			resp.getWriter().write(message);
		}
	}
}

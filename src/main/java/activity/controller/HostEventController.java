package activity.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import activity.service.TripService;
import activity.service.impl.TripServiceImpl;


@WebServlet("/HostEventController")//註冊Servlet
public class HostEventController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private TripService tripservice;
	
    public HostEventController() {
       
    }
    
    //init()初始化頁面(必寫)，只要不執行生命週期的destroy()，它就永遠都會在
  	@Override
  	public void init() throws ServletException {
  		tripservice = new TripServiceImpl();
  	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String userInput = request.getParameter("userInput");
	        int inputLength = userInput.length();
	        
	        // 將計算結果傳遞給Service層處理
	        tripservice.processUserInput(inputLength);
	}

}

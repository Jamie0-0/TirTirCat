package product.controller;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import product.model.*;
import tools.*;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, // 1MB
    maxFileSize = 1024 * 1024 * 5, // 5MB
    maxRequestSize = 1024 * 1024 * 10 // 10MB
)

public class ProductServlet extends HttpServlet {
	Connection con;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost method is called!");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("action = "+action);
//=============================================================================
//=============================================================================
//=====================================單一查詢==================================
		if ("getOneSearch".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			//接收請求參數 - 輸入格式的錯誤處理
			String str = req.getParameter("p_id");
			if (str == null || (str.trim()).length() == 0) {
				RequestDispatcher failureView = req.getRequestDispatcher("/backEnd/products.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer p_id = Integer.valueOf(str);

			//開始查詢資料
			ProductService proSvc = new ProductService();
			ProductVO productVO = proSvc.getOnePro(p_id);
			if (productVO == null) {
				errorMsgs.add("查無資料，以全部查詢替代");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/backEnd/products.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			ArrayList<ProductVO> list = new ArrayList<>();
			list.add(productVO);

			//查詢完成,準備轉交(Send the Success view)
			req.setAttribute("list", list);
			String url = "/backEnd/products.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

//=============================================================================
//=============================================================================
//==================================進入修改頁面=================================
		if ("getUpdate".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			//接收請求參數
			Integer p_id = Integer.valueOf(req.getParameter("p_id"));

			//開始查詢資料
			ProductService proSvc = new ProductService();
			ProductVO proVO = proSvc.getOnePro(p_id);

			// 將位元資料流轉換為 Base64 編碼
			String p1 = Base64.getEncoder().encodeToString(proVO.getP_pic_one());
			String p2 = Base64.getEncoder().encodeToString(proVO.getP_pic_two());
			String p3 = Base64.getEncoder().encodeToString(proVO.getP_pic_three());
			String p4 = Base64.getEncoder().encodeToString(proVO.getP_pic_four());

			// 將 Base64 編碼的圖片位元資料流傳遞給 JSP 頁面
			req.setAttribute("p1", p1);
			req.setAttribute("p2", p2);
			req.setAttribute("p3", p3);
			req.setAttribute("p4", p4);
			
			//查詢完成,準備轉交(Send the Success view)
			req.setAttribute("proVO", proVO);
			String url = "/backEnd/update-product.jsp";
//			String url = "/backEnd/ttttttt.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

//=============================================================================
//=============================================================================
//==================================修改頁面遞出請求==============================
		if ("update".equals(action)) {
			System.out.println("servlet1111111111111111111111");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			//自製檢查程式
			Tools t = new Tools();
			
			//商品編號一定正確故不檢查
			Integer p_id = Integer.valueOf(req.getParameter("p_id"));

			//商品名稱 - 錯誤處理
			String p_name = req.getParameter("p_name");
			String errMsg = "";
			if (p_name == null || p_name.trim().length() == 0) {
				p_name = "";
				errorMsgs.add("商品名稱: 請勿空白");
			} else {
				errMsg = t.check(p_name,"word");
				if(!errMsg.equals("ok")) {
					errorMsgs.add("商品名稱:"+errMsg);
				}
			}

			//商品價格 - 錯誤處理
			String p_price = req.getParameter("p_price");
			errMsg = "";
			if (p_price == null || p_price.trim().length() == 0) {
				p_price = "0";
				errorMsgs.add("商品價格: 請勿空白");
			} else {
				errMsg = t.check(p_price,"num");
				if(!errMsg.equals("ok")) {
					errorMsgs.add("商品價格:"+errMsg);
				}
			}
			
			//商品數量 - 錯誤處理
			String p_stock = req.getParameter("p_stock");
			errMsg = "";
			if (p_stock == null || p_stock.trim().length() == 0) {
				p_stock = "0";
				errorMsgs.add("商品數量: 請勿空白");
			} else {
				errMsg = t.check(p_stock,"num");
				if(!errMsg.equals("ok")) {
					errorMsgs.add("商品數量:"+errMsg);
				}
			}

			Integer p_type = Integer.parseInt(req.getParameter("p_type"));
			Integer p_status = Integer.parseInt(req.getParameter("p_status"));
			Integer p_class = Integer.parseInt(req.getParameter("p_class"));
			String p_des = req.getParameter("p_des");
			System.out.println("-============"+p_des);
			
//			Part filePart1 = req.getPart("p_pic_one");
//			Part filePart2 = req.getPart("p_pic_two");
//			Part filePart3 = req.getPart("p_pic_three");
//			Part filePart4 = req.getPart("p_pic_four");
//			
//			InputStream filecontent1 = filePart1.getInputStream();
//			byte[] p_pic_one = new byte[filecontent1.available()];
//			filecontent1.read(p_pic_one);
//			filecontent1.close();
//			
//			InputStream filecontent2 = filePart2.getInputStream();
//			byte[] p_pic_two = new byte[filecontent2.available()];
//			filecontent2.read(p_pic_two);
//			filecontent2.close();
//			
//			InputStream filecontent3 = filePart3.getInputStream();
//			byte[] p_pic_three = new byte[filecontent3.available()];
//			filecontent3.read(p_pic_three);
//			filecontent3.close();
//			
//			InputStream filecontent4 = filePart4.getInputStream();
//			byte[] p_pic_four = new byte[filecontent4.available()];
//			filecontent4.read(p_pic_four);
//			filecontent4.close();

			ProductVO proVO = new ProductVO();
			proVO.setP_id(p_id);
			proVO.setP_name(p_name);
			proVO.setP_price(Integer.parseInt(p_price));
			proVO.setP_stock(Integer.parseInt(p_stock));
			proVO.setP_type(p_type);
			proVO.setP_status(p_status);
			proVO.setP_class(p_class);
			proVO.setP_des(p_des);
//			proVO.setP_pic_one(p_pic_one);
//			proVO.setP_pic_two(p_pic_two);
//			proVO.setP_pic_three(p_pic_three);
//			proVO.setP_pic_four(p_pic_four);


			if (!errorMsgs.isEmpty()) {
				System.out.println("servletBBBBBBBBBBBBBBBBBBBBBBB");
				req.setAttribute("proVO", proVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/backEnd/update-product.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			ProductService proSvc = new ProductService();
//			proVO = proSvc.updatePro(p_name,
//					Integer.parseInt(p_price),Integer.parseInt(p_stock),p_type,
//					p_class,p_des,p_status,p_pic_one,
//					p_pic_two,p_pic_three,p_pic_four);
			System.out.println("servlet222222");
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("proVO", proVO);
			String url = "/backEnd/products.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}


		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String p_name = req.getParameter("p_name");
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (p_name == null || p_name.trim().length() == 0) {
				errorMsgs.add("員工姓名: 請勿空白");
			} else if (!p_name.trim().matches(enameReg)) {
				errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String job = req.getParameter("job").trim();
			if (job == null || job.trim().length() == 0) {
				errorMsgs.add("職位請勿空白");
			}

			java.sql.Date hiredate = null;
			try {
				hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
			} catch (IllegalArgumentException e) {
				hiredate = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}

			Double sal = null;
			try {
				sal = Double.valueOf(req.getParameter("sal").trim());
			} catch (NumberFormatException e) {
				sal = 0.0;
				errorMsgs.add("薪水請填數字.");
			}

			Double comm = null;
			try {
				comm = Double.valueOf(req.getParameter("comm").trim());
			} catch (NumberFormatException e) {
				comm = 0.0;
				errorMsgs.add("獎金請填數字.");
			}

			Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());

			ProductVO proVO = new ProductVO();
			proVO.setP_name(p_name);
//			empVO.setJob(job);
//			empVO.setHiredate(hiredate);
//			empVO.setSal(sal);
//			empVO.setComm(comm);
//			empVO.setDeptno(deptno);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("proVO", proVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/emp/addEmp.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			ProductService proSvc = new ProductService();
//			proVO = proSvc.addPro(p_name, job, hiredate, sal, comm, deptno);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/emp/listAllEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

	}
}

package master.controller;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import static core.util.CommonUtil.writePojo2Json;
import static core.util.Constants.GSON;

import master.model.*;
import tools.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@WebServlet("/mas.do")
public class MasterServlet extends HttpServlet {

	Connection con;

//    private String m_name; 廠商名稱
//    private String m_pwd;  密碼
//    private String m_gui;  統編
//	private String m_bank_name; 銀行行號
//	private String m_bank_id;   銀行帳號
//	private String m_address;   廠商地址
//	private String m_man_id;    身分證
//	private String m_man_name;  聯絡人名稱
//	private String m_email;     信箱
//	private String m_phone;     電話
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//=============================================================================
//=============================================================================
//==================================修改頁面遞出請求==============================
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			// 自製檢查程式
			Tools t = new Tools();

			// 商品編號一定正確故不檢查
			Integer p_id = Integer.valueOf(req.getParameter("p_id"));

			// 商品名稱 - 錯誤處理
			String p_name = req.getParameter("p_name");
			String errMsg = "";
			if (p_name == null || p_name.trim().length() == 0) {
				p_name = "";
				errorMsgs.add("商品名稱: 請勿空白");
			} else {
				errMsg = t.check(p_name, "word");
				if (!errMsg.equals("ok")) {
					errorMsgs.add("商品名稱:" + errMsg);
				}
			}

			// 商品價格 - 錯誤處理
			String p_price = req.getParameter("p_price");
			errMsg = "";
			if (p_price == null || p_price.trim().length() == 0) {
				p_price = "0";
				errorMsgs.add("商品價格: 請勿空白");
			} else {
				errMsg = t.check(p_price, "num");

				if (!errMsg.equals("ok")) {
					errorMsgs.add("商品價格:" + errMsg);
					p_price = "0";
				}
			}

			// 商品數量 - 錯誤處理
			String p_stock = req.getParameter("p_stock");
			errMsg = "";
			if (p_stock == null || p_stock.trim().length() == 0) {
				p_stock = "0";
				errorMsgs.add("商品數量: 請勿空白");
			} else {
				errMsg = t.check(p_stock, "num");
				if (!errMsg.equals("ok")) {
					errorMsgs.add("商品數量:" + errMsg);
					p_stock = "0";
				}
			}

			Integer p_type = Integer.parseInt(req.getParameter("p_type"));
			Integer p_status = Integer.parseInt(req.getParameter("p_status"));
			Integer p_class = Integer.parseInt(req.getParameter("p_class"));
			String p_des = req.getParameter("p_des");

			Part filePart1 = req.getPart("p_pic_one");
			Part filePart2 = req.getPart("p_pic_two");
			Part filePart3 = req.getPart("p_pic_three");
			Part filePart4 = req.getPart("p_pic_four");

			InputStream filecontent1 = filePart1.getInputStream();
			byte[] p_pic_one = new byte[filecontent1.available()];
			filecontent1.read(p_pic_one);

			InputStream filecontent2 = filePart2.getInputStream();
			byte[] p_pic_two = new byte[filecontent2.available()];
			filecontent2.read(p_pic_two);

			InputStream filecontent3 = filePart3.getInputStream();
			byte[] p_pic_three = new byte[filecontent3.available()];
			filecontent3.read(p_pic_three);

			InputStream filecontent4 = filePart4.getInputStream();
			byte[] p_pic_four = new byte[filecontent4.available()];
			filecontent4.read(p_pic_four);


//			ProductVO proVO = new ProductVO.Builder()
//												.setP_id(p_id)
//												.setP_name(p_name)
//												.setP_price(Integer.parseInt(p_price))
//												.setP_stock(Integer.parseInt(p_stock))
//												.setP_type(p_type)
//												.setP_status(p_status)
//												.setP_class(p_class)
//												.setP_des(p_des)
//												.build();
//
//			ProductService proSvc2 = new ProductService();
//			ProductVO productVO1 = proSvc2.getOnePro(p_id);
//
//			if (!errorMsgs.isEmpty()) {
//				filecontent4.close();
//				filecontent3.close();
//				filecontent2.close();
//				filecontent1.close();
//				String p1 = Base64.getEncoder().encodeToString(productVO1.getP_pic_one());
//				String p2 = Base64.getEncoder().encodeToString(productVO1.getP_pic_two());
//				String p3 = Base64.getEncoder().encodeToString(productVO1.getP_pic_three());
//				String p4 = Base64.getEncoder().encodeToString(productVO1.getP_pic_four());
//
//				// 將 Base64 編碼的圖片位元資料流傳遞給 JSP 頁面
//				req.setAttribute("p1", p1);
//				req.setAttribute("p2", p2);
//				req.setAttribute("p3", p3);
//				req.setAttribute("p4", p4);
//
//				req.setAttribute("proVO", proVO);
//				RequestDispatcher failureView = req.getRequestDispatcher("/backEnd/update-product.jsp");
//				failureView.forward(req, res);
//				return; // 程式中斷
//			}
//
//			//開始修改資料
//			ProductService proSvc = new ProductService();
//			proVO = proSvc.updatePro(p_name, Integer.parseInt(p_price), Integer.parseInt(p_stock), p_type, p_class,
//					p_des, p_status, p_id, p_pic_one, p_pic_two, p_pic_three, p_pic_four);
//
//			//修改完成,準備轉交
//			ProductService proSvc1 = new ProductService();
//			List productVO = proSvc1.getOnePro2(p_id, p_status, p_class);
//			ArrayList<ProductVO> list = (ArrayList<ProductVO>) productVO;
//
//			// 查詢完成,準備轉交
//			req.setAttribute("list", list);
//
//			String url = "/backEnd/products.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
//
//			filecontent4.close();
//			filecontent3.close();
//			filecontent2.close();
//			filecontent1.close();
		}
//=============================================================================
//=============================================================================
//==================================修改頁面遞出請求==============================
		if ("getOneSearch".equals(action)) {
			Gson gson = new Gson();
			MasterVO master = gson.fromJson(req.getReader(), MasterVO.class);
			Integer m_id = Integer.valueOf(req.getParameter("m_id"));

			MasterService masSvc = new MasterService();

			MasterVO mVO = masSvc.getOnePro(m_id);
			System.out.println("77777action======"+action);
			MasterVO2 masterVO2 = new MasterVO2.Builder()
											.setM_id(mVO.getM_id())
											.setM_name(mVO.getM_name())
											.setM_pwd(mVO.getM_pwd())
											.setM_gui(mVO.getM_gui())
											.setM_bank_name(mVO.getM_bank_name())
											.setM_bank_id(mVO.getM_bank_id())
											.setM_address(mVO.getM_address())
											.setM_man_id(mVO.getM_man_id())
											.setM_man_name(mVO.getM_man_name())
											.setM_email(mVO.getM_email())
											.setM_phone(mVO.getM_phone())
											.build();
			writePojo2Json(res, GSON.toJson(masterVO2));
			
			
			
			
			
			
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			// 接收請求參數 - 輸入格式的錯誤處理
//			String str = req.getParameter("m_id");
//			if (str == null || (str.trim()).length() == 0) {
//				str = "0";
//			}
//
//			Integer p_id = Integer.valueOf(str);
//			Integer p_status = Integer.valueOf(req.getParameter("p_status"));
//			Integer p_class = Integer.valueOf(req.getParameter("p_class"));
//
//			if (p_id == 0 && p_status == 0 && p_class == 0) {
//				errorMsgs.add("全部查詢");
//			}
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/backEnd/products.jsp");
//				failureView.forward(req, res);
//				return;// 程式中斷
//			}
//
//			// 開始查詢資料
//			ProductService proSvc = new ProductService();
//			List productVO = proSvc.getOnePro2(p_id, p_status, p_class);
//			if (productVO == null) {
//				errorMsgs.add("查無資料，以全部查詢替代");
//			}
//
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req.getRequestDispatcher("/backEnd/products.jsp");
//				failureView.forward(req, res);
//				return;// 程式中斷
//			}
//
//			// 查詢完成,準備轉交
//			ArrayList<ProductVO> list = (ArrayList<ProductVO>) productVO;
//			req.setAttribute("list", list);
//			String url = "/backEnd/products.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
		}
	}
}

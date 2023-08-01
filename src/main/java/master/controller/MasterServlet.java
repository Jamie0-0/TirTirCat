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

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//=============================================================================
//=============================================================================
//==================================遞出修改請求==================================
		System.out.println("action====="+action);
		if ("update".equals(action)) {
			//取得網頁傳來資資料
			Integer m_id = Integer.valueOf(req.getParameter("m_id"));
			String m_name = String.valueOf(req.getParameter("m_name"));
			String m_gui = String.valueOf(req.getParameter("m_gui"));
			String m_bank_id = String.valueOf(req.getParameter("m_bank_id"));
			String m_address = String.valueOf(req.getParameter("m_address"));
			String m_man_id = String.valueOf(req.getParameter("m_man_id"));
			String m_man_name = String.valueOf(req.getParameter("m_man_name"));
			String m_email = String.valueOf(req.getParameter("m_email"));
			String m_phone = String.valueOf(req.getParameter("m_phone"));

			//更新資料庫
			MasterService masSvc = new MasterService();

			masSvc.updatePro(m_id,m_name
					,m_gui,m_bank_id,m_address
					,m_man_id,m_man_name,m_email,m_phone);
			}
//=============================================================================
//=============================================================================
//==================================頁面單一查詢==================================
		if ("getOneSearch".equals(action)) {
			
			//查詢資料
			MasterService masSvc = new MasterService();
			Integer m_id = Integer.valueOf(req.getParameter("m_id"));
			MasterVO mVO = masSvc.getOnePro(m_id);

			//資料塞入masterVO2
			MasterVO2 masterVO2 = new MasterVO2.Builder()
												.setM_id(mVO.getM_id())
												.setM_name(mVO.getM_name())
												.setM_gui(mVO.getM_gui())
												.setM_bank_id(mVO.getM_bank_id())
												.setM_address(mVO.getM_address())
												.setM_man_id(mVO.getM_man_id())
												.setM_man_name(mVO.getM_man_name())
												.setM_email(mVO.getM_email())
												.setM_phone(mVO.getM_phone())
												.build();
			
			//返還資料到網頁
			writePojo2Json(res, GSON.toJson(masterVO2));
		}
	}
}

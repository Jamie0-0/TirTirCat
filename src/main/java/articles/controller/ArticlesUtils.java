package articles.controller;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class ArticlesUtils {
	public static void sendPicToClient(byte[] pic_content, HttpServletResponse response) {
	    try {
	       ServletOutputStream outputStream = response.getOutputStream();
	        response.setContentType("image/jpeg, image/jpg, image/png, image/gif"); 
	        outputStream.write(pic_content);  // 走IO直接輸出照片的byte[]到前端
	        outputStream.flush();
	        outputStream.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	public static String TurnIntoJson(Object object) {
		Gson gson = new Gson();
		return  gson.toJson(object);
	}
}

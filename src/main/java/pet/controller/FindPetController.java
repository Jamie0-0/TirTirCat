package pet.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import pet.dao.PetDao;
import pet.dao.PetDaoImpl;
import pet.vo.Pet;

@MultipartConfig
@WebServlet("/findpetcontroller")
public class FindPetController extends HttpServlet {

	private static final long serialVersionUID = 123L;
	private PetDao dao;

	@Override
	public void init() throws ServletException {
		dao = new PetDaoImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json;charset=utf-8");
		Gson gson = new Gson();

		Pet pet = gson.fromJson(req.getReader(), Pet.class);
		int uid = pet.getUId();
//		System.out.println(uid);
		List<Pet> pets = dao.selectByUid(uid);

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("status", "success");

		JsonArray petsArray = new JsonArray();
		// 將每個 JSON 字串加入陣列中
		for (Pet petItem : pets) {
			JsonObject petObject = new JsonObject();
			petObject.addProperty("petName", gson.toJson(petItem.getPetName()));
			petObject.addProperty("petBreed", gson.toJson(petItem.getPetBreed()));
			petObject.addProperty("petAge", gson.toJson(petItem.getPetAge()));
			petObject.addProperty("petSex", gson.toJson(petItem.getPetSex()));
			petObject.addProperty("petPerson", gson.toJson(petItem.getPetPerson()));
			byte[] petPicBytes = petItem.getPetPic();
			String base64EncodedString = Base64.getEncoder().encodeToString(petPicBytes);
			petObject.addProperty("petPic", base64EncodedString);
			petsArray.add(petObject);
		}

		jsonObject.add("petdata", petsArray);
		String message = gson.toJson(jsonObject);

		if (pet != null) {
			resp.getWriter().write(message);
		}
	}
}

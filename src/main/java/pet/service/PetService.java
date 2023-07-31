package pet.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Base64;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import pet.dao.PetDao;
import pet.dao.PetDaoImpl;
import pet.vo.Pet;

public class PetService {

	private PetDao dao;

	public PetService() {
		dao = new PetDaoImpl();
	}

	public void savePets(BufferedReader reader) throws IOException {
		JsonElement jsonElement = JsonParser.parseReader(reader);
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		JsonArray petsDataArray = jsonObject.getAsJsonArray("petsData");
		String uid = jsonObject.get("uid").getAsString();

		for (JsonElement petDataElement : petsDataArray) {
			JsonObject petDataObject = petDataElement.getAsJsonObject();
			String petType = petDataObject.get("petType").getAsString();
			String petName = petDataObject.get("petName").getAsString();
			String petBreed = petDataObject.get("petBreed").getAsString();
			int petAge = petDataObject.get("petAge").getAsInt();
			String petSex = petDataObject.get("petSex").getAsString();
			String petPerson = petDataObject.get("petPerson").getAsString();
			String petPic = petDataObject.get("petPic").getAsString();
			String base64 = petPic.substring(petPic.indexOf(",") + 1);

			Pet petData = new Pet();
			int petPersonInt = "活潑".equals(petPerson) ? 1 : 2;
			int petTypeValue = "狗".equals(petType) ? 1 : 2;
			String petSexValue = "公".equals(petSex) ? "0" : "1";
			byte[] petPicBytes = Base64.getDecoder().decode(base64);

			petData.setPetType(petTypeValue);
			petData.setPetName(petName);
			petData.setPetBreed(petBreed);
			petData.setPetAge(petAge);
			petData.setPetSex(petSexValue);
			petData.setPetPerson(petPersonInt);
			petData.setPetPic(petPicBytes);

			dao.insert(uid, petData);
		}
	}
}

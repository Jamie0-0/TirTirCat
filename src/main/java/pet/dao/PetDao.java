package pet.dao;

import java.util.List;

import pet.vo.Pet;

public interface PetDao {
	List<Pet> selectByUid(Integer uId);

	int insert(String uid, Pet petsData);

}
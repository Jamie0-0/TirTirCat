package pet.service;

import java.io.BufferedReader;
import java.io.IOException;

public interface PetService {
	void savePets(BufferedReader reader)throws IOException;
}
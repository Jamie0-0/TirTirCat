package pet.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Pet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pet_id")
	private Integer petId;
	@Column(name = "pet_uid")
	private Integer uId;
	@Column(name = "pet_type")
	private Integer petType;
	@Column(name = "pet_name")
	private String petName;
	@Column(name = "pet_breed")
	private String petBreed;
	@Column(name = "pet_age")
	private Integer petAge;
	@Column(name = "pet_pic")
	private byte[] petPic;
	@Column(name = "pet_sex")
	private String petSex;
	@Column(name = "pet_person")
	private Integer petPerson;

	public Integer getPetId() {
		return petId;
	}

	public void setPetId(Integer petId) {
		this.petId = petId;
	}

	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}

	public Integer getPetType() {
		return petType;
	}

	public void setPetType(Integer petType) {
		this.petType = petType;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getPetBreed() {
		return petBreed;
	}

	public void setPetBreed(String petBreed) {
		this.petBreed = petBreed;
	}

	public Integer getPetAge() {
		return petAge;
	}

	public void setPetAge(Integer petAge) {
		this.petAge = petAge;
	}

	public byte[] getPetPic() {
		return petPic;
	}

	public void setPetPic(byte[] petPic) {
		this.petPic = petPic;
	}

	public String getPetSex() {
		return petSex;
	}

	public void setPetSex(String petSex) {
		this.petSex = petSex;
	}

	public Integer getPetPerson() {
		return petPerson;
	}

	public void setPetPerson(Integer petPerson) {
		this.petPerson = petPerson;
	}

}

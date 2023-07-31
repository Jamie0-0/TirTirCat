package pet.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import member.vo.Member;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PET")
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
	
//	@ManyToOne
//	@JoinColumn(name = "pet_uid", insertable = false, updatable = false)
//	private Member member;
}

package pet.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import core.util.HibernateUtil;
import pet.vo.Pet;

public class PetDaoImpl implements PetDao {

	private Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	public List<Pet> selectByUid(Integer uId) {
		String hql = "FROM Pet  WHERE pet_uid = :uid";
		try {
			Transaction transaction = getSession().beginTransaction();
			Query<Pet> query = getSession().createQuery(hql, Pet.class);
			query.setParameter("uid", uId);
			List<Pet> pets = query.list();
			transaction.commit();
			return pets;
		} catch (Exception e) {
			getSession().getTransaction().rollback();
			e.printStackTrace();
		}
		System.out.println(hql);
		return null;
	}

	public int insert(String uid, Pet petsData) {
		try {
			Transaction transaction = getSession().beginTransaction();

			// 將 uid 設定到寵物資料中
			petsData.setUId(Integer.parseInt(uid));
			getSession().save(petsData);

			transaction.commit();
			return 1;
		} catch (Exception e) {
			getSession().getTransaction().rollback();
			e.printStackTrace();
		}
		return -1;
	}

}

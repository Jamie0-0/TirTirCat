package test.controller;

import org.hibernate.Session;

import articles.vo.Comment;
import core.util.HibernateUtil;

public class TestApp {

	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Comment comment = session.get(Comment.class, 1);
//		System.out.println(comment.getCom_content());
		HibernateUtil.shutdown();

	}

}

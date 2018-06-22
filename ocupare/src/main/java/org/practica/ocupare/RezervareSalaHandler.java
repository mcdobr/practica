package org.practica.ocupare;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class RezervareSalaHandler {
	static RezervareSalaHandler instance = new RezervareSalaHandler();
	
	
	public static RezervareSalaHandler getInstance() {
		return instance;
	}
	
	private SessionFactory sessionFactory;
	
	private RezervareSalaHandler() {
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}
	
	public String getRezervari() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<RezervareSala> result = session.createQuery("from RezervareSala").list();
		
		StringBuilder sb = new StringBuilder();
		for (RezervareSala rezervare : result)
			sb.append("Rezervarea " + rezervare.id);
		
		session.getTransaction().commit();
		session.close();
		
		return sb.toString();
			
		
	}
	
}

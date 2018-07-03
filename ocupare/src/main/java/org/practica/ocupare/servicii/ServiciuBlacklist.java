package org.practica.ocupare.servicii;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.practica.ocupare.utile.HibernateUtil;

@Path("blacklist")
public class ServiciuBlacklist {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<LocalDate> getDate()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		
		session.createQuery("from Blacklist");
		
		session.getTransaction().commit();
		session.close();
		
		return new ArrayList<LocalDate>();
	}
}
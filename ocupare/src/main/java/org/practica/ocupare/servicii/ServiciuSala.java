package org.practica.ocupare.servicii;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.practica.ocupare.entitati.Sala;
import org.practica.ocupare.utile.HibernateUtil;

@Path("sali")
public class ServiciuSala {

	
	@GET
	@Path("{salaID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Sala getTag(@PathParam("salaID") int id)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
    	
    	Sala sala = session.get(Sala.class, id);
    	session.getTransaction().commit();
    	session.close();
    	
    	return sala;
	}
	
	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Sala> getSali()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
    	List<Sala> sali = new ArrayList<Sala>();
		sali = session.createQuery("from Sala").list();
		
		session.getTransaction().commit();
		session.close();
		return sali;
	
	}
}
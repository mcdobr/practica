package org.practica.ocupare.servicii;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.practica.ocupare.entitati.Sala;
import org.practica.ocupare.entitati.Sala.TipSala;
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
    	session.beginTransaction();

    	List<Sala> sali = new ArrayList<Sala>();
		sali = session.createQuery("from Sala").list();
		
		session.getTransaction().commit();
		session.close();
		return sali;
	
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createSala(Sala s)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();

    	// logica de creare
    	session.save(s);
    	
    	session.getTransaction().commit();
		session.close();
		
		return Response.ok().build();
	}
	
	@DELETE
	@Path("{salaID}")
	public Response deleteSala(@PathParam("salaID") int id)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
		
    	Sala s = session.get(Sala.class, id);
    	
    	if(s!=null)
    		session.delete(s);
    	
    	session.getTransaction().commit();
		session.close();
		
    	
		return Response.ok().build();
	}

	
}
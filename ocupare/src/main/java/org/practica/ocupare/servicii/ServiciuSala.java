package org.practica.ocupare.servicii;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.practica.ocupare.entitati.Sala;
import org.practica.ocupare.entitati.Tag;
import org.practica.ocupare.utile.HibernateUtil;

@Path("sali")
public class ServiciuSala {

	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Sala getTag(@PathParam("id") int id)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
    	
    	Sala sala = session.get(Sala.class, id);
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
		session.close();
		return sali;
	
	}
	
}
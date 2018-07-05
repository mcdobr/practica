package org.practica.ocupare.servicii;

import java.time.LocalDate;
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
import org.practica.ocupare.entitati.ZiLibera;
import org.practica.ocupare.utile.HibernateUtil;

@Path("zilelibere")
public class ServiciuZileLibere {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ZiLibera> getDate()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
	
		List<ZiLibera> zileLibere = session.createQuery("from zilelibere").list();
		
		session.getTransaction().commit();
		session.close();
		
		return zileLibere;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response adaugaData(LocalDate data)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.save(data);
		
		session.getTransaction().commit();
		session.close();
		
		return Response.ok().build();
	}
	
	@DELETE
	@Path("{ziLiberaID}")
	public Response deleteData(@PathParam("ziLiberaID") int id)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		LocalDate data = session.get(LocalDate.class, id);
		if (data != null)
			session.delete(data);
		
		session.getTransaction().commit();
		session.close();
		return Response.ok().build();
	}
	
}

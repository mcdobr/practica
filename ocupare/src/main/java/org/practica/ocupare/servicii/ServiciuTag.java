package org.practica.ocupare.servicii;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.practica.ocupare.entitati.Tag;
import org.practica.ocupare.utile.HibernateUtil;

@Path("taguri")
public class ServiciuTag {

	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Tag getTag(@PathParam("id") int id)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
    	
    	Tag t = session.get(Tag.class, id);
    	session.getTransaction().commit();
    	session.close();
    	
    	return t;
	}
	
	@SuppressWarnings("unchecked")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Tag> getTags()
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
    	List<Tag> tags = new ArrayList<Tag>();
		tags = session.createQuery("from Tag").list();

    	session.getTransaction().commit();
		session.close();
		return tags;
	
	}
	
	@DELETE
	@Path("/tagDelete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Tag> deleteTag(@QueryParam("id") Integer id)
	{
		Session session = HibernateUtil.getSessionFactory().openSession();
    	List<Tag> tags = new ArrayList<Tag>();
    	for(Tag t: tags)
    	{
    		if(t.getId()==id)
    		{
    			session.delete(t);
    			
    		}
    	}

    	session.getTransaction().commit();
    	session.close();
    	return tags;
	}
	
}

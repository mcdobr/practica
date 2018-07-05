package org.practica.ocupare.servicii;

import java.time.LocalDate;
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
import org.practica.ocupare.entitati.Plan;
import org.practica.ocupare.entitati.Plan.*;
import org.practica.ocupare.utile.HibernateUtil;

@Path("planuri")
public class ServiciuPlanuri {
	
    @GET
    @Path("{planID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Plan getPlan(@PathParam("planID") int planID) {
    	
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
    	
    	//Plan p = new Plan("sadfasdf", new Periodicitate(), LocalDate.now(), LocalDate.now(), "ewrqeq", "weqrwqer");
    	//session.save(p);
    	
    	Plan plan = session.get(Plan.class, planID);
    	session.getTransaction().commit();
    	session.close();
    	return plan;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPlan(Plan p)
    {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
    	
    	session.save(p);
    	
    	session.getTransaction().commit();
    	session.close();
    	
    	return Response.ok().build();
    }
    
    @DELETE
    @Path("{planID}")
    public Response deletePlan(@PathParam("planID") int planID)
    {
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
    	
    	Plan plan = session.get(Plan.class, planID);
    	if (plan != null)
    		session.delete(plan);
    	
    	session.getTransaction().commit();
    	session.close();
    	
    	return Response.ok().build();
    }
}

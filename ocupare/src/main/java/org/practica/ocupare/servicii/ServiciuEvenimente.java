package org.practica.ocupare.servicii;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.practica.ocupare.entitati.Eveniment;
import org.practica.ocupare.entitati.Plan;
import org.practica.ocupare.utile.HibernateUtil;

@Path("evenimente")
public class ServiciuEvenimente {
	
	@GET
	@Path("{evenimentID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Eveniment getEveniment(@PathParam("evenimentID") int evenimentID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Eveniment eveniment = session.get(Eveniment.class, evenimentID);
			
		session.getTransaction().commit();
		session.close();
		return eveniment;
	}
	
	@GET
	@Path("query")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Eveniment> getEvenimentelePlanului(@QueryParam("planID") int planID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Plan plan = session.get(Plan.class, planID);
		
		//TODO: eliminat astea
		Eveniment e = new Eveniment(plan, LocalDateTime.now(), LocalDateTime.now().plusHours(2));
		session.save(e);
		plan.getEvenimente().add(e);
		
		List<Eveniment> ev = new ArrayList<>(plan.getEvenimente());
		System.out.println(Arrays.toString(ev.toArray()));
		
		List<Eveniment> evenimente = new ArrayList<>(plan.getEvenimente());

		session.getTransaction().commit();
		session.close();		
		return evenimente;
	}
	
	@DELETE
	@Path("{evenimentID}")
	public Response deleteEveniment(@PathParam("evenimentID") int evenimentID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		session.getTransaction().commit();
		session.close();
		return Response.ok().build();
	}
}

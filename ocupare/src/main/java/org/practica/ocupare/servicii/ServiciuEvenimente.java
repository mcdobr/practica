package org.practica.ocupare.servicii;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Query;
import org.hibernate.Session;
import org.practica.ocupare.entitati.Eveniment;
import org.practica.ocupare.entitati.Plan;
import org.practica.ocupare.entitati.Sala;
import org.practica.ocupare.utile.HibernateUtil;

@Path("evenimente")
public class ServiciuEvenimente {

	@GET
	@Path("{evenimentID}")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Eveniment getEveniment(@PathParam("evenimentID") int evenimentID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Eveniment eveniment = session.get(Eveniment.class, evenimentID);

		session.getTransaction().commit();
		session.close();
		return eveniment;
	}
	
	@SuppressWarnings("unchecked")
	@GET
	@Path("query")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public List<Eveniment> getSalaSiPlanuri(@QueryParam("planID") int planID,@QueryParam("salaID") int salaID ) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Plan plan = session.get(Plan.class, planID);
		Sala sala = session.get(Sala.class, salaID);
		List<Eveniment> evenimente = new ArrayList<>(plan.getEvenimente());
		
		
		if(sala!=null && plan!=null)
		{
			String hql = "select e.id,e.inceput,e.sfarsit,e.plan from evenimente e,planuri p,sali s, planuri_sali sp where e.plan=p.id and sp.plan_id=p.id and sp.sali_id=s.id and s.id =: sid and p.id =: pid";
			
			evenimente = session.createQuery(hql).setParameter("sid", salaID).setParameter("pid", planID).list();
			
		}
		else
		{
			if(sala!=null)
			{
				
			}
			else
			{
				Eveniment e = new Eveniment(plan, LocalDateTime.now(), LocalDateTime.now().plusHours(2));
				session.save(e);
				plan.getEvenimente().add(e);

				List<Eveniment> ev = new ArrayList<>(plan.getEvenimente());
				System.out.println(Arrays.toString(ev.toArray()));

			}
		}

		session.getTransaction().commit();
		session.close();
		return evenimente;
	}

	@DELETE
	@Path("{evenimentID}")
	@RolesAllowed({ "user", "admin" })
	public Response deleteEveniment(@PathParam("evenimentID") int evenimentID) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		session.getTransaction().commit();
		session.close();
		return Response.ok().build();
	}
}

package org.practica.ocupare.servicii;

import java.time.LocalDate;
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

import org.hibernate.Session;
import org.hibernate.query.Query;
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

	@GET
	@Path("query")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public List<Eveniment> getEvenimenteQuery(@QueryParam("planID") Integer planID, @QueryParam("salaID") Integer salaID,
			@QueryParam("data") String dataStr) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Plan plan = null;
		Sala sala = null;
		LocalDate data = null;
		// Doar sala
		
		boolean usePlan = (planID != null);
		boolean useSala = (salaID != null);
		boolean useData = (data != null);
		
		
		StringBuilder queryBuilder = new StringBuilder();
		queryBuilder.append("from evenimente as ev ");
		if (usePlan || useSala || useData)
			queryBuilder.append("where ");
		
		if (usePlan) {
			plan = session.get(Plan.class, planID);
			queryBuilder.append("ev.plan.id = :planID ");
		}
		
		if (useSala) {
			sala = session.get(Sala.class, salaID);
			if (usePlan)
				queryBuilder.append("and ");
			queryBuilder.append(":sala in elements(ev.plan.sali) ");
		}
		
		if (useData) {
			data = LocalDate.parse(dataStr);
			if (usePlan || useSala)
				queryBuilder.append("and ");
			queryBuilder.append("day(ev.inceput) = day(:data) and month(ev.inceput) = month(:data) and year(ev.inceput) = year(:data)");
		}
		
		String hql = queryBuilder.toString();
		Query query = session.createQuery(hql);
		
		if (usePlan)
			query = query.setParameter("planID", planID);
		if (useSala)
			query = query.setParameter("sala", sala);
		if (useData)
			query = query.setParameter("data", data);
		
		
		List<Eveniment> evenimente = query.list();
		
		
		session.getTransaction().commit();
		session.close();
		return evenimente;
		

		/*
		evenimente = session.createQuery("from evenimente as ev " +
				"where day(ev.inceput) = day(:data) and month(ev.inceput) = month(:data) and year(ev.inceput) = year(:data)")
				.setParameter("data", data).list();
		*/
		
		/*evenimente = session.createQuery("from evenimente as ev where :sala in elements(ev.plan.sali)")
		.setParameter("sala", sala).list();*/


		/*evenimente = session.createQuery("from evenimente as ev where ev.plan.id = :planID")
				.setParameter("planID", planID).list();
		*/
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

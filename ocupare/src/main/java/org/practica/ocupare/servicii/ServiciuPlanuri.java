package org.practica.ocupare.servicii;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
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
import org.practica.ocupare.entitati.Eveniment;
import org.practica.ocupare.entitati.Plan;
import org.practica.ocupare.entitati.Plan.Periodicitate;
import org.practica.ocupare.entitati.Plan.Periodicitate.TipPeriodicitate;
import org.practica.ocupare.utile.HibernateUtil;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Path("planuri")
public class ServiciuPlanuri {

	@GET
	@Path("{planID}")
	@Produces(MediaType.APPLICATION_JSON)
	//@PermitAll
	@RolesAllowed({ "user", "admin" })
	public Plan getPlan(@PathParam("planID") int planID) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		// Plan p = new Plan("sadfasdf", new Periodicitate(), LocalDate.now(),
		// LocalDate.now(), "ewrqeq", "weqrwqer");
		// session.save(p);

		Plan plan = session.get(Plan.class, planID);
		session.getTransaction().commit();
		session.close();

		// return p;
		return plan;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({ "user", "admin" })
	public Response createPlan(final String json) throws JsonParseException, JsonMappingException, IOException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		
		final ObjectNode node = new ObjectMapper().readValue(json, ObjectNode.class);
		System.out.println(json);
		System.out.println(node.get("nume"));
		
		
		
		final LocalDate inceputPlan = LocalDate.parse(node.get("inceput").asText());
		final LocalDate sfarsitPlan = LocalDate.parse(node.get("sfarsit").asText());
		final LocalTime inceputOra	= LocalTime.parse(node.get("oraInceput").asText());
		final LocalTime sfarsitOra	= LocalTime.parse(node.get("oraTerminare").asText());
		
		Periodicitate per = new Periodicitate(TipPeriodicitate.UNIC);
		Plan plan = new Plan(
				node.get("nume").asText(),
				per, 
				inceputPlan,
				sfarsitPlan,
				node.get("participanti").asText(),
				node.get("descriere").asText()
				);
		
		session.save(plan);
		
		
		//TODO: Trebuie modificat incrementul. trebuie luate salile si adaugate
		TemporalAmount increment = null;
		
		for (LocalDate currentDate = plan.getInceput(); 
				currentDate.isBefore(plan.getSfarsit()); 
				currentDate = currentDate.plusWeeks(1)) {
			
			Eveniment eveniment = new Eveniment(plan, LocalDateTime.of(currentDate, inceputOra),
					LocalDateTime.of(currentDate, sfarsitOra));
			
			plan.getEvenimente().add(eveniment);
			session.save(eveniment);
		}
		
		/*
		switch (1)
		{
		case UNIC:
			break;
		case SAPTAMANAL:
			break;
		case BISAPTAMANAL:
			break;
		case LUNAR:
			break;
		case ANUAL:
			break;
		case CUSTOM:
			break;
		}
		*/
		session.getTransaction().commit();
		session.close();

		return Response.ok().build();
	}

	@DELETE
	@Path("{planID}")
	@RolesAllowed({ "user", "admin" })
	public Response deletePlan(@PathParam("planID") int planID) {
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

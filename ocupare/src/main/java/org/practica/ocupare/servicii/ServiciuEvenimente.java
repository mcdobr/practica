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

		//Plan plan = session.get(Plan.class, planID);
		//Sala sala = session.get(Sala.class, salaID);
		LocalDate data = LocalDate.parse(dataStr);
		/*evenimente = session.createQuery("from evenimente as ev where ev.plan.id = :planID")
				.setParameter("planID", planID).list();
		*/
		// Doar sala
		
		List<Eveniment> evenimente;
		/*evenimente = session.createQuery("from evenimente as ev where :sala in elements(ev.plan.sali)")
				.setParameter("sala", sala).list();*/
		
		
		evenimente = session.createQuery("from evenimente as ev " +
				"where day(ev.inceput) = day(:data) and month(ev.inceput) = month(:data) and year(ev.inceput) = year(:data)")
				.setParameter("data", data).list();
		
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

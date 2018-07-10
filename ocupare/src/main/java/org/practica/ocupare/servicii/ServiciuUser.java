package org.practica.ocupare.servicii;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.hibernate.Session;
import org.practica.ocupare.entitati.User;
import org.practica.ocupare.utile.HibernateUtil;

@Path("useri")
public class ServiciuUser {
	@GET
	@Path("{userID}")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public User getUser(@PathParam("userID") int userID) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		User user = session.get(User.class, userID);

		session.getTransaction().commit();
		session.close();

		return user;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	// TODO: Schimbat ca doar adminii sa poata face asta
	@PermitAll
	public Response createUser(User u) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		session.save(u);

		session.getTransaction().commit();
		session.close();

		// TODO: modifica
		return Response.ok().build();
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{userID}")
	@PermitAll
	public Response logUser(@PathParam("userID") int userID, User u) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		User persistUser = session.get(User.class, userID);
		boolean isAuthenticated = (persistUser != null && persistUser.getNume().equals(u.getNume()) && persistUser.getParola().equals(u.getParola()));
		
		session.getTransaction().commit();
		session.close();
		
		if (isAuthenticated)
			return Response.ok().build();
		else
			return Response.status(Status.FORBIDDEN).build();
	}

}

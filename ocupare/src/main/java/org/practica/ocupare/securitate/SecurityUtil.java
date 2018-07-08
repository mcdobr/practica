package org.practica.ocupare.securitate;

import java.util.Set;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.internal.util.Base64;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.practica.ocupare.entitati.User;
import org.practica.ocupare.utile.HibernateUtil;

public class SecurityUtil {
	// HTTP has a misnomer on 401 (it should be unauthenticated not unauthorized).
	// 403 is for unauthorized.
	public static final String AUTHORIZATION_PROPERTY = "Authorization";
	public static final String AUTHENTICATION_SCHEME = "Basic";
	public static final ResponseBuilder UNAUTHENTICATED_RESPONSE = Response.status(Status.UNAUTHORIZED)
			.type(MediaType.TEXT_PLAIN).entity("Nu ești logat!");
	public static final ResponseBuilder UNAUTHORIZED_RESPONSE = Response.status(Status.FORBIDDEN)
			.type(MediaType.TEXT_PLAIN).entity("Nu ești autorizat să faci asta!");

	/**
	 * @param authHeader
	 *            The HTTP basic auth header string.
	 * @return An array with two Strings representing the username and the password.
	 */
	public static String[] getUserPassPair(String authHeader) {
		final String encodedUserPass = authHeader.replaceFirst(AUTHENTICATION_SCHEME + " ", "");
		final String usernameAndPassword = new String(Base64.decode(encodedUserPass.getBytes()));
		

		if (usernameAndPassword.contains(":")) {
			final String[] userPassPair = usernameAndPassword.split(":");
			return userPassPair;
		} else
			throw new IllegalArgumentException("Invalid authHeader!");
	}

	/**
	 * 
	 * @param username
	 *            Username supplied by basic auth header.
	 * @param password
	 *            Password supplied by basic auth header.
	 * @return If the user is authenticated (proves his identity).
	 */
	public static boolean isAuthenticationValid(String username, String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from users where nume = :nume");
		query.setParameter("nume", username);
		User user = (User)query.uniqueResult();
		
		session.getTransaction().commit();
		session.close();

		if (user == null || !password.equals(user.getParola()))
			return false;
		return true;
	}

	public static boolean isAuthenticationValid(String[] userPassPair) {
		return isAuthenticationValid(userPassPair[0], userPassPair[1]);
	}

	/**
	 * @param username
	 *            Username supplied by basic auth header.
	 * @param password
	 *            Password supplied by basic auth header.
	 * @param allowedRolesSet
	 *            Set of annotation strings present on the method.
	 * @return If the user can access the method.
	 */
	public static boolean isUserAllowedOnMethod(String username, String password, Set<String> allowedRolesSet) {
		if (!isAuthenticationValid(username, password))
			return false;

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		User user = (User) session.createCriteria(User.class, "user").add(Restrictions.eq("user.nume", username))
				.uniqueResult();

		session.getTransaction().commit();
		session.close();

		if (user.getRol().equals("admin") && allowedRolesSet.contains("admin"))
			return true;
		if (!user.getRol().equals("admin") && allowedRolesSet.contains("user"))
			return true;
		return false;
	}

	/*
	 * public static boolean isPersonalResource(String username, Object obj) { if
	 * (obj instanceof User) { User res = (User)obj; return
	 * (username.equals(res.getNume())); } else if (obj instanceof Review) { Review
	 * res = (Review)obj; return (username.equals(res.getUsername())); } else return
	 * false; }
	 * 
	 * public static boolean isModeratorsResource(Object obj) { if (obj instanceof
	 * User) { User user = (User)obj; return user.getIsModerator(); } else if (obj
	 * instanceof Review) { Review review = (Review)obj; return
	 * UserDAO.getInstance().getUser(review.getUsername()).getIsModerator(); } else
	 * return false; }
	 */

	/**
	 * @param username
	 *            Username supplied by basic auth header.
	 * @param password
	 *            Password supplied by basic auth header.
	 * @param obj
	 *            Called resource.
	 * @return If the user is authorized to modify the entity.
	 */
	public static boolean isUserAuthorized(String authHeader, Object obj) {
		final String[] userPassPair = getUserPassPair(authHeader);
		final String username = userPassPair[0];
		final String password = userPassPair[1];

		if (!isAuthenticationValid(username, password))
			return false;

		// Authentication is valid
		// A user with an account is making the request

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		User user = (User) session.createCriteria(User.class, "user").add(Restrictions.eq("user.nume", username))
				.uniqueResult();

		session.getTransaction().commit();
		session.close();

		if (user.getRol().equals("admin")) {
			return true;
		} else {
			return true;
		}
		/*
		 * if (isModeratorsResource(obj)) return isPersonalResource(username, obj); else
		 * return true; } else { return isPersonalResource(username, obj); }
		 */
	}
}

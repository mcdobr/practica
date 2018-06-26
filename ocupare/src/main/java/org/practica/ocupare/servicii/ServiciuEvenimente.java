package org.practica.ocupare.servicii;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.practica.ocupare.entitati.Eveniment;

@Path("evenimente")
public class ServiciuEvenimente {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        
    	
        Eveniment eveniment = new Eveniment();
    	eveniment.setId(1);
    	eveniment.setNume("First event");
    	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	session.save(eveniment);
    	
    	eveniment = new Eveniment();
    	eveniment.setId(2);
    	eveniment.setNume("Second event");
    	session.save(eveniment);
    	
    	session.getTransaction().commit();
    	session.close();
    	
        return "Got it!";
    }
}

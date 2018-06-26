package org.practica.ocupare.servicii;

import java.time.LocalDate;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.practica.ocupare.entitati.PlanEveniment;

@Path("planuri")
public class ServiciuPlanuri {
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PlanEveniment getIt() {
    	
        PlanEveniment plan = new PlanEveniment();
    	plan.setId(1);
    	plan.setNume("Curs SD");
    	plan.setInceput(LocalDate.now());
    	plan.setSfarsit(LocalDate.now().plusMonths(1));
    	plan.setParticipanti("Anul III");
    	plan.setDescriere("Sisteme P2P");
    	plan.setDetinatorId(1);
    	
    	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    	Session session = sessionFactory.openSession();
    	session.beginTransaction();
    	session.save(plan);
    	
    	/*
    	plan = new PlanEveniment();
    	plan.setId(2);
    	plan.setNume("Second event");
    	session.save(plan);*/
    	
    	session.getTransaction().commit();
    	session.close();
    	
        return plan;
    }
}

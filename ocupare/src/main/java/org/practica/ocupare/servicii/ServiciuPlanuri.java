package org.practica.ocupare.servicii;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.practica.ocupare.entitati.PlanEveniment;
import org.practica.ocupare.entitati.PlanEveniment.*;
import org.practica.ocupare.entitati.PlanEveniment.Periodicitate.TipPeriodicitate;
import org.practica.ocupare.utile.HibernateUtil;

@Path("planuri")
public class ServiciuPlanuri {
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PlanEveniment getIt() {
    	
        PlanEveniment plan = new PlanEveniment();
    	//plan.setId(1);
    	plan.setNume("Curs SD");
    	plan.setInceput(LocalDate.now());
    	plan.setSfarsit(LocalDate.now().plusMonths(1));
    	plan.setParticipanti("Anul III");
    	plan.setDescriere("Sisteme P2P");
    	plan.setDetinatorId(1);
    	plan.setPeriodicitate(new Periodicitate(TipPeriodicitate.CUSTOM, new ArrayList<String>(Arrays.asList("Luni", "Miercuri", "Vineri"))));
    	
    	Session session = HibernateUtil.getSessionFactory().openSession();
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

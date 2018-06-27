package org.practica.ocupare.servicii;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.practica.ocupare.entitati.BlackList;
import org.practica.ocupare.entitati.PlanEveniment;
import org.practica.ocupare.entitati.RezervareSala;
import org.practica.ocupare.entitati.Sala;
import org.practica.ocupare.entitati.TipSala;
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
    	
    	Sala sala = new Sala();
    	sala.setID(1);
    	sala.setNume("C0-3");
    	sala.setNrLocuri(30);
    	sala.setProiector(true);
    	sala.setTip(TipSala.Laborator);
    	session.save(sala);
    	
    	sala = new Sala();
    	sala.setNume("C0-4");
    	sala.setNrLocuri(30);
    	sala.setProiector(false);
    	sala.setTip(TipSala.Laborator);
    	session.save(sala);
    	
    	
    	sala = new Sala();
    	sala.setNume("C0-5");
    	sala.setNrLocuri(30);
    	sala.setProiector(true);
    	sala.setTip(TipSala.Laborator);
    	session.save(sala);
    	
    	RezervareSala rs = new RezervareSala();
    	rs.setId(1);
    	rs.setIdEveniment(1);
    	rs.setIdSala(1);
    	session.save(rs);
    	
    	rs=new RezervareSala();
    	rs.setIdEveniment(1);
    	rs.setIdSala(2);
    	session.save(rs);
    	
    	rs=new RezervareSala();
    	rs.setIdEveniment(2);
    	rs.setIdSala(2);
    	session.save(rs);
 	
    	
    	BlackList bl = new BlackList();
    	bl.setId(1);
    	bl.setZi(LocalDate.parse("2018-12-25"));
    	session.save(bl);
    	
    	bl=new BlackList();
    	bl.setZi(LocalDate.parse("2018-12-26"));
    	session.save(bl);
    	
    	
    	session.getTransaction().commit();
    	session.close();
    	
    	
    	
    	
        return plan;
    }
}

package org.practica.ocupare.servicii;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.practica.ocupare.entitati.BlackList;
import org.practica.ocupare.entitati.PlanEveniment;
import org.practica.ocupare.entitati.Sala;
import org.practica.ocupare.entitati.Tag;
import org.practica.ocupare.entitati.TipSala;
import org.practica.ocupare.entitati.PlanEveniment.*;
import org.practica.ocupare.entitati.PlanEveniment.Periodicitate.TipPeriodicitate;
import org.practica.ocupare.utile.HibernateUtil;

@Path("planuri")
public class ServiciuPlanuri {
	
    @GET
    @Path("{planID}")
    @Produces(MediaType.APPLICATION_JSON)
    public PlanEveniment getPlan(@PathParam("planID") int planID) {
    	
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
    	
    	PlanEveniment pe1 = new PlanEveniment(2, "Curs BD", new Periodicitate(), LocalDate.now(), LocalDate.now().plusDays(1), 1, "", "");
    	PlanEveniment pe2 = new PlanEveniment(3, "Curs RC", new Periodicitate(), LocalDate.now(), LocalDate.now().plusDays(1), 1, "", "");

    	
    	Sala s1 = new Sala(5, "AC01", 150, true, TipSala.Amfiteatru);
    	Sala s2 = new Sala(6, "AC03", 80, false, TipSala.Seminar); 
    	
    	Tag t1 = new Tag(1, "CTI", "tyvgfyvg");
    	Tag t2 = new Tag(1, "IS", "bla");    	
    	
    	pe1.adaugaSala(s1);
    	pe1.adaugaSala(s2);    	
    	pe2.adaugaSala(s1);
    	pe1.adaugaTag(t1);
    	pe1.adaugaTag(t2);
    	pe2.adaugaTag(t2);
    	
    	
    	session.save(s1);
    	session.save(s2);
    	session.save(pe1);
    	session.save(pe2);
    	session.save(t1);
    	session.save(t2);
    	
    	session.getTransaction().commit();
    	session.close();
    	
    	/*
    	
    	PlanEveniment plan = session.get(PlanEveniment.class, planID);
    	
    	session.getTransaction().commit();
    	session.close();*/
        return pe1;
    	
    	/*
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
    	
    	
    	plan = new PlanEveniment();
    	plan.setId(2);
    	plan.setNume("Second event");
    	session.save(plan);
    	
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
    	*/
    }
}

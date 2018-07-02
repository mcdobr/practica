package org.practica.ocupare.servicii;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.practica.ocupare.entitati.Eveniment;
import org.practica.ocupare.entitati.Plan;
import org.practica.ocupare.entitati.Sala;
import org.practica.ocupare.entitati.Sala.TipSala;
import org.practica.ocupare.entitati.Tag;
import org.practica.ocupare.entitati.User;
import org.practica.ocupare.functii.Encrypt;
import org.practica.ocupare.entitati.Plan.*;
import org.practica.ocupare.utile.HibernateUtil;

@Path("planuri")
public class ServiciuPlanuri {
	
    @GET
    @Path("{planID}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getPlan(@PathParam("planID") int planID) {
    	
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	session.beginTransaction();
    	
    	Plan pe3 = new Plan("Curs SD",new Periodicitate(),LocalDate.now(),LocalDate.now(),"Anul III","Liste circulare");
    	Sala s1 = new Sala("AC01", 150, true, TipSala.AMFITEATRU);
    	Tag t1 = new Tag("CTI", "2 ore");
    
    	pe3.adaugaSala(s1);
    	pe3.adaugaTag(t1);
  
    	
    	User user = new User();
    	user.setNume("Florin Ungureanu");
    	user.setEmail("fungurea@cs.tuiasi.ro");
    	user.setRol(" prof. dr. ing.");
    	user.setParola(Encrypt.generateHash("florina.ungureanu"));
    	pe3.setUser(user);
    	
    	Eveniment ie = new Eveniment();
    	ie.setInceput(LocalDateTime.of(pe3.getInceput(), LocalTime.of(8, 0)));
    	ie.setSfarsit(LocalDateTime.of(pe3.getSfarsit(), LocalTime.of(9, 50)));
    	ie.setPlan(pe3);
    	pe3.getEvenimente().add(ie);
    	user.getPlanuri().add(pe3);
    	
    	session.save(pe3);
    	session.save(ie);
    	session.save(user);
    	session.save(s1);
    	session.save(t1);
    	
    	session.getTransaction().commit();
    	session.close();
    	
    	/*
    	
    	PlanEveniment plan = session.get(PlanEveniment.class, planID);
    	
    	session.getTransaction().commit();
    	session.close();*/
        return user;
    	
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

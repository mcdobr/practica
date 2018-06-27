package org.practica.ocupare.servicii;

import java.time.LocalDate;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.practica.ocupare.entitati.BlackList;
import org.practica.ocupare.entitati.Eveniment;
import org.practica.ocupare.entitati.RezervareSala;
import org.practica.ocupare.entitati.Sala;
import org.practica.ocupare.entitati.TipSala;

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
    	
    	eveniment = new Eveniment();
    	eveniment.setId(3);
    	eveniment.setNume("Third event");
    	session.save(eveniment);
    	
    	
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
    	
    	
    	
        return "Got it!";
    }
}

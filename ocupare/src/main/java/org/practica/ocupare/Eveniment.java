package org.practica.ocupare;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Eveniment {
	@Id
	public int id;	
	public String nume;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
}

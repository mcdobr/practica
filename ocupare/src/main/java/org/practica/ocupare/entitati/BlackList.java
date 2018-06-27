package org.practica.ocupare.entitati;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="BlackList")
public class BlackList {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	int Id;
	
	
	@Column(name="Zi")
	LocalDate Zi;


	public int getId() {
		return Id;
	}


	public void setId(int id) {
		Id = id;
	}


	public LocalDate getZi() {
		return Zi;
	}


	public void setZi(LocalDate zi) {
		Zi = zi;
	}
	

}

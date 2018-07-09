package org.practica.ocupare.entitati;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "zilelibere")
@Table(name = "zilelibere")
public class ZiLibera {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	int id;

	@Column(name = "zi")
	LocalDate zi;

	public int getId() {
		return id;
	}

	public LocalDate getZi() {
		return zi;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setZi(LocalDate zi) {
		this.zi = zi;
	}

}

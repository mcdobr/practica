package org.practica.ocupare;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Eveniment {
	@Id
	public Integer id;
	
	public String nume;
}

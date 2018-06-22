package org.practica.ocupare;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RezervareSala {
	@Id
	public Integer id;
	
	public Integer idEveniment;
	public Integer idSala;
}

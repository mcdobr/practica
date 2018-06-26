package org.practica.ocupare.entitati;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="RezervareSala")
public class RezervareSala {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	int Id;
	
	@Column(name="IdEveniment", nullable=false)
	int IdEveniment;
	
	@Column(name="IdSala", nullable=false)
	int IdSala;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getIdEveniment() {
		return IdEveniment;
	}

	public void setIdEveniment(int idEveniment) {
		IdEveniment = idEveniment;
	}

	public int getIdSala() {
		return IdSala;
	}

	public void setIdSala(int idSala) {
		IdSala = idSala;
	}
	
}

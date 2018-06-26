package org.practica.ocupare.entitati;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@Entity(name="Sala")
public class Sala {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	int Id;

	@Column(name="Nume", nullable=false)
	String Nume;
	
	@Column(name="NrLocuri")
	int NrLocuri;
	
	@Column(name="Proiector", nullable=false)
	boolean Proiector;
	
	@Column(name="Tip", nullable=false)
	@NotNull
	TipSala Tip;

	public TipSala getTip() {
		return Tip;
	}

	public void setTip(TipSala tip) {
		this.Tip = tip;
	}

	public int getID() {
		return Id;
	}

	public void setID(int iD) {
		Id = iD;
	}

	public String getNume() {
		return Nume;
	}

	public void setNume(String nume) {
		this.Nume = nume;
	}

	public int getNrLocuri() {
		return NrLocuri;
	}

	public void setNrLocuri(int nrLocuri) {
		NrLocuri = nrLocuri;
	}

	public boolean isProiector() {
		return Proiector;
	}

	public void setProiector(boolean proiector) {
		this.Proiector = proiector;
	};
	
	
	
}

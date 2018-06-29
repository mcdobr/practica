package org.practica.ocupare.entitati;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


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

	
	@ManyToMany(fetch=FetchType.EAGER)
	@JsonIgnoreProperties("saliList")	
	private Collection<PlanEveniment> evenimenteList = new ArrayList<>();
	
	public Sala(String nume, int nrLocuri, boolean proiector, TipSala tip) {
		super();
		Nume = nume;
		NrLocuri = nrLocuri;
		Proiector = proiector;
		Tip = tip;
		this.evenimenteList = new ArrayList<>();
	}

	
	public Sala() {}
	
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
	}

	public Collection<PlanEveniment> getEvenimenteList() {
		return evenimenteList;
	}

	public void setEvenimenteList(Collection<PlanEveniment> evenimenteList) {
		this.evenimenteList = evenimenteList;
	};
	
	
	public void adaugaEveniment(PlanEveniment pe)
	{
		this.evenimenteList.add(pe);
		pe.getSaliList().add(this);
	}
	
	
	
}

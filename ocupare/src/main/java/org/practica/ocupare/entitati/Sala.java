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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "Sala")
@Table(name = "Sala")
public class Sala {
	public static enum TipSala {
		AMFITEATRU, LABORATOR, SEMINAR, BIROU, CONFERINTE

	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "Id")
	int Id;

	@Column(name = "Nume", nullable = false, unique = true)
	String Nume;

	@Column(name = "NrLocuri")
	Integer NrLocuri;

	@Column(name = "Proiector", nullable = false)
	boolean Proiector;

	@Column(name = "Tip", nullable = false)
	@NotNull
	TipSala Tip;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	//@JsonIgnoreProperties("{sali, planuri}")
	@JsonIgnore
	private Collection<Plan> planuri = new ArrayList<>();

	public Sala(String nume, int nrLocuri, boolean proiector, TipSala tip) {
		super();
		Nume = nume;
		NrLocuri = nrLocuri;
		Proiector = proiector;
		Tip = tip;
		this.planuri = new ArrayList<>();
	}

	public Sala() {
	}

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

	public Collection<Plan> getPlanuri() {
		return planuri;
	}

	public void setPlanuri(Collection<Plan> planuri) {
		this.planuri = planuri;
	}

	public void adaugaEveniment(Plan pe) {
		this.planuri.add(pe);
		pe.getSali().add(this);
	}
}

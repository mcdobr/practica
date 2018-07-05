package org.practica.ocupare.entitati;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "sali")
public class Sala {
	public static enum TipSala {
		AMFITEATRU, LABORATOR, SEMINAR, BIROU, CONFERINTE

	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@Column(name = "Id")
	int id;

	@Column(name = "nume", nullable = false, unique = true)
	String nume;

	@Column(name = "nrLocuri")
	Integer nrLocuri;

	@Column(name = "proiector", nullable = false)
	boolean proiector;

	@Enumerated(EnumType.STRING)
	@Column(name = "tip", nullable = false)
	@NotNull
	TipSala tip;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	//@JsonIgnoreProperties("{sali, planuri}")
	@JsonIgnore
	private Collection<Plan> planuri = new ArrayList<>();

	public Sala(String nume, int nrLocuri, boolean proiector, TipSala tip) {
		super();
		this.nume = nume;
		this.nrLocuri = nrLocuri;
		this.proiector = proiector;
		this.tip = tip;
		this.planuri = new ArrayList<>();
	}

	public Sala() {
	}

	public TipSala getTip() {
		return tip;
	}

	public void setTip(TipSala tip) {
		this.tip = tip;
	}

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public int getNrLocuri() {
		return nrLocuri;
	}

	public void setNrLocuri(int nrLocuri) {
		this.nrLocuri = nrLocuri;
	}

	public boolean isProiector() {
		return proiector;
	}

	public void setProiector(boolean proiector) {
		this.proiector = proiector;
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

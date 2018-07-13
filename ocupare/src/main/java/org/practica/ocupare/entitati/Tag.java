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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "taguri")
@Table(name = "taguri")
public class Tag {

	@Id
	@Column(name = "id", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;

	@Column(name = "nume", nullable = false, length = 20, unique = true)
	public String nume;

	@Column(name = "descriere", nullable = false)
	public String descriere;

	@ManyToMany(fetch = FetchType.EAGER)
	@LazyCollection(LazyCollectionOption.FALSE)
	// @JsonIgnoreProperties("{taguri, sali}")
	@JsonIgnore
	private Collection<Plan> planuri = new ArrayList<>();

	public Tag() {
	}

	public Tag(String nume, String descriere) {
		super();
		this.nume = nume;
		this.descriere = descriere;
		this.planuri = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public Collection<Plan> getPlanuri() {
		return planuri;
	}

	public void setPlanuri(Collection<Plan> planuri) {
		this.planuri = planuri;
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

	public String getDescriere() {
		return descriere;
	}

	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}

	@Override
	public String toString() {
		return "Tag [id=" + id + ", nume=" + nume + ", descriere=" + descriere + "]";
	}

	public void adaugaEveniment(Plan pe) {
		this.planuri.add(pe);
		pe.getTaguri().add(this);
	}

}

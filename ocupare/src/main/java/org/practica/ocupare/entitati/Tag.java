package org.practica.ocupare.entitati;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Tag{
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	
	@Column(name="nume", nullable=false, length=20)
	public String nume;
	
	@Column(name="descriere", nullable=false)
	public String descriere;

	public int getId() {
		return id;
	}
	
	@ManyToMany
	@JsonBackReference
	private Collection<PlanEveniment> evenimenteList = new ArrayList<>();


	public Collection<PlanEveniment> getEvenimenteList() {
		return evenimenteList;
	}

	public void setEvenimenteList(Collection<PlanEveniment> evenimenteList) {
		this.evenimenteList = evenimenteList;
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
	

}

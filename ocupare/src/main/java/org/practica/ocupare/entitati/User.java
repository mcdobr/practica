package org.practica.ocupare.entitati;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int id;
	
	@Column(name = "nume", length=50, nullable = false)
	public String nume;
	
	@Column(name = "email", length=50, nullable = false)
	public String email;
	
	//SETARE CONSTRANGERI GEN: O LITERA MARE, O CIFRA, ETC;
	@Column(name = "parola", nullable = false)
	public String parola;
	
	public Collection<PlanEveniment> getEvenimente() {
		return evenimente;
	}

	public void setEvenimente(Collection<PlanEveniment> evenimente) {
		this.evenimente = evenimente;
	}

	@Column(name = "rol", nullable = false)
	public String rol;
	
	@OneToMany
	@JoinTable(name="User_PlanEveniment",
		joinColumns=@JoinColumn(name="id"),inverseJoinColumns=@JoinColumn(name="detinatorId"))
	@JsonIgnoreProperties("user")
	Collection<PlanEveniment> evenimente = new ArrayList<PlanEveniment>();

	public int getId() {
		return id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getParola() {
		return parola;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nume=" + nume + ", email=" + email + ", parola=" + parola + ", rol=" + rol + "]";
	}
	
	
}

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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int id;

	@Column(name = "nume", length = 50, nullable = false)
	public String nume;

	@Column(name = "email", length = 50, nullable = false)
	public String email;

	// SETARE CONSTRANGERI GEN: O LITERA MARE, O CIFRA, ETC;
	@Column(name = "parola", nullable = false)
	public String parola;

	@Column(name = "rol", nullable = false)
	public String rol;

	@OneToMany
	@JoinTable(name = "User_Plan", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "detinatorId"))
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonIgnoreProperties("user")
	Collection<Plan> planuri = new ArrayList<Plan>();

	public String getEmail() {
		return email;
	}

	public int getId() {
		return id;
	}

	public String getNume() {
		return nume;
	}

	public String getParola() {
		return parola;
	}

	public Collection<Plan> getPlanuri() {
		return planuri;
	}

	public String getRol() {
		return rol;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public void setParola(String parola) {
		this.parola = parola;
	}

	public void setPlanuri(Collection<Plan> planuri) {
		this.planuri = planuri;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nume=" + nume + ", email=" + email + ", parola=" + parola + ", rol=" + rol + "]";
	}

}

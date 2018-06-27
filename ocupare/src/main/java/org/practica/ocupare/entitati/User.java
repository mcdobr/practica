package org.practica.ocupare.entitati;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	
	@Column(name = "rol", nullable = false)
	public String rol;

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

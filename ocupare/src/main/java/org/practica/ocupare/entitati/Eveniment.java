package org.practica.ocupare.entitati;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity(name="evenimente")
@Table(name = "evenimente")
public class Eveniment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "plan")
	// @JsonIgnoreProperties("plan")
	// @JsonProperty(value = "defaultaddressid")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
	@JsonIdentityReference(alwaysAsId = true)
	private Plan plan;

	private LocalDateTime inceput;
	private LocalDateTime sfarsit;

	public Eveniment() {
		super();
	}

	public Eveniment(Plan plan, LocalDateTime inceput, LocalDateTime sfarsit) {
		super();
		this.plan = plan;
		this.inceput = inceput;
		this.sfarsit = sfarsit;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public LocalDateTime getInceput() {
		return inceput;
	}

	public void setInceput(LocalDateTime inceput) {
		this.inceput = inceput;
	}

	public LocalDateTime getSfarsit() {
		return sfarsit;
	}

	public void setSfarsit(LocalDateTime sfarsit) {
		this.sfarsit = sfarsit;
	}
}

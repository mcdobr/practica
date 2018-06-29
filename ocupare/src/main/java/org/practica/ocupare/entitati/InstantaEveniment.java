package org.practica.ocupare.entitati;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
public class InstantaEveniment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="planId")
	@JsonIgnoreProperties("planId")
	private PlanEveniment planId;
	
	private LocalDateTime inceput;
	private LocalDateTime sfarsit;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public PlanEveniment getPlanId() {
		return planId;
	}
	public void setPlan(PlanEveniment plan) {
		this.planId = plan;
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

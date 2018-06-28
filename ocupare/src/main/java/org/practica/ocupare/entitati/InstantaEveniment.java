package org.practica.ocupare.entitati;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class InstantaEveniment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="planId")
	private PlanEveniment plan;
	
	private LocalDateTime inceput;
	private LocalDateTime sfarsit;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public PlanEveniment getPlanId() {
		return plan;
	}
	public void setPlan(PlanEveniment plan) {
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

package org.practica.ocupare.entitati;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class InstantaEveniment {
	@Id
	private Integer id;
	private Integer planId;
	private LocalDateTime inceput;
	private LocalDateTime sfarsit;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
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

package org.practica.ocupare.entitati;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PlanEveniment {
	static class Periodicitate {
		
		public enum TipPeriodicitate {
			UNIC, SAPTAMANAL, BISAPTAMANAL, LUNAR, ANUAL, CUSTOM
		}
		
		public TipPeriodicitate tip;
		public List<String> zileProgramate;

		public Periodicitate(TipPeriodicitate tip) {
			super();
			this.tip = tip;
			this.zileProgramate = new ArrayList<String>();
		}
	}
	
	@Id
	private Integer id;
	private String nume;
	//private Periodicitate periodicitate;
	private LocalDate inceput;
	private LocalDate sfarsit;
	private Integer detinatorId;
	private String participanti;
	private String descriere;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	/*public Periodicitate getPeriodicitate() {
		return periodicitate;
	}
	public void setPeriodicitate(Periodicitate periodicitate) {
		this.periodicitate = periodicitate;
	}*/
	public LocalDate getInceput() {
		return inceput;
	}
	public void setInceput(LocalDate inceput) {
		this.inceput = inceput;
	}
	public LocalDate getSfarsit() {
		return sfarsit;
	}
	public void setSfarsit(LocalDate sfarsit) {
		this.sfarsit = sfarsit;
	}
	public Integer getDetinatorId() {
		return detinatorId;
	}
	public void setDetinatorId(Integer detinatorId) {
		this.detinatorId = detinatorId;
	}
	public String getParticipanti() {
		return participanti;
	}
	public void setParticipanti(String participanti) {
		this.participanti = participanti;
	}
	public String getDescriere() {
		return descriere;
	}
	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}
}

package org.practica.ocupare.entitati;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name = "PlanEveniment")
public class PlanEveniment {
	@Embeddable
	public static class Periodicitate {
		public enum TipPeriodicitate {
			UNIC, SAPTAMANAL, BISAPTAMANAL, LUNAR, ANUAL, CUSTOM
		}
		
		public TipPeriodicitate tipPeriodicitate;

		@ElementCollection(fetch=FetchType.EAGER)
		public Collection<String> zileProgramate;
		
		public Periodicitate() {
			super();
			this.tipPeriodicitate = TipPeriodicitate.CUSTOM;
			this.zileProgramate = new ArrayList<>();
		}
		
		public Periodicitate(TipPeriodicitate tipPeriodicitate, Collection<String> zileProgramate) {
			super();
			this.tipPeriodicitate = tipPeriodicitate;
			this.zileProgramate = zileProgramate;
		}

		public Periodicitate(TipPeriodicitate tipPeriodicitate) {
			this(tipPeriodicitate, new ArrayList<String>());
		}
		
		
		public TipPeriodicitate getTipPeriodicitate() {
			return tipPeriodicitate;
		}

		public void setTipPeriodicitate(TipPeriodicitate tipPeriodicitate) {
			this.tipPeriodicitate = tipPeriodicitate;
		}

		public Collection<String> getZileProgramate() {
			return zileProgramate;
		}

		public void setZileProgramate(Collection<String> zileProgramate) {
			this.zileProgramate = zileProgramate;
		}
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "nume", nullable = false)
	private String nume;
	
	@Embedded
	private Periodicitate periodicitate;
	
	@Column(nullable = false)
	private LocalDate inceput;
	private LocalDate sfarsit;
	
	@Column(nullable = false)
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
	public Periodicitate getPeriodicitate() {
		return periodicitate;
	}
	public void setPeriodicitate(Periodicitate periodicitate) {
		this.periodicitate = periodicitate;
	}
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

package org.practica.ocupare.entitati;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nume", nullable = false)
	private String nume;
	
	@Embedded
	private Periodicitate periodicitate;
	
	@Column(nullable = false)
	private LocalDate inceput;
	private LocalDate sfarsit;
	
	private String participanti;
	private String descriere;
	
	@ManyToMany
	@JsonIgnoreProperties("evenimenteList")
	public Collection<Sala> saliList = new ArrayList<>();
	
	@ManyToMany
	@JsonIgnoreProperties("evenimenteList")
	public Collection<Tag> tagList = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="detinatorId")
	private User user;
	
	@OneToMany
	@JoinTable(name="Eveniment_InstanteEveniment",
			joinColumns=@JoinColumn(name="id"),inverseJoinColumns=@JoinColumn(name="planId"))
	@JsonIgnoreProperties("planId")
	Collection<InstantaEveniment> instante_evenimente = new ArrayList<InstantaEveniment>();

	
	public Collection<InstantaEveniment> getInstante_evenimente() {
		return instante_evenimente;
	}
	public void setInstante_evenimente(Collection<InstantaEveniment> instante_evenimente) {
		this.instante_evenimente = instante_evenimente;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public PlanEveniment(String nume, Periodicitate periodicitate, LocalDate inceput, LocalDate sfarsit,
			String participanti, String descriere) {
		super();
		this.nume = nume;
		this.periodicitate = periodicitate;
		this.inceput = inceput;
		this.sfarsit = sfarsit;
		this.participanti = participanti;
		this.descriere = descriere;
		this.saliList = new ArrayList<Sala>();
	}
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
	
	
	public Collection<Sala> getSaliList() {
		return saliList;
	}
	public void setSaliList(Collection<Sala> saliList) {
		this.saliList = saliList;
	}
	
	
	
	public Collection<Tag> getTagList() {
		return tagList;
	}
	public void setTagList(Collection<Tag> tagList) {
		this.tagList = tagList;
	}
	public void adaugaSala(Sala s)
	{
		this.saliList.add(s);
		s.getEvenimenteList().add(this);
	}
	
	public void adaugaTag(Tag t)
	{
		this.tagList.add(t);
		t.getEvenimenteList().add(this);
	}
}

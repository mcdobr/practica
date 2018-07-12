package org.practica.ocupare.entitati;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "planuri")
@Table(name = "planuri")
public class Plan {
	@Embeddable
	public static class Periodicitate {
		public enum TipPeriodicitate {
			UNIC, SAPTAMANAL, BISAPTAMANAL, LUNAR, ANUAL, CUSTOM
		}

		private TipPeriodicitate tipPeriodicitate;

		@ElementCollection(fetch = FetchType.EAGER)
		private Collection<String> zileProgramate;

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
	@LazyCollection(LazyCollectionOption.FALSE)
	//@JsonIgnoreProperties("planuri")
	@JsonIgnore
	public Collection<Sala> sali;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	//@JsonIgnoreProperties("planuri")
	@JsonIgnore
	public Collection<Tag> taguri;

	@ManyToOne
	@JoinColumn(name = "detinatorId")
	private User user;

	@OneToMany
	@JoinTable(name = "Plan_Eveniment", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "plan"))
	//@JsonIgnoreProperties("plan")
	@JsonIgnore
	Collection<Eveniment> evenimente;

	public Plan() {
		this.sali = new ArrayList<>();
		this.taguri = new ArrayList<>();
		this.evenimente = new ArrayList<>();
	}

	public Plan(String nume, Periodicitate periodicitate, LocalDate inceput, LocalDate sfarsit, String participanti,
			String descriere) {
		super();
		this.nume = nume;
		this.periodicitate = periodicitate;
		this.inceput = inceput;
		this.sfarsit = sfarsit;
		this.participanti = participanti;
		this.descriere = descriere;
		this.sali = new ArrayList<>();
		this.taguri = new ArrayList<>();
		this.evenimente = new ArrayList<>();
	}

	public Collection<Eveniment> getEvenimente() {
		return evenimente;
	}

	public void setEvenimente(Collection<Eveniment> evenimente) {
		this.evenimente = evenimente;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Collection<Sala> getSali() {
		return sali;
	}

	public void setSali(Collection<Sala> sali) {
		this.sali = sali;
	}

	public Collection<Tag> getTaguri() {
		return taguri;
	}

	public void setTaguri(Collection<Tag> taguri) {
		this.taguri = taguri;
	}

	public void adaugaSala(Sala s) {
		this.sali.add(s);
		s.getPlanuri().add(this);
	}

	public void adaugaTag(Tag t) {
		this.taguri.add(t);
		t.getPlanuri().add(this);
	}
	
	public void inlaturaSala(Sala s) {
		if (this.sali.contains(s)) {
			this.sali.remove(s);
			s.getPlanuri().remove(this);
		}
	}
	
	public void inlaturaTag(Tag t) {
		if (this.taguri.contains(t)) {
			this.taguri.remove(t);
			t.getPlanuri().remove(this);
		}
	}
}

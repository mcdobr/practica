package org.practica.ocupare.entitati;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name="Eveniment_Tag")
public class EvenimentTag{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int id;

	@Column(name="id_eveniment", nullable=false)
	public int id_eveniment;
	
	@ManyToOne
	public Tag tag;

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_eveniment() {
		return id_eveniment;
	}

	public void setId_eveniment(int id_eveniment) {
		this.id_eveniment = id_eveniment;
	}

	@Override
	public String toString() {
		return "EvenimentTag [id=" + id + ", id_eveniment=" + id_eveniment + "]";
	}

	
}

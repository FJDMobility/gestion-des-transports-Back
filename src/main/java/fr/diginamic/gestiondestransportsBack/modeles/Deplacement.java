package fr.diginamic.gestiondestransportsBack.modeles;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Deplacement {

	public Participant getParticipant() {
		return participant;
	}

	public void setParticipant(Participant participant) {
		this.participant = participant;
	}

	private Integer id;
	private Date dateDepart;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "VOITURE_ID")
	private Voiture voiture;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "PARTICIPANT_ID")
	private Participant participant;

	public Deplacement() {
		// TODO Auto-generated constructor stub
	}

	public Deplacement(Integer id, Date dateDepart) {
		super();
		this.id = id;
		this.dateDepart = dateDepart;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(Date dateDepart) {
		this.dateDepart = dateDepart;
	}

	@Override
	public String toString() {
		return "Deplacement [id=" + id + ", dateDepart=" + dateDepart + "]";
	}

	public Voiture getVoiture() {
		return voiture;
	}

	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}

}

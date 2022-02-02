package fr.diginamic.gestiondestransportsBack.modeles;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "DeplacementSociete")
public class DeplacementSociete extends Deplacement {
	
	@Column(name = "avecChauffeur")
	private boolean avecChauffeur;
	
	@Column(name = "dateArrivee")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateArrivee;

	public DeplacementSociete() {
		super();
	}
	
	public DeplacementSociete(Integer id, Date dateDepart, Voiture voiture, boolean avecChauffeur, Date dateArrivee) {
		super(id, dateDepart, voiture);
		this.avecChauffeur=avecChauffeur;
		this.dateArrivee = dateArrivee;
	}

	public boolean isAvecChauffeur() {
		return avecChauffeur;
	}

	public void setAvecChauffeur(boolean avecChauffeur) {
		this.avecChauffeur = avecChauffeur;
	}

	public Date getDateArrivee() {
		return dateArrivee;
	}

	public void setDateArrivee(Date dateArrivee) {
		this.dateArrivee = dateArrivee;
	}

}

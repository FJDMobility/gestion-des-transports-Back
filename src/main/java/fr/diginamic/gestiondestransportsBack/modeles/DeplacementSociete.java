package fr.diginamic.gestiondestransportsBack.modeles;

import java.util.Date;

public class DeplacementSociete extends Deplacement {

	private boolean avecChauffeur;
	private Date dateArrivee;

	public DeplacementSociete() {
		// TODO Auto-generated constructor stub
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

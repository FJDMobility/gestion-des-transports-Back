package fr.diginamic.gestiondestransportsBack.modeles;

import javax.persistence.Entity;

import fr.diginamic.gestiondestransportsBack.modeles.enums.StatutVoiture;
import fr.diginamic.gestiondestransportsBack.modeles.enums.VoitureCategorie;
@Entity
public class VoitureService extends Voiture {

	private VoitureCategorie categorie;
	private String urlPhoto;
	private StatutVoiture statut;

	public VoitureService() {
		// TODO Auto-generated constructor stub
	}

	public VoitureService(VoitureCategorie categorie, String urlPhoto, StatutVoiture statut) {
		super();
		this.categorie = categorie;
		this.urlPhoto = urlPhoto;
		this.statut = statut;
	}

	public VoitureCategorie getCategorie() {
		return categorie;
	}

	public void setCategorie(VoitureCategorie categorie) {
		this.categorie = categorie;
	}

	public String getUrlPhoto() {
		return urlPhoto;
	}

	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}

	public StatutVoiture getStatut() {
		return statut;
	}

	public void setStatut(StatutVoiture statut) {
		this.statut = statut;
	}

	@Override
	public String toString() {
		return "VoitureService [categorie=" + categorie + ", urlPhoto=" + urlPhoto + ", statut=" + statut + "]";
	}

}

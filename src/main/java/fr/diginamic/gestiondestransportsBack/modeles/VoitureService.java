package fr.diginamic.gestiondestransportsBack.modeles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import fr.diginamic.gestiondestransportsBack.modeles.enums.StatutVoiture;
import fr.diginamic.gestiondestransportsBack.modeles.enums.VoitureCategorie;

@Entity
@Table(name = "VoitureService")
public class VoitureService extends Voiture {
	@Column(name = "categorie")
	@Enumerated(EnumType.STRING)
	private VoitureCategorie categorie;
	@Column(name = "urlPhoto")
	private String urlPhoto;
	
	@Column(name = "statut")
	@Enumerated(EnumType.STRING)
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

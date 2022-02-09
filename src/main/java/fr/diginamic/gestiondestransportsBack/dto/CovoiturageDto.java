package fr.diginamic.gestiondestransportsBack.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import fr.diginamic.gestiondestransportsBack.modeles.Covoiturage;
import fr.diginamic.gestiondestransportsBack.modeles.Participant;
import fr.diginamic.gestiondestransportsBack.modeles.Personne;
import fr.diginamic.gestiondestransportsBack.modeles.User;
import fr.diginamic.gestiondestransportsBack.modeles.Voiture;
import fr.diginamic.gestiondestransportsBack.modeles.enums.RolePerson;

public class CovoiturageDto {

	private Integer id;
	private Date dateDepart;
	private String villeDepart;
	private String villeArrivee;
	private String status;
	private Integer placesDisponibles;
	private Voiture voiture;
	private Personne organisateur;
	private Set<Personne> participant;


	public CovoiturageDto(Covoiturage covoiturage) {
		this.id = covoiturage.getId();
		this.dateDepart = covoiturage.getDateDepart();
		this.villeDepart = covoiturage.getVilleDepart();
		this.villeArrivee = covoiturage.getVilleArrivee();
		this.status = "ouvert";
		this.placesDisponibles = covoiturage.getNbPlacesDisponibles();
		this.voiture = covoiturage.getVoiture();
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

	public String getVilleDepart() {
		return villeDepart;
	}

	public void setVilleDepart(String villeDepart) {
		this.villeDepart = villeDepart;
	}

	public Voiture getVoiture() {
		return voiture;
	}

	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}

	public Personne getOrganisateur() {
		return organisateur;
	}

	public void setOrganisateur(Personne organisateur) {
		this.organisateur = organisateur;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getPlacesDisponibles() {
		return placesDisponibles;
	}

	public void setPlacesDisponibles(Integer placesDisponibles) {
		this.placesDisponibles = placesDisponibles;
	}

	public String getVilleArrivee() {
		return villeArrivee;
	}

	public void setVilleArrivee(String villeArrivee) {
		this.villeArrivee = villeArrivee;
	}

	public Set<Personne> getParticipant() {
		return participant;
	}

	public void setParticipant(Set<Personne> participant) {
		this.participant = participant;
	}

	@Override
	public String toString() {
		return "CovoiturageDTO [id=" + id + ", dateDepart=" + dateDepart + ", villeDepart=" + villeDepart
				+ ", villeArrive=" + villeArrivee + ", voiture=" + voiture + ", Organisateur=" + organisateur
				+ ", passagers=" + participant + "]";
	}
}

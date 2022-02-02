package fr.diginamic.gestiondestransportsBack.dto;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import fr.diginamic.gestiondestransportsBack.modeles.Covoiturage;
import fr.diginamic.gestiondestransportsBack.modeles.Participant;
import fr.diginamic.gestiondestransportsBack.modeles.Personne;
import fr.diginamic.gestiondestransportsBack.modeles.User;
import fr.diginamic.gestiondestransportsBack.modeles.Voiture;
import fr.diginamic.gestiondestransportsBack.modeles.enums.RolePerson;

public class CovoiturageDTO {

	private Integer id;
	private Date dateDepart;
	private String villeDepart;
	private String villeArrive;
	private Integer nbPlacesDisponibles;
	private Voiture voiture;
	private Personne Organisateur;
	private Set<Personne> passagers;


	public CovoiturageDTO(Covoiturage covoiturage, User user) {
		super();
		this.id = covoiturage.getId();
		this.dateDepart = covoiturage.getDateDepart();
		this.villeDepart = covoiturage.getVilleDepart();
		this.villeArrive = covoiturage.getVilleArrivee();
		this.nbPlacesDisponibles = covoiturage.getNbPlacesDisponibles();
		this.voiture = covoiturage.getVoiture();
		this.Organisateur = extractOrganisateur(covoiturage.getParticipants());
		this.passagers = extractPassagers(covoiturage.getParticipants(), user);
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

	public String getVilleArrive() {
		return villeArrive;
	}

	public void setVilleArrive(String villeArrive) {
		this.villeArrive = villeArrive;
	}

	public Voiture getVoiture() {
		return voiture;
	}

	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}

	public Personne getOrganisateur() {
		return Organisateur;
	}

	public void setOrganisateur(Personne organisateur) {
		Organisateur = organisateur;
	}

	public Set<Personne> getPassagers() {
		return passagers;
	}

	public void setPassagers(Set<Personne> passagers) {
		this.passagers = passagers;
	}

	private Personne extractOrganisateur(Set<Participant> participants) {
		return participants.stream().filter(p -> p.getRolePersonne() == RolePerson.ORGANISATEUR)
				.map(p -> p.getPersonne()).findFirst().get();
	}
	
	private Set<Personne> extractPassagers(Set<Participant> participants, User user) {
		return participants.stream().filter(p -> p.getRolePersonne() == RolePerson.PASSAGER)
				.map(p -> p.getPersonne())
				.filter(personne -> personne.getId() != user.getPersonne().getId())
				.collect(Collectors.toSet());
	}

	public Integer getNbPlacesDisponibles() {
		return nbPlacesDisponibles;
	}

	public void setNbPlacesDisponibles(Integer nbPlacesDisponibles) {
		this.nbPlacesDisponibles = nbPlacesDisponibles;
	}

	@Override
	public String toString() {
		return "CovoiturageDTO [id=" + id + ", dateDepart=" + dateDepart + ", villeDepart=" + villeDepart
				+ ", villeArrive=" + villeArrive + ", voiture=" + voiture + ", Organisateur=" + Organisateur
				+ ", passagers=" + passagers + "]";
	}

}

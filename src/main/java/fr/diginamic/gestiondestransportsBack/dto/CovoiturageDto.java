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
import fr.diginamic.gestiondestransportsBack.services.CovoiturageService;

public class CovoiturageDto {

	private Integer id;
	private Date dateDepart;
	private String villeDepart;
	private String villeArrive;
	private String status;
	private Integer placesDisponibles;
	private Voiture voiture;
	private Personne organisateur;
	private Set<Personne> passagers;


	public CovoiturageDto(Covoiturage covoiturage) {
		this.id = covoiturage.getId();
		this.dateDepart = covoiturage.getDateDepart();
		this.villeDepart = covoiturage.getVilleDepart();
		this.villeArrive = covoiturage.getVilleArrivee();
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
		return organisateur;
	}

	public void setOrganisateur(Personne organisateur) {
		this.organisateur = organisateur;
	}

	public Set<Personne> getPassagers() {
		return passagers;
	}

	public void setPassagers(Set<Personne> passagers) {
		this.passagers = passagers;
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

	@Override
	public String toString() {
		return "CovoiturageDTO [id=" + id + ", dateDepart=" + dateDepart + ", villeDepart=" + villeDepart
				+ ", villeArrive=" + villeArrive + ", voiture=" + voiture + ", Organisateur=" + organisateur
				+ ", passagers=" + passagers + "]";
	}
	
	public Personne extractOrganisateur(List<Participant> participants) {
		System.out.println(this.id);
		System.out.println(participants.size() + " participants");
		participants.forEach(p -> System.out.println(p));
		participants.stream().forEach(p -> System.out.println(p.getPersonne().getId() +" " + p.getRolePersonne()));
		return participants.stream().filter(p -> p.getRolePersonne() == RolePerson.ORGANISATEUR)
				.map(p -> p.getPersonne()).findFirst().get();
	}
	
	public Set<Personne> extractPassagers(List<Participant> participants, User user) {
		return participants.stream().filter(p -> p.getRolePersonne() == RolePerson.PASSAGER)
				.map(p -> p.getPersonne())
				.filter(personne -> personne.getId() != user.getPersonne().getId())
				.collect(Collectors.toSet());
	}

	public void setPassagers(List<Participant> participants, User user) {
		this.passagers = extractPassagers(participants, user);		
	}

	public void setOrganisateur(List<Participant> participants) {
		this.organisateur = extractOrganisateur(participants); 
	}

}

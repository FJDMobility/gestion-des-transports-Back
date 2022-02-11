package fr.diginamic.gestiondestransportsBack.services;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.diginamic.gestiondestransportsBack.cruds.CrudCovoiturage;
import fr.diginamic.gestiondestransportsBack.cruds.CrudParticipant;
import fr.diginamic.gestiondestransportsBack.cruds.CrudUser;
import fr.diginamic.gestiondestransportsBack.dto.CovoiturageDto;
import fr.diginamic.gestiondestransportsBack.modeles.Covoiturage;
import fr.diginamic.gestiondestransportsBack.modeles.Participant;
import fr.diginamic.gestiondestransportsBack.modeles.Personne;
import fr.diginamic.gestiondestransportsBack.modeles.enums.RolePerson;
import fr.diginamic.gestiondestransportsBack.security.MyUserDetails;
import fr.diginamic.gestiondestransportsBack.utils.ModeleExtractor;

@Service
@Transactional
public class PassagerService {

	@Autowired
	CrudCovoiturage crudCovoiturage;
	@Autowired
	CrudParticipant crudParticipant;
	@Autowired
	CrudUser crudUser;

	public List<CovoiturageDto> getMesReservations(Authentication authentication) {
		Personne personne = MyUserDetails.getCurrentUser(authentication).getPersonne();
		List<Covoiturage> covoiturages = crudCovoiturage.getCovituragesByPersAndRole(personne, RolePerson.PASSAGER);
		List<CovoiturageDto> covoiturasgeDto = covoiturages.stream()
				.map(covoiturage -> addFullParticipants(covoiturage))
				.map(covoiturage -> mapToCovoiturageDto(covoiturage)).collect(Collectors.toList());
		return covoiturasgeDto;
	}

	public CovoiturageDto getReservationDetails(Authentication authentication, Integer covoiturageId) {
		Covoiturage covoiturage = crudCovoiturage.findById(covoiturageId).get();
		return mapToCovoiturageDto(covoiturage);
	}

	public ResponseEntity<String> annulerReservation(Authentication authentication, Integer covoiturageId) {
		
		// Delete personne from Participant Table 
		Personne currentPersonne = MyUserDetails.getCurrentUser(authentication).getPersonne();
		System.out.println("id Personne : "+ currentPersonne.getId());
		Covoiturage covoiturage = crudCovoiturage.findById(covoiturageId).get();
		System.out.println("id Covoiturage :" +covoiturage.getId() +" "+covoiturage.getVilleDepart());
		crudParticipant.deleteByPersonAndCovoiturage(currentPersonne, covoiturage);
		
		// Update Nb Places disponibles 
		covoiturage.setNbPlacesDisponibles(covoiturage.getNbPlacesDisponibles() + 1);
		crudCovoiturage.save(covoiturage);
		
		return new ResponseEntity<>("Annulation ok! ",HttpStatus.OK);
	}

	public List<CovoiturageDto> searchByVillesAndDate(Authentication authentication, String villeDepart,
			String villeArrivee, Date dateDepart) {
		List<Covoiturage> covoiturages = crudCovoiturage.findByVillesAndDate(villeDepart, villeArrivee, dateDepart);
		List<CovoiturageDto> covoituragesDto = covoiturages.stream().filter(covoiturage -> covoiturage.getNbPlacesDisponibles()>0)
				.map(covoiturage -> mapToCovoiturageDto(covoiturage)).collect(Collectors.toList());
		return covoituragesDto;
	}

	public List<CovoiturageDto> searchByVilles(Authentication authentication, String villeDepart, String villeArrivee) {
		Personne personne = MyUserDetails.getCurrentUser(authentication).getPersonne();
		List<Covoiturage> covoiturages = crudCovoiturage.findByVilles(villeDepart, villeArrivee);
		List<CovoiturageDto> covoituragesDto = covoiturages.stream()
				.filter(covoiturage -> covoiturage.getNbPlacesDisponibles()>0)
				.filter(covoiturage -> filterPersonneDifferentToUser(covoiturage, personne))
				.map(covoiturage -> mapToCovoiturageDto(covoiturage)).collect(Collectors.toList());
		return covoituragesDto;

	}

	public ResponseEntity<String> reserverCovoiturage(Authentication authentication, Integer idCovoiturage) {
		Personne currentPersonne = MyUserDetails.getCurrentUser(authentication).getPersonne();
		Covoiturage covoiturageSelected = crudCovoiturage.findById(idCovoiturage).get(); 
		Participant particpant = new Participant();
		
		// Créer un emplacement dans la table Participant
		particpant.setDeplacement(covoiturageSelected);
		particpant.setPersonne(currentPersonne);
		particpant.setRolePersonne(RolePerson.PASSAGER);
		crudParticipant.save(particpant);
		
		// Réduire le nombre de places disponible 
		covoiturageSelected.setNbPlacesDisponibles(covoiturageSelected.getNbPlacesDisponibles()-1);
		crudCovoiturage.save(covoiturageSelected);
		return new ResponseEntity<>("Resrvation ok! ",HttpStatus.CREATED);
		
	}
	
	private boolean filterPersonneDifferentToUser(Covoiturage covoiturage, Personne personne) {
		return covoiturage.getParticipants().stream().anyMatch(participant -> participant.getPersonne() == personne);
	}

	private CovoiturageDto mapToCovoiturageDto(Covoiturage covoiturage) {
		CovoiturageDto covoiturageDto = new CovoiturageDto(covoiturage);
		covoiturageDto.setParticipant(ModeleExtractor.extractPassagerFromParticipant(covoiturage.getParticipants()));
		covoiturageDto
				.setOrganisateur(ModeleExtractor.extractOrganisateurFromParticipant(covoiturage.getParticipants()));
		return covoiturageDto;
	}

	private Covoiturage addFullParticipants(Covoiturage covoiturage) {
		Set<Participant> participants = crudParticipant.getParticipantByCovoiturage(covoiturage);
		covoiturage.setParticipants(participants);
		return covoiturage;
	}

}

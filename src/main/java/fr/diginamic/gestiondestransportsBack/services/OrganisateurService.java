package fr.diginamic.gestiondestransportsBack.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import fr.diginamic.gestiondestransportsBack.cruds.CrudCovoiturage;
import fr.diginamic.gestiondestransportsBack.cruds.CrudParticipant;
import fr.diginamic.gestiondestransportsBack.cruds.CrudVoiture;
import fr.diginamic.gestiondestransportsBack.dto.CovoiturageDto;
import fr.diginamic.gestiondestransportsBack.exceptions.CovoiturageNotFoundException;
import fr.diginamic.gestiondestransportsBack.modeles.Covoiturage;
import fr.diginamic.gestiondestransportsBack.modeles.Participant;
import fr.diginamic.gestiondestransportsBack.modeles.Personne;
import fr.diginamic.gestiondestransportsBack.modeles.enums.RolePerson;
import fr.diginamic.gestiondestransportsBack.security.MyUserDetails;
import fr.diginamic.gestiondestransportsBack.utils.ModeleExtractor;

@Service
@Transactional
public class OrganisateurService {

	@Autowired
	CrudCovoiturage crudCovoiturage;
	@Autowired
	CrudParticipant crudParticipant;
	@Autowired
	CrudVoiture crudVoiture;

	public List<CovoiturageDto> getMesOffres(Authentication authentication) {
		Personne personne = MyUserDetails.getCurrentUser(authentication).getPersonne();
		List<Covoiturage> covoiturages = crudCovoiturage.getCovituragesByPersAndRole(personne, RolePerson.ORGANISATEUR);
		List<CovoiturageDto> covoiturasgeDto = covoiturages.stream()
				.map(covoiturage -> addFullParticipants(covoiturage))
				.map(covoiturage -> mapToCovoiturageDto(covoiturage)).collect(Collectors.toList());
		return covoiturasgeDto;
	}

	public CovoiturageDto getOffreDetails(Authentication authentication, Integer covoiturageId)
			throws CovoiturageNotFoundException {
		Covoiturage covoiturage = crudCovoiturage.findById(covoiturageId).get();
		return mapToCovoiturageDto(covoiturage);
	}

	public boolean annulerOffre(Authentication authentication, Integer covoiturageId) {
		Covoiturage covoiturage = crudCovoiturage.findById(covoiturageId).get();
		if(covoiturage.getParticipants().size() > 1 ) {
			return false;
		}
		crudParticipant.deleteByCovoiturage(covoiturage);		
		crudCovoiturage.deleteById(covoiturageId);
		return true;
	}
	
	public boolean addNewOffreCovoiturage(Authentication authentication,@RequestBody Covoiturage covoiturage) {
		Personne personne = MyUserDetails.getCurrentUser(authentication).getPersonne();
		crudCovoiturage.save(covoiturage);
		Participant participant = new Participant();
		participant.setDeplacement(covoiturage);
		participant.setPersonne(personne);
		crudParticipant.save(participant);
		return true;
	}
	
	
	public List<VoiturePersonnelle> getMesVoitures(Authentication authentication) {
		Personne personne = MyUserDetails.getCurrentUser(authentication).getPersonne();
		return crudVoiture.findByPersonne(personne);
	}
	
	public VoiturePersonnelle getVoitureDetail(Authentication authentication, Integer idVoiture) {		
		return (VoiturePersonnelle) crudVoiture.findById(idVoiture).get();
	}
	
	public ResponseEntity<String> addNewVoiture(Authentication authentication, @RequestBody VoiturePersonnelle voiture) {
		Personne personne = MyUserDetails.getCurrentUser(authentication).getPersonne();		
		voiture.setPersonne(personne);
		return new ResponseEntity<>("Voiture ajouté", HttpStatus.OK);
		
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

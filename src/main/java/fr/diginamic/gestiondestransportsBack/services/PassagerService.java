package fr.diginamic.gestiondestransportsBack.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.diginamic.gestiondestransportsBack.cruds.CrudCovoiturage;
import fr.diginamic.gestiondestransportsBack.cruds.CrudParticipant;
import fr.diginamic.gestiondestransportsBack.cruds.CrudUser;
import fr.diginamic.gestiondestransportsBack.dto.CovoiturageDto;
import fr.diginamic.gestiondestransportsBack.exceptions.CovoiturageNotFoundException;
import fr.diginamic.gestiondestransportsBack.modeles.Covoiturage;
import fr.diginamic.gestiondestransportsBack.modeles.Participant;
import fr.diginamic.gestiondestransportsBack.modeles.Personne;
import fr.diginamic.gestiondestransportsBack.modeles.User;
import fr.diginamic.gestiondestransportsBack.modeles.enums.RolePerson;
import fr.diginamic.gestiondestransportsBack.security.MyUserDetails;

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
//		User user = MyUserDetails.getCurrentUser(authentication);
		User user = crudUser.findById(2).get();
		List<Covoiturage> covoiturages = crudCovoiturage.getCovituragesByPersAndRole(user.getPersonne(),
				RolePerson.PASSAGER);
		
		 List<CovoiturageDto> covoiturasgeDto = covoiturages.stream().map(covoiturage -> {
			CovoiturageDto covoiturageDto = new CovoiturageDto(covoiturage);
			List<Participant> participants = crudParticipant.getParticipantByCovoiturage(covoiturage);
			covoiturageDto.setPassagers(participants, user);
			covoiturageDto.setOrganisateur(participants);
			return covoiturageDto;
		}).collect(Collectors.toList());
		
		return covoiturasgeDto;
	}

	public CovoiturageDto getReservationDetails(Authentication authentication, Integer covoiturageId)
			throws CovoiturageNotFoundException {

		User user = MyUserDetails.getCurrentUser(authentication);
		Optional<Covoiturage> optionalCovoiturage = crudCovoiturage.findById(covoiturageId);
		if (optionalCovoiturage.isEmpty()) {
			throw new CovoiturageNotFoundException("Covoiturage non trouvé, id : " + covoiturageId);
		}
		Covoiturage covoiturage = optionalCovoiturage.get();
		CovoiturageDto covoiturageDTO = new CovoiturageDto(covoiturage);
		return covoiturageDTO;
	}

	public CovoiturageDto annulerReservation(Authentication authentication, Integer covoiturageId) {
		User currentUser = MyUserDetails.getCurrentUser(authentication);
		Covoiturage covoiturage = crudCovoiturage.findById(covoiturageId).get();
		Personne currentPersonne = currentUser.getPersonne();
		crudParticipant.deleteByPersonAndCovoiturage(currentPersonne, covoiturage);
		covoiturage.setNbPlacesDisponibles(covoiturage.getNbPlacesDisponibles() + 1);
		crudCovoiturage.save(covoiturage);
		CovoiturageDto covoiturageDTO = new CovoiturageDto(covoiturage);
		return covoiturageDTO;
	}
}

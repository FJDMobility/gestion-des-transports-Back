package fr.diginamic.gestiondestransportsBack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.diginamic.gestiondestransportsBack.cruds.CrudCovoiturage;
import fr.diginamic.gestiondestransportsBack.cruds.CrudParticipant;
import fr.diginamic.gestiondestransportsBack.dto.CovoiturageDTO;
import fr.diginamic.gestiondestransportsBack.exceptions.CovoiturageNotFoundException;
import fr.diginamic.gestiondestransportsBack.modeles.Covoiturage;
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

	public List<Covoiturage> getMesReservations(Authentication authentication) {
		User user = MyUserDetails.getCurrentUser(authentication);
		return crudCovoiturage.getCovituragesByPersAndRole(user.getPersonne(), RolePerson.PASSAGER);
	}

	public CovoiturageDTO getReservationDetails(Authentication authentication, Integer covoiturageId)
			throws CovoiturageNotFoundException {

		User user = MyUserDetails.getCurrentUser(authentication);
		Optional<Covoiturage> optionalCovoiturage = crudCovoiturage.findById(covoiturageId);
		if (optionalCovoiturage.isEmpty()) {
			throw new CovoiturageNotFoundException("Covoiturage non trouvé, id : " + covoiturageId);
		}
		Covoiturage covoiturage = optionalCovoiturage.get();
		CovoiturageDTO covoiturageDTO = new CovoiturageDTO(covoiturage, user);
		return covoiturageDTO;
	}

	public CovoiturageDTO annulerReservation(Authentication authentication, Integer covoiturageId) {
		User currentUser = MyUserDetails.getCurrentUser(authentication);
		Covoiturage covoiturage = crudCovoiturage.findById(covoiturageId).get();
		Personne currentPersonne = currentUser.getPersonne();
		crudParticipant.deleteByPersonAndCovoiturage(currentPersonne, covoiturage);		
		covoiturage.setNbPlacesDisponibles(covoiturage.getNbPlacesDisponibles() + 1);
		crudCovoiturage.save(covoiturage);
		CovoiturageDTO covoiturageDTO = new CovoiturageDTO(covoiturage, currentUser);
		return covoiturageDTO;
	}
}

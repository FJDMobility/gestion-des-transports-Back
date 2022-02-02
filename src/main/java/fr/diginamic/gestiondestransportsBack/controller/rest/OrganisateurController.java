package fr.diginamic.gestiondestransportsBack.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.gestiondestransportsBack.dto.CovoiturageDTO;
import fr.diginamic.gestiondestransportsBack.exceptions.CovoiturageNotFoundException;
import fr.diginamic.gestiondestransportsBack.modeles.Covoiturage;
import fr.diginamic.gestiondestransportsBack.services.PassagerService;

@RestController
@CrossOrigin
@RequestMapping("/covoiturage/offre")
public class OrganisateurController {

	
	@Autowired
	PassagerService passagerService;
	
	@GetMapping("all")
	public List<Covoiturage> getMesReservations(Authentication authentication) {		
		List<Covoiturage> covoiturages = passagerService.getMesReservations(authentication);
		return covoiturages;	
	}
	
	@GetMapping("{id}")
	public CovoiturageDTO getReservationDetails(Authentication authentication, @PathVariable("id") Integer covoiturageId) throws CovoiturageNotFoundException {		
		CovoiturageDTO covoiturageDTO = passagerService.getReservationDetails(authentication, covoiturageId);
		return covoiturageDTO;
	}
	
	@DeleteMapping("{id}")
	public CovoiturageDTO deleteReservation(Authentication authentication, @PathVariable("id") Integer covoiturageId) throws CovoiturageNotFoundException {		
		CovoiturageDTO covoiturageDTO = passagerService.annulerReservation(authentication, covoiturageId);
		return covoiturageDTO;
	}
	
	
	
}

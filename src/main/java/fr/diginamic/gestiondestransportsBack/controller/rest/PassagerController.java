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

import fr.diginamic.gestiondestransportsBack.dto.CovoiturageDto;
import fr.diginamic.gestiondestransportsBack.exceptions.CovoiturageNotFoundException;
import fr.diginamic.gestiondestransportsBack.modeles.Covoiturage;
import fr.diginamic.gestiondestransportsBack.services.PassagerService;

@RestController
@CrossOrigin
@RequestMapping("/covoiturage/reservation")
public class PassagerController {

	
	@Autowired
	PassagerService passagerService;
	
	@GetMapping("all")
	public List<CovoiturageDto> getMesReservations(Authentication authentication) {		
		List<CovoiturageDto> covoiturages = passagerService.getMesReservations(authentication);
		return covoiturages;	
	}
	
	@GetMapping("{id}")
	public CovoiturageDto getReservationDetails(Authentication authentication, @PathVariable("id") Integer covoiturageId) throws CovoiturageNotFoundException {		
		CovoiturageDto covoiturageDTO = passagerService.getReservationDetails(authentication, covoiturageId);
		return covoiturageDTO;
	}
	
	@DeleteMapping("{id}")
	public CovoiturageDto deleteReservation(Authentication authentication, @PathVariable("id") Integer covoiturageId) throws CovoiturageNotFoundException {		
		CovoiturageDto covoiturageDTO = passagerService.annulerReservation(authentication, covoiturageId);
		return covoiturageDTO;
	}
	
	
	
}

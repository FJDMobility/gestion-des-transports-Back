package fr.diginamic.gestiondestransportsBack.controller.rest;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.gestiondestransportsBack.dto.CovoiturageDto;
import fr.diginamic.gestiondestransportsBack.exceptions.CovoiturageNotFoundException;
import fr.diginamic.gestiondestransportsBack.modeles.Covoiturage;
import fr.diginamic.gestiondestransportsBack.services.PassagerService;

@RestController
@RequestMapping("/covoiturage/reservation")
@CrossOrigin
public class PassagerController {

	@Autowired
	PassagerService passagerService;

	@GetMapping("all")
	public List<CovoiturageDto> getMesReservations(Authentication authentication) {
		List<CovoiturageDto> covoiturages = passagerService.getMesReservations(authentication);
		return covoiturages;
	}

	@GetMapping("{id}")
	public CovoiturageDto getReservationDetails(Authentication authentication,
			@PathVariable("id") Integer covoiturageId) throws CovoiturageNotFoundException {
		CovoiturageDto covoiturageDTO = passagerService.getReservationDetails(authentication, covoiturageId);
		return covoiturageDTO;
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteReservation(Authentication authentication, @PathVariable("id") Integer covoiturageId)
			throws CovoiturageNotFoundException {
		System.out.println("idCovoiturage to delete : "+covoiturageId);
		return passagerService.annulerReservation(authentication, covoiturageId);
	}

	@GetMapping("/covoiturages")
	public List<CovoiturageDto> findReservationByVillesAndDate(Authentication authentication,@RequestParam(name = "villeDepart", required=true) String villeDepart,
			@RequestParam(name = "villeArrivee", required=true) String villeArrivee, @RequestParam(name ="dateRecherche",required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateDepart) {	
		
		System.out.println("ville depart : "+villeDepart);
		System.out.println("ville arrivee : "+villeArrivee);
		System.out.println("date recherhce : "+dateDepart);
		if (dateDepart != null) {
			return passagerService.searchByVillesAndDate(authentication,villeDepart, villeArrivee, dateDepart);
		}
		return passagerService.searchByVilles(authentication,villeDepart, villeArrivee);
	}
	
	@PostMapping("select/{id}")
	public ResponseEntity<String> selectReservationByIdCovoiturage(Authentication authentication, @PathVariable("id") Integer idCovoiturage) {
		System.out.println("ma reservation dans covoiturage , id : "+idCovoiturage);		
		return passagerService.reserverCovoiturage(authentication,idCovoiturage);
	}
	
}

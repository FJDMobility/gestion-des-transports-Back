package fr.diginamic.gestiondestransportsBack.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.gestiondestransportsBack.dto.CovoiturageDto;
import fr.diginamic.gestiondestransportsBack.exceptions.CovoiturageNotFoundException;
import fr.diginamic.gestiondestransportsBack.modeles.Covoiturage;
import fr.diginamic.gestiondestransportsBack.services.OrganisateurService;
import fr.diginamic.gestiondestransportsBack.services.VoiturePersonnelle;

@RestController
@CrossOrigin
@RequestMapping("/covoiturage/offre")
public class OrganisateurController {

	@Autowired
	OrganisateurService organisateurService;

	@GetMapping("all")
	public List<CovoiturageDto> getMesOffres(Authentication authentication) {
		List<CovoiturageDto> covoituragesDto = organisateurService.getMesOffres(authentication);
		return covoituragesDto;
	}

	@GetMapping("{id}")
	public CovoiturageDto getOffreDetails(Authentication authentication, @PathVariable("id") Integer covoiturageId)
			throws CovoiturageNotFoundException {
		CovoiturageDto covoiturageDTO = organisateurService.getOffreDetails(authentication, covoiturageId);
		return covoiturageDTO;
	}
	
	@GetMapping("/voitures")
	public List<VoiturePersonnelle> getMesVoitures(Authentication authentication) {
		return organisateurService.getMesVoitures(authentication);
	}
	
	@GetMapping("/voiture/{id}")
	public VoiturePersonnelle getVoitureDetails(Authentication authentication, @PathVariable("id") Integer idVoiture) {
		return organisateurService.getVoitureDetail(authentication,idVoiture);
	}
	
	@PostMapping("/voiture/add")
	public ResponseEntity<String> addNewVoiture(Authentication authentication, @RequestBody VoiturePersonnelle voiture) {
		return organisateurService.addNewVoiture(authentication, voiture);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteOffre(Authentication authentication, @PathVariable("id") Integer covoiturageId)
			throws CovoiturageNotFoundException {
		if (organisateurService.annulerOffre(authentication, covoiturageId)) {
			return new ResponseEntity<>("Offre effacé ! ", HttpStatus.OK);
		}
		return new ResponseEntity<>("Offre non effacé", HttpStatus.CONFLICT);
	}

	@PostMapping("add")
	public ResponseEntity<String> addNewOffreCovoiturage(Authentication authentication,
			@RequestBody Covoiturage covoiturage) {

		if (organisateurService.addNewOffreCovoiturage(authentication, covoiturage)) {
			return new ResponseEntity<>("Offre Ajouté ! ", HttpStatus.OK);
		}
		return new ResponseEntity<>("Offre non Ajouté", HttpStatus.CONFLICT);
	}
}

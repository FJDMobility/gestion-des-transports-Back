package fr.diginamic.gestiondestransportsBack.controller.rest;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.gestiondestransportsBack.exceptions.CovoiturageNotFoundException;
import fr.diginamic.gestiondestransportsBack.exceptions.TransportException;
import fr.diginamic.gestiondestransportsBack.modeles.Covoiturage;
import fr.diginamic.gestiondestransportsBack.security.MyUserDetails;
import fr.diginamic.gestiondestransportsBack.services.CovoiturageService;

@RestController
@CrossOrigin
@RequestMapping("/covoiturage")
public class CovoiturageController{

	@Autowired
	CovoiturageService covoiturageService;	
	
	
//	@GetMapping("all")
//	public List<Covoiturage> getAllCovoiturages(Authentication authentication) throws CovoiturageNotFoundException {		
//		return covoiturageService.getAll(authentication);
//	}

	@GetMapping("{id}")
	public Covoiturage getCovoiturageById(@PathVariable("id") Integer id) throws CovoiturageNotFoundException {		
		Covoiturage covoiturage = covoiturageService.getById(id);
		System.out.println(covoiturage.getParticipants());
		return covoiturageService.getById(id);
	}
	

	@PostMapping("/add")
	public Covoiturage addCovoiturage(@Valid @RequestBody Covoiturage covoiturage, BindingResult result) {
		return covoiturageService.addOne(covoiturage);
	}

	@PutMapping("/update/{id}")
	public Covoiturage updateCovoiturage(@PathVariable("id") Integer id,@Valid @RequestBody Covoiturage covoiturage, BindingResult result) throws TransportException {		
		return covoiturageService.updateOne(id,covoiturage);
	}

	@DeleteMapping
	public ResponseEntity<String> deleteCovoiturageById(@PathVariable("id") Integer id) throws CovoiturageNotFoundException {
		return covoiturageService.deleteById(id);
	}
	
	
}

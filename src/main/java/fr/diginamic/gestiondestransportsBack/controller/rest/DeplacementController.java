package fr.diginamic.gestiondestransportsBack.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.gestiondestransportsBack.exceptions.DeplacementNotFoundException;
import fr.diginamic.gestiondestransportsBack.exceptions.TransportException;
import fr.diginamic.gestiondestransportsBack.modeles.Deplacement;
import fr.diginamic.gestiondestransportsBack.services.DeplacementService;

@RestController
@RequestMapping("/api/deplacement")
public class DeplacementController{

	@Autowired
	DeplacementService deplacementService;
	
	@GetMapping("all")
	public List<Deplacement> getAllDeplacements() throws DeplacementNotFoundException {		
		return deplacementService.getAll();
	}

	@GetMapping("{id}")
	public Deplacement getDeplacementById(@PathVariable("id") Integer id) throws DeplacementNotFoundException {		
		return deplacementService.getById(id);
	}

	@PostMapping("/add")
	public Deplacement addDeplacement(@Valid @RequestBody Deplacement deplacement, BindingResult result) {
		return deplacementService.addOne(deplacement);
	}

	@PutMapping("/update/{id}")
	public Deplacement updateDeplacement(@PathVariable("id") Integer id,@Valid @RequestBody Deplacement deplacement, BindingResult result) throws TransportException {		
		return deplacementService.updateOne(id,deplacement);
	}

	@DeleteMapping
	public ResponseEntity<String> deleteDeplacementById(@PathVariable("id") Integer id) throws DeplacementNotFoundException {
		return deplacementService.deleteById(id);
	}
		
}

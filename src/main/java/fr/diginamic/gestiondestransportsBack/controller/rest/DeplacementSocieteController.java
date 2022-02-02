package fr.diginamic.gestiondestransportsBack.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import fr.diginamic.gestiondestransportsBack.exceptions.DeplacementSocieteNotFoundException;
import fr.diginamic.gestiondestransportsBack.exceptions.TransportException;
import fr.diginamic.gestiondestransportsBack.modeles.DeplacementSociete;
import fr.diginamic.gestiondestransportsBack.services.DeplacementSocieteService;

@RestController
@RequestMapping("/api/deplacementSociete")
public class DeplacementSocieteController{

	@Autowired
	DeplacementSocieteService deplacementSocieteService;
	
	@GetMapping("all")
	public List<DeplacementSociete> getAllDeplacementSocietes() throws DeplacementSocieteNotFoundException {		
		return deplacementSocieteService.getAll();
	}

	@GetMapping("{id}")
	public DeplacementSociete getDeplacementSocieteById(@PathVariable("id") Integer id) throws DeplacementSocieteNotFoundException {		
		return deplacementSocieteService.getById(id);
	}

	@PostMapping("/add")
	public DeplacementSociete addDeplacementSociete(@Valid @RequestBody DeplacementSociete deplacementSociete, BindingResult result) {
		return deplacementSocieteService.addOne(deplacementSociete);
	}

	@PutMapping("/update/{id}")
	public DeplacementSociete updateDeplacementSociete(@PathVariable("id") Integer id,@Valid @RequestBody DeplacementSociete deplacementSociete, BindingResult result) throws TransportException {		
		return deplacementSocieteService.updateOne(id,deplacementSociete);
	}

	@DeleteMapping
	public ResponseEntity<String> deleteDeplacementSocieteById(@PathVariable("id") Integer id) throws DeplacementSocieteNotFoundException {
		return deplacementSocieteService.deleteById(id);
	}
		
}

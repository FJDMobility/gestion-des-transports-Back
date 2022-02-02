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

import fr.diginamic.gestiondestransportsBack.exceptions.VoitureNotFoundException;
import fr.diginamic.gestiondestransportsBack.modeles.Voiture;
import fr.diginamic.gestiondestransportsBack.services.VoitureService;

@RestController
@RequestMapping("/api/voiture")
public class VoitureController {

	@Autowired
	VoitureService voitureService;
	
	public VoitureController() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping("/all")
	public List<Voiture> getAllVoitures() throws VoitureNotFoundException {
		return voitureService.getAll();
	}

	@GetMapping("{id}")
	public Voiture getVoitureById(@PathVariable("id") Integer id) throws VoitureNotFoundException {
		return voitureService.getById(id);
		
	}
	
	@PostMapping("/add")
	public Voiture addVoiture(@Valid @RequestBody Voiture voiture, BindingResult result) {
		return voitureService.addOne(voiture);
	}
	
	@PutMapping("/update/{id}")
	public Voiture updateVoiture(@PathVariable("id") Integer id,@Valid @RequestBody Voiture voiture, BindingResult result) throws VoitureNotFoundException {
		return voitureService.updateOne(id, voiture);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteVoitureById(@PathVariable("id") Integer id) throws VoitureNotFoundException {
		return voitureService.deleteById(id);
	}

}

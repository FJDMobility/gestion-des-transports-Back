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

import fr.diginamic.gestiondestransportsBack.exceptions.PersonneNotFoundException;
import fr.diginamic.gestiondestransportsBack.modeles.Deplacement;
import fr.diginamic.gestiondestransportsBack.modeles.Personne;
import fr.diginamic.gestiondestransportsBack.modeles.enums.RolePerson;
import fr.diginamic.gestiondestransportsBack.services.PersonneService;

@RestController
@RequestMapping("/api/personne")
public class PersonneController{

	@Autowired
	PersonneService ps ;

	@GetMapping("/all")
	public List<Personne> getAllPersonnes() throws PersonneNotFoundException{	
		System.out.println("Je rentre");
		return(ps.getAll());		
	}
	
	@GetMapping("{id}")
	public Personne getById(@PathVariable("id") Integer id) throws PersonneNotFoundException {
		return ps.getById(id);
	}

	@PostMapping("/add")
	public Personne addPersonne(@Valid @RequestBody Personne personne, BindingResult result) {
		return ps.addOne(personne);
	}

	@PutMapping("/update/{id}")
	public Personne updateOne(@PathVariable("id") Integer id, @Valid @RequestBody Personne personne,
			BindingResult result) throws PersonneNotFoundException {

		return ps.updateOne(id, personne);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") Integer id) throws PersonneNotFoundException {
		return ps.deleteById(id);
	}

	@GetMapping("/{id}/covoiturages")
	public List<Deplacement> getMesReservations(@PathVariable("id") Integer  id) throws PersonneNotFoundException {
		List<Deplacement> deplacements = ps.getCovoiturageReservations(id, RolePerson.PASSAGER);
		return deplacements;
	}

}

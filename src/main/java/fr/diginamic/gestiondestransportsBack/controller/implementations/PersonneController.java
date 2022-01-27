package fr.diginamic.gestiondestransportsBack.controller.implementations;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import fr.diginamic.gestiondestransportsBack.controller.interfaces.RestControllerInterface;
import fr.diginamic.gestiondestransportsBack.cruds.CrudPersonne;
import fr.diginamic.gestiondestransportsBack.exceptions.PersonneNotFoundException;
import fr.diginamic.gestiondestransportsBack.modeles.Personne;

public class PersonneController implements RestControllerInterface<Personne> {

	@Autowired
	CrudPersonne cd;
	
	public PersonneController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Personne> getAll() throws PersonneNotFoundException {
		List<Personne> personnes = (List<Personne>) cd.findAll();
		if(personnes == null) {
			throw new PersonneNotFoundException("Aucun Déplacement Trouvé");
		}
		return personnes;
	}

	@Override
	public Personne getById(@PathVariable("id") Integer id) throws PersonneNotFoundException {
		
		Optional<Personne> personne = cd.findById(id);
		if(personne.isEmpty() || personne.get() == null) {
			throw new PersonneNotFoundException("Déplacement non trouvé, id : "+id);
		}
		return personne.get();
	}

	@Override
	public Personne addOne(@Valid @RequestBody Personne personne, BindingResult result) {
		return cd.save(personne);
	}

	@Override
	public Personne updateOne(@PathVariable("id") Integer id,@Valid @RequestBody Personne personne, BindingResult result) throws PersonneNotFoundException {
		if(id != personne.getId()) {
			throw new PersonneNotFoundException("Update impossible, Identifiants différents");
		}
		getById(id);
		
		return cd.save(personne);
	}

	@Override
	public ResponseEntity<String> deleteById(@PathVariable("id") Integer id) throws PersonneNotFoundException {
		getById(id);
		deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Compte supprimé");
	}

}

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
import fr.diginamic.gestiondestransportsBack.cruds.CrudDeplacement;
import fr.diginamic.gestiondestransportsBack.exceptions.DeplacementNotFoundExceptions;
import fr.diginamic.gestiondestransportsBack.exceptions.TransportException;
import fr.diginamic.gestiondestransportsBack.modeles.Deplacement;

public class DeplacementController implements RestControllerInterface<Deplacement> {

	@Autowired
	CrudDeplacement cd;
	
	public DeplacementController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Deplacement> getAll() throws DeplacementNotFoundExceptions {
		List<Deplacement> deplacements = (List<Deplacement>) cd.findAll();
		if(deplacements == null) {
			throw new DeplacementNotFoundExceptions("Aucun Déplacement Trouvé");
		}
		return deplacements;
	}

	@Override
	public Deplacement getById(@PathVariable("id") Integer id) throws DeplacementNotFoundExceptions {
		
		Optional<Deplacement> deplacement = cd.findById(id);
		if(deplacement.isEmpty() || deplacement.get() == null) {
			throw new DeplacementNotFoundExceptions("Déplacement non trouvé, id : "+id);
		}
		return deplacement.get();
	}

	@Override
	public Deplacement addOne(@Valid @RequestBody Deplacement deplacement, BindingResult result) {
		return cd.save(deplacement);
	}

	@Override
	public Deplacement updateOne(@PathVariable("id") Integer id,@Valid @RequestBody Deplacement deplacement, BindingResult result) throws TransportException {
		if(id != deplacement.getId()) {
			throw new TransportException("Update impossible, Identifiants différents");
		}
		getById(id);
		
		return cd.save(deplacement);
	}

	@Override
	public ResponseEntity<String> deleteById(@PathVariable("id") Integer id) throws DeplacementNotFoundExceptions {
		getById(id);
		deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Compte supprimé");
	}

}

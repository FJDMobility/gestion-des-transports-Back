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
import fr.diginamic.gestiondestransportsBack.cruds.CrudVoiture;
import fr.diginamic.gestiondestransportsBack.exceptions.VoitureNotFoundException;
import fr.diginamic.gestiondestransportsBack.modeles.Voiture;

public class VoitureController implements RestControllerInterface<Voiture> {

	@Autowired
	CrudVoiture cd;
	
	public VoitureController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Voiture> getAll() throws VoitureNotFoundException {
		List<Voiture> voitures = (List<Voiture>) cd.findAll();
		if(voitures == null) {
			throw new VoitureNotFoundException("Aucun Déplacement Trouvé");
		}
		return voitures;
	}

	@Override
	public Voiture getById(@PathVariable("id") Integer id) throws VoitureNotFoundException {
		
		Optional<Voiture> voiture = cd.findById(id);
		if(voiture.isEmpty() || voiture.get() == null) {
			throw new VoitureNotFoundException("Déplacement non trouvé, id : "+id);
		}
		return voiture.get();
	}

	@Override
	public Voiture addOne(@Valid @RequestBody Voiture voiture, BindingResult result) {
		return cd.save(voiture);
	}

	@Override
	public Voiture updateOne(@PathVariable("id") Integer id,@Valid @RequestBody Voiture voiture, BindingResult result) throws VoitureNotFoundException {
		if(id != voiture.getId()) {
			throw new VoitureNotFoundException("Update impossible, Identifiants différents");
		}
		getById(id);
		
		return cd.save(voiture);
	}

	@Override
	public ResponseEntity<String> deleteById(@PathVariable("id") Integer id) throws VoitureNotFoundException {
		getById(id);
		deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Compte supprimé");
	}

}

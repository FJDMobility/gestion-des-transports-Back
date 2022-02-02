package fr.diginamic.gestiondestransportsBack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.diginamic.gestiondestransportsBack.cruds.CrudDeplacement;
import fr.diginamic.gestiondestransportsBack.exceptions.DeplacementNotFoundException;
import fr.diginamic.gestiondestransportsBack.modeles.Covoiturage;
import fr.diginamic.gestiondestransportsBack.modeles.Deplacement;

@Service
@Transactional
public class DeplacementService implements ApplicationServiceInterface<Deplacement> {

	@Autowired
	CrudDeplacement cp ; 
	
	@Override
	public List<Deplacement> getAll() throws DeplacementNotFoundException {
		List<Deplacement> deplacements = (List<Deplacement>) cp.findAll();
		if (deplacements == null) {
			throw new DeplacementNotFoundException("Aucun deplacement Trouvé");
		}
		return deplacements;
	}

	
	@Override
	public Deplacement getById(Integer id) throws DeplacementNotFoundException {

		Optional<Deplacement> deplacement = cp.findById(id);
		if (deplacement.isEmpty() || deplacement.get() == null) {
			throw new DeplacementNotFoundException("Deplacement non trouvé, id : " + id);
		}
		return deplacement.get();
	}

	@Override
	public Deplacement addOne(Deplacement deplacement) {
		return cp.save(deplacement);
	}

	@Override
	public Deplacement updateOne(Integer id,Deplacement deplacement) throws DeplacementNotFoundException {
		if (id != deplacement.getId()) {
			throw new DeplacementNotFoundException("Update impossible, Identifiants différents");
		}
		getById(id);

		return cp.save(deplacement);
	}

	@Override
	public ResponseEntity<String> deleteById(Integer id) throws DeplacementNotFoundException {
		getById(id);
		cp.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Deplacement supprimé");
	}
	
}

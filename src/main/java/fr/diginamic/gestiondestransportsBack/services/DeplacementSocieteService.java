package fr.diginamic.gestiondestransportsBack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.diginamic.gestiondestransportsBack.cruds.CrudDeplacementSociete;
import fr.diginamic.gestiondestransportsBack.exceptions.DeplacementSocieteNotFoundException;
import fr.diginamic.gestiondestransportsBack.modeles.Covoiturage;
import fr.diginamic.gestiondestransportsBack.modeles.DeplacementSociete;

@Service
@Transactional
public class DeplacementSocieteService implements ApplicationServiceInterface<DeplacementSociete> {

	@Autowired
	CrudDeplacementSociete cp ; 
	
	@Override
	public List<DeplacementSociete> getAll() throws DeplacementSocieteNotFoundException {
		List<DeplacementSociete> deplacementSocietes = (List<DeplacementSociete>) cp.findAll();
		if (deplacementSocietes == null) {
			throw new DeplacementSocieteNotFoundException("Aucun deplacementSociete Trouvé");
		}
		return deplacementSocietes;
	}

	
	@Override
	public DeplacementSociete getById(Integer id) throws DeplacementSocieteNotFoundException {

		Optional<DeplacementSociete> deplacementSociete = cp.findById(id);
		if (deplacementSociete.isEmpty() || deplacementSociete.get() == null) {
			throw new DeplacementSocieteNotFoundException("DeplacementSociete non trouvé, id : " + id);
		}
		return deplacementSociete.get();
	}

	@Override
	public DeplacementSociete addOne(DeplacementSociete deplacementSociete) {
		return cp.save(deplacementSociete);
	}

	@Override
	public DeplacementSociete updateOne(Integer id,DeplacementSociete deplacementSociete) throws DeplacementSocieteNotFoundException {
		if (id != deplacementSociete.getId()) {
			throw new DeplacementSocieteNotFoundException("Update impossible, Identifiants différents");
		}
		getById(id);

		return cp.save(deplacementSociete);
	}

	@Override
	public ResponseEntity<String> deleteById(Integer id) throws DeplacementSocieteNotFoundException {
		getById(id);
		cp.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("DeplacementSociete supprimé");
	}
	
}

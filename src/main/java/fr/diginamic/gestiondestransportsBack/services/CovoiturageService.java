package fr.diginamic.gestiondestransportsBack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.diginamic.gestiondestransportsBack.cruds.CrudCovoiturage;
import fr.diginamic.gestiondestransportsBack.exceptions.CovoiturageNotFoundException;
import fr.diginamic.gestiondestransportsBack.modeles.Covoiturage;

@Service
@Transactional
public class CovoiturageService implements ApplicationServiceInterface<Covoiturage> {

	@Autowired
	CrudCovoiturage cp ; 
	
	@Override
	public List<Covoiturage> getAll() throws CovoiturageNotFoundException {
		List<Covoiturage> covoiturages = (List<Covoiturage>) cp.findAll();
		if (covoiturages == null) {
			throw new CovoiturageNotFoundException("Aucun covoiturage Trouvé");
		}
		return covoiturages;
	}

	
	@Override
	public Covoiturage getById(Integer id) throws CovoiturageNotFoundException {

		Optional<Covoiturage> covoiturage = cp.findById(id);
		if (covoiturage.isEmpty() || covoiturage.get() == null) {
			throw new CovoiturageNotFoundException("Covoiturage non trouvé, id : " + id);
		}
		return covoiturage.get();
	}

	@Override
	public Covoiturage addOne(Covoiturage covoiturage) {
		return cp.save(covoiturage);
	}

	@Override
	public Covoiturage updateOne(Integer id,Covoiturage covoiturage) throws CovoiturageNotFoundException {
		if (id != covoiturage.getId()) {
			throw new CovoiturageNotFoundException("Update impossible, Identifiants différents");
		}
		getById(id);

		return cp.save(covoiturage);
	}

	@Override
	public ResponseEntity<String> deleteById(Integer id) throws CovoiturageNotFoundException {
		getById(id);
		cp.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Covoiturage supprimé");
	}
	
}

package fr.diginamic.gestiondestransportsBack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.diginamic.gestiondestransportsBack.cruds.CrudVoiture;
import fr.diginamic.gestiondestransportsBack.exceptions.VoitureNotFoundException;
import fr.diginamic.gestiondestransportsBack.modeles.Voiture;

@Service
@Transactional
public class VoitureService implements ApplicationServiceInterface<Voiture> {

	@Autowired
	CrudVoiture cp ; 
	
	@Override
	public List<Voiture> getAll() throws VoitureNotFoundException {
		List<Voiture> voitures = (List<Voiture>) cp.findAll();
		if (voitures == null) {
			throw new VoitureNotFoundException("Aucune voiture Trouvée");
		}
		return voitures;
	}

	
	@Override
	public Voiture getById(Integer id) throws VoitureNotFoundException {

		Optional<Voiture> voiture = cp.findById(id);
		if (voiture.isEmpty() || voiture.get() == null) {
			throw new VoitureNotFoundException("Voiture non trouvée, id : " + id);
		}
		return voiture.get();
	}

	@Override
	public Voiture addOne(Voiture voiture) {
		return cp.save(voiture);
	}

	@Override
	public Voiture updateOne(Integer id,Voiture voiture) throws VoitureNotFoundException {
		if (id != voiture.getId()) {
			throw new VoitureNotFoundException("Update impossible, Identifiants différents");
		}
		getById(id);

		return cp.save(voiture);
	}

	@Override
	public ResponseEntity<String> deleteById(Integer id) throws VoitureNotFoundException {
		getById(id);
		cp.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Voiture supprimée");
	}


}

package fr.diginamic.gestiondestransportsBack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.diginamic.gestiondestransportsBack.cruds.CrudPersonne;
import fr.diginamic.gestiondestransportsBack.exceptions.PersonneNotFoundException;
import fr.diginamic.gestiondestransportsBack.modeles.Deplacement;
import fr.diginamic.gestiondestransportsBack.modeles.Personne;
import fr.diginamic.gestiondestransportsBack.modeles.enums.RolePerson;

@Service
@Transactional
public class PersonneService implements ApplicationServiceInterface<Personne> {

	@Autowired
	CrudPersonne cp ; 
	
	@Override
	public List<Personne> getAll() throws PersonneNotFoundException {
		List<Personne> personnes = (List<Personne>) cp.findAll();
		if (personnes == null) {
			throw new PersonneNotFoundException("Aucune Personne Trouvée");
		}
		return personnes;
	}

	
	@Override
	public Personne getById(Integer id) throws PersonneNotFoundException {

		Optional<Personne> personne = cp.findById(id);
		if (personne.isEmpty() || personne.get() == null) {
			throw new PersonneNotFoundException("Personne non trouvée, id : " + id);
		}
		return personne.get();
	}

	@Override
	public Personne addOne(Personne personne) {
		return cp.save(personne);
	}

	@Override
	public Personne updateOne(Integer id,Personne personne) throws PersonneNotFoundException {
		if (id != personne.getId()) {
			throw new PersonneNotFoundException("Update impossible, Identifiants différents");
		}
		getById(id);

		return cp.save(personne);
	}

	@Override
	public ResponseEntity<String> deleteById(Integer id) throws PersonneNotFoundException {
		getById(id);
		cp.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Personne supprimée");
	}


	public List<Deplacement> getCovoiturageReservations(Integer id, RolePerson role) throws PersonneNotFoundException {
		Personne personne = getById(id);
		System.out.println(personne);
		return cp.getAllCovoituragesByIdPerson(id, role);
	}
	
	public List<Deplacement> SearchCovoiturageByTraject(String villeDepart, String villeArrivee){
		return cp.getCovoiturageByTraject(villeDepart, villeArrivee); 
	}


}

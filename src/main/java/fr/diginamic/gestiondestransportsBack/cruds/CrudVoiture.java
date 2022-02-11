package fr.diginamic.gestiondestransportsBack.cruds;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.diginamic.gestiondestransportsBack.modeles.Personne;
import fr.diginamic.gestiondestransportsBack.modeles.Voiture;
import fr.diginamic.gestiondestransportsBack.services.VoiturePersonnelle;

public interface CrudVoiture extends CrudRepository<Voiture, Integer>{

	@Query("SELECT v FROM Voiture v WHERE v.personne=:personne")
	List<VoiturePersonnelle> findByPersonne(Personne personne);
	
}

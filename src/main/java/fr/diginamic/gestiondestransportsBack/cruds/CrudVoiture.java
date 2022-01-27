package fr.diginamic.gestiondestransportsBack.cruds;

import org.springframework.data.repository.CrudRepository;

import fr.diginamic.gestiondestransportsBack.modeles.Voiture;

public interface CrudVoiture extends CrudRepository<Voiture, Integer>{
	
}

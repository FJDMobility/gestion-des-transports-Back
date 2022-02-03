package fr.diginamic.gestiondestransportsBack.cruds;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.diginamic.gestiondestransportsBack.modeles.Covoiturage;
import fr.diginamic.gestiondestransportsBack.modeles.Personne;
import fr.diginamic.gestiondestransportsBack.modeles.enums.RolePerson;

public interface CrudCovoiturage extends CrudRepository<Covoiturage, Integer>{	
	@Query("SELECT c FROM Covoiturage c JOIN FETCH c.participants pa where pa.personne= :personne AND pa.rolePersonne= :rolePerson")
	public List<Covoiturage> getCovituragesByPersAndRole(Personne personne, RolePerson rolePerson);	
}

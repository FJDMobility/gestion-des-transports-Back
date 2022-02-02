package fr.diginamic.gestiondestransportsBack.cruds;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.diginamic.gestiondestransportsBack.modeles.Deplacement;
import fr.diginamic.gestiondestransportsBack.modeles.Participant;
import fr.diginamic.gestiondestransportsBack.modeles.Personne;
import fr.diginamic.gestiondestransportsBack.modeles.enums.RolePerson;

public interface CrudPersonne extends CrudRepository<Personne, Integer>{

	
	@Query("SELECT c FROM Covoiturage c INNER JOIN FETCH c.participants pa INNER JOIN FETCH pa.personne p where p.id = :idPerson AND pa.rolePersonne = :role")
	public List<Deplacement> getAllCovoituragesByIdPerson(Integer idPerson, RolePerson role);
	
	@Query("SELECT c FROM Covoiturage c WHERE c.villeDepart= :villeDepart AND c.villeArrivee = :villeArrivee")
	public List<Deplacement> getCovoiturageByTraject(String villeDepart, String villeArrivee);
	
}

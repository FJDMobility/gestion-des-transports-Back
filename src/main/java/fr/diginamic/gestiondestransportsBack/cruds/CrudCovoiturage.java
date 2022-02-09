package fr.diginamic.gestiondestransportsBack.cruds;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.diginamic.gestiondestransportsBack.modeles.Covoiturage;
import fr.diginamic.gestiondestransportsBack.modeles.Personne;
import fr.diginamic.gestiondestransportsBack.modeles.enums.RolePerson;

public interface CrudCovoiturage extends CrudRepository<Covoiturage, Integer> {
	@Query("SELECT c FROM Covoiturage c JOIN FETCH c.participants pa where pa.personne= :personne AND pa.rolePersonne= :rolePerson")
	public List<Covoiturage> getCovituragesByPersAndRole(Personne personne, RolePerson rolePerson);

	@Query("SELECT c FROM Covoiturage c WHERE lower(c.villeDepart)= lower(:villeDepart) AND lower(c.villeArrivee)= lower(:villeArrivee)   AND c.dateDepart =:dateDepart")
	public List<Covoiturage> findByVillesAndDate(String villeDepart, String villeArrivee, Date dateDepart);
	
	@Query("SELECT c FROM Covoiturage c WHERE lower(c.villeDepart)= lower(:villeDepart) AND lower(c.villeArrivee)= lower(:villeArrivee)")
	public List<Covoiturage> findByVilles(String villeDepart, String villeArrivee);
	
}

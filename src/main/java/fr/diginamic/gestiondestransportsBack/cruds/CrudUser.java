package fr.diginamic.gestiondestransportsBack.cruds;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import fr.diginamic.gestiondestransportsBack.modeles.User;

public interface CrudUser extends CrudRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.username = :username")
	public User getUserByUsername(String username);
}

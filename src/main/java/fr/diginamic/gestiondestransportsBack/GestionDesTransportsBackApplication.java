package fr.diginamic.gestiondestransportsBack;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.diginamic.gestiondestransportsBack.cruds.CrudPersonne;
import fr.diginamic.gestiondestransportsBack.modeles.Personne;

@SpringBootApplication
public class GestionDesTransportsBackApplication implements ApplicationRunner{
	
	@Autowired
	CrudPersonne cp ;
	
	public static void main(String[] args) {
		SpringApplication.run(GestionDesTransportsBackApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		Personne p1 = new Personne("MENTSEUR", "Fares", new Date(88, 0, 28), "mentseur.fares@hotmail.fr");
		Personne p2 = new Personne("MAMA", "AFRICA", new Date(79, 11, 21), "mama.africa@hotmail.fr");
		Personne p3 = new Personne("PAPA", "AFRICA", new Date(111, 1, 2), "papa.africa@hotmail.fr");
		cp.save(p1);
		cp.save(p2);
		cp.save(p3);
		
		
		
		
	}

}

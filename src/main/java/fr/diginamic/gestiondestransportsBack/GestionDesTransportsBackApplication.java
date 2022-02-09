package fr.diginamic.gestiondestransportsBack;

import java.sql.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fr.diginamic.gestiondestransportsBack.cruds.CrudDeplacement;
import fr.diginamic.gestiondestransportsBack.cruds.CrudParticipant;
import fr.diginamic.gestiondestransportsBack.cruds.CrudPersonne;
import fr.diginamic.gestiondestransportsBack.cruds.CrudRole;
import fr.diginamic.gestiondestransportsBack.cruds.CrudUser;
import fr.diginamic.gestiondestransportsBack.cruds.CrudVoiture;
import fr.diginamic.gestiondestransportsBack.modeles.Covoiturage;
import fr.diginamic.gestiondestransportsBack.modeles.Deplacement;
import fr.diginamic.gestiondestransportsBack.modeles.Participant;
import fr.diginamic.gestiondestransportsBack.modeles.Personne;
import fr.diginamic.gestiondestransportsBack.modeles.Role;
import fr.diginamic.gestiondestransportsBack.modeles.User;
import fr.diginamic.gestiondestransportsBack.modeles.Voiture;
import fr.diginamic.gestiondestransportsBack.modeles.enums.RolePerson;
import fr.diginamic.gestiondestransportsBack.services.VoiturePersonnelle;

@SpringBootApplication
public class GestionDesTransportsBackApplication implements ApplicationRunner {

	@Autowired
	CrudPersonne cp;
	@Autowired
	CrudVoiture cv;
	@Autowired 
	CrudDeplacement cd;
	@Autowired 
	CrudParticipant cpa;
	@Autowired 
	CrudRole cr;
	@Autowired
	CrudUser cu;
	
	@Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

	public static void main(String[] args) {
		SpringApplication.run(GestionDesTransportsBackApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Personne p1 = new Personne("MENTSEUR", "Fares", new Date(88, 0, 28), "mentseur.fares@hotmail.fr");
		Personne p2 = new Personne("MAMA", "AFRICA", new Date(79, 11, 21), "mama.africa@hotmail.fr");
		Personne p3 = new Personne("PAPA", "AFRICA", new Date(111, 1, 2), "papa.africa@hotmail.fr");
		Personne p4 = new Personne("MOHAMED", "SALAH", new Date(92, 3, 28), "mohamed.salag@hotmail.fr");
		Personne p5 = new Personne("SADIO", "MANE", new Date(94, 11, 21), "sadio.mane@hotmail.fr");
		Personne p6 = new Personne("HARVEY", "ELLIOT", new Date(199, 1, 2), "harvey.elliot@hotmail.fr");
		Personne p7 = new Personne("CURTIS", "JONES", new Date(111, 6, 6), "curtis.jones@hotmail.fr");
		
		cp.save(p1);
		cp.save(p2);
		cp.save(p3);
		cp.save(p4);
		cp.save(p5);
		cp.save(p6);
		cp.save(p7);

		Voiture v1 = new VoiturePersonnelle(1, "Renault", "Clio", 5, "AV-144-SC", p1);
		Voiture v2 = new VoiturePersonnelle(2, "Peugeot", "507", 5, "AD-44-FC", p2);
		Voiture v3 = new VoiturePersonnelle(3, "Toyota", "Yaris", 4, "XR-65-TR", p3);

		cv.save(v1);
		cv.save(v2);
		cv.save(v3);
		
		Deplacement d1 = new Covoiturage(1, new Date(121,11,24), v1, "Paris", "Montpellier", 5);
		Deplacement d2 = new Covoiturage(2, new Date(121,11,26), v1, "Lyon", "Montpellier", 5);
		Deplacement d3 = new Covoiturage(3, new Date(121,11,29), v3, "Montpellier", "Nimes", 4);
		
		cd.save(d1);
		cd.save(d2);
		cd.save(d3);
		
		
		Participant pa1 = new Participant(1, RolePerson.ORGANISATEUR, p1, d1);
		Participant pa2 = new Participant(2, RolePerson.PASSAGER, p2, d1);
		Participant pa3 = new Participant(3, RolePerson.PASSAGER, p4, d1);
		Participant pa4 = new Participant(4, RolePerson.PASSAGER, p5, d1);
		
		Participant pa5 = new Participant(5, RolePerson.ORGANISATEUR, p1, d2);
		Participant pa6 = new Participant(6, RolePerson.PASSAGER, p3, d2);
		Participant pa7 = new Participant(7, RolePerson.PASSAGER, p6, d2);
		Participant pa8 = new Participant(8, RolePerson.PASSAGER, p7, d2);
		
		Participant pa9 = new Participant(9, RolePerson.ORGANISATEUR, p5, d3);
		Participant pa10 = new Participant(10, RolePerson.PASSAGER, p6, d3);
		Participant pa11 = new Participant(11, RolePerson.PASSAGER, p7, d3);
		Participant pa12 = new Participant(12, RolePerson.PASSAGER, p2, d3);
		
		
		cpa.save(pa1);
		cpa.save(pa2);
		cpa.save(pa3);
		cpa.save(pa4);
		
		cpa.save(pa5);
		cpa.save(pa6);
		cpa.save(pa7);
		cpa.save(pa8);
		
		cpa.save(pa9);
		cpa.save(pa10);
		cpa.save(pa11);
		cpa.save(pa12);
		
		
		Role r1 = new Role(1,"ADMIN");
		Role r2 = new Role(2, "USER");
		
		cr.save(r1);
		cr.save(r2);
		
		User u1 = new User(1,"FaresMt",encoder().encode("faresse"),p1,Set.of(r2),true);		
		User u2 = new User(2,"MamaFrika",encoder().encode("FrikaFrika"),p2,Set.of(r2),true);		
		
		cu.save(u1);
		cu.save(u2);
		
	}

}

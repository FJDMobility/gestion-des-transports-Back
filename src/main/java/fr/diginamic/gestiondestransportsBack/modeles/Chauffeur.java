package fr.diginamic.gestiondestransportsBack.modeles;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Chauffeur")
public class Chauffeur extends Personne {


	public Chauffeur() {
		super();
	}
	
	public Chauffeur(String nom, String prenom, Date dateNaissance, String mail) {
		super(nom, prenom, dateNaissance, mail);
	}

	@Override
	public String toString() {
		return "Chauffeur";
	}

}

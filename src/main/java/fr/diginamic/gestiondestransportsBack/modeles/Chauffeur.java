package fr.diginamic.gestiondestransportsBack.modeles;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Chauffeur")
public class Chauffeur extends Personne {


	public Chauffeur() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Chauffeur";
	}

}

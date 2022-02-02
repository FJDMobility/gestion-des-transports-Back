package fr.diginamic.gestiondestransportsBack.modeles;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "VoiturePersonnelle")
public class VoiturePersonnelle extends Voiture{

	public VoiturePersonnelle() {
		super();
	}
	
	public VoiturePersonnelle(Integer id, String marque, String model, Integer nbPlaces, String immatriculation, Personne personne) {
		super(id, marque, model, nbPlaces,immatriculation, personne);
		
	}

}

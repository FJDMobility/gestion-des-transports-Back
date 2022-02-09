package fr.diginamic.gestiondestransportsBack.services;

import javax.persistence.Entity;
import javax.persistence.Table;

import fr.diginamic.gestiondestransportsBack.modeles.Personne;
import fr.diginamic.gestiondestransportsBack.modeles.Voiture;

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

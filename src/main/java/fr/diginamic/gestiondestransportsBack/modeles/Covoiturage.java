package fr.diginamic.gestiondestransportsBack.modeles;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import fr.diginamic.gestiondestransportsBack.modeles.enums.RolePerson;

@Entity
@Table(name = "covoiturage")
public class Covoiturage extends Deplacement {
	@Column(name = "villeDepart")
	private String villeDepart;
	@Column(name = "villeArrivee")
	private String villeArrivee;
	@Column(name = "nbPlacesDisponibles")
	private Integer nbPlacesDisponibles;

	public Covoiturage() {
		super();
	}

	public Covoiturage(String villeDepart, String villeArrivee, Integer nbPlacesDisponibles) {
		super();
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.nbPlacesDisponibles = nbPlacesDisponibles;
	}

	public Covoiturage(Integer id, Date dateDepart, Voiture voiture, String villeDepart, String villeArrivee,
			Integer nbPlacesDisponibles) {
		super(id, dateDepart, voiture);
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.nbPlacesDisponibles = nbPlacesDisponibles;
	}

	public String getVilleDepart() {
		return villeDepart;
	}

	public void setVilleDepart(String villeDepart) {
		this.villeDepart = villeDepart;
	}

	public String getVilleArrivee() {
		return villeArrivee;
	}

	public void setVilleArrivee(String villeArrivee) {
		this.villeArrivee = villeArrivee;
	}

	public Integer getNbPlacesDisponibles() {
		return nbPlacesDisponibles;
	}

	public void setNbPlacesDisponibles(Integer nbPlacesDisponibles) {
		this.nbPlacesDisponibles = nbPlacesDisponibles;
	}

	@Override
	public String toString() {
		return "Covoiturage [villeDepart=" + villeDepart + ", villeArrivee=" + villeArrivee + ", placesDisponibles="
				+ nbPlacesDisponibles + "]";
	}
	
}

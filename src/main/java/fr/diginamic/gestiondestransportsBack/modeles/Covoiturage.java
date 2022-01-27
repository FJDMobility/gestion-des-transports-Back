package fr.diginamic.gestiondestransportsBack.modeles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "covoiturage")
public class Covoiturage extends Deplacement {
	@Column(name = "villeDepart")
	private String villeDepart;
	@Column(name = "villeArrivee")
	private String villeArrivee;
	@Column(name = "placesDisponibles")
	private Integer placesDisponibles;

	public Covoiturage() {
		// TODO Auto-generated constructor stub
	}

	public Covoiturage(String villeDepart, String villeArrivee, Integer placesDisponibles) {
		super();
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.placesDisponibles = placesDisponibles;
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

	public Integer getPlacesDisponibles() {
		return placesDisponibles;
	}

	public void setPlacesDisponibles(Integer placesDisponibles) {
		this.placesDisponibles = placesDisponibles;
	}

	@Override
	public String toString() {
		return "Covoiturage [villeDepart=" + villeDepart + ", villeArrivee=" + villeArrivee + ", placesDisponibles="
				+ placesDisponibles + "]";
	}

}

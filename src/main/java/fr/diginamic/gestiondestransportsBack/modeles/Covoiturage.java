package fr.diginamic.gestiondestransportsBack.modeles;

public class Covoiturage extends Deplacement {

	private String villeDepart;
	private String villeArrivee;
	private Integer PlacesDisponibles;

	public Covoiturage() {
		// TODO Auto-generated constructor stub
	}

	public Covoiturage(String villeDepart, String villeArrivee, Integer placesDisponibles) {
		super();
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		PlacesDisponibles = placesDisponibles;
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
		return PlacesDisponibles;
	}

	public void setPlacesDisponibles(Integer placesDisponibles) {
		PlacesDisponibles = placesDisponibles;
	}

	@Override
	public String toString() {
		return "Covoiturage [villeDepart=" + villeDepart + ", villeArrivee=" + villeArrivee + ", PlacesDisponibles="
				+ PlacesDisponibles + "]";
	}

}

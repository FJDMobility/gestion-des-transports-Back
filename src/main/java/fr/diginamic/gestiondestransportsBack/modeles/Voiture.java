package fr.diginamic.gestiondestransportsBack.modeles;

import java.util.List;

public class Voiture {
	
	private Integer id;
	private String marque;
	private String model;
	private Integer nbrPlaces;
	private String immatriculation;
	
	// lazy
	private List<Deplacement> deplacement;

	public Voiture() {
		// TODO Auto-generated constructor stub
	}

	public Voiture(Integer id, String marque, String model, Integer nbrPlaces, String immatriculation) {
		super();
		this.id = id;
		this.marque = marque;
		this.model = model;
		this.nbrPlaces = nbrPlaces;
		this.immatriculation = immatriculation;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getNbrPlaces() {
		return nbrPlaces;
	}

	public void setNbrPlaces(Integer nbrPlaces) {
		this.nbrPlaces = nbrPlaces;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	@Override
	public String toString() {
		return "Voiture [id=" + id + ", marque=" + marque + ", model=" + model + ", nbrPlaces=" + nbrPlaces
				+ ", immatriculation=" + immatriculation + "]";
	}

	public List<Deplacement> getDeplacement() {
		return deplacement;
	}

	public void setDeplacement(List<Deplacement> deplacement) {
		this.deplacement = deplacement;
	}

}

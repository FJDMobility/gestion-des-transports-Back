package fr.diginamic.gestiondestransportsBack.modeles;

import java.util.Date;

public class Deplacement {
	
	private Integer id;
	private Date dateDepart;
	
	private Voiture voiture; 

	public Deplacement() {
		// TODO Auto-generated constructor stub
	}

	public Deplacement(Integer id, Date dateDepart) {
		super();
		this.id = id;
		this.dateDepart = dateDepart;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(Date dateDepart) {
		this.dateDepart = dateDepart;
	}

	@Override
	public String toString() {
		return "Deplacement [id=" + id + ", dateDepart=" + dateDepart + "]";
	}

	public Voiture getVoiture() {
		return voiture;
	}

	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}

}

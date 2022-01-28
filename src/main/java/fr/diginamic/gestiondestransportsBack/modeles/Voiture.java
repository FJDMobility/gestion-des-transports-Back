package fr.diginamic.gestiondestransportsBack.modeles;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Voiture")
public class Voiture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	@Column(name = "marque")
	protected String marque;
	@Column(name = "model")
	protected String model;
	@Column(name = "nbPlaces")
	protected Integer nbPlaces;
	@Column(name = "immatriculation")
	protected String immatriculation;

	@OneToMany(mappedBy = "voiture", fetch = FetchType.LAZY)
	protected List<Deplacement> deplacements;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "idPersonne")
	protected Personne personne;

	public Voiture() {
		// TODO Auto-generated constructor stub
	}

	public Voiture(Integer id, String marque, String model, Integer nbrPlaces, String immatriculation) {
		super();
		this.id = id;
		this.marque = marque;
		this.model = model;
		this.nbPlaces = nbrPlaces;
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
		return nbPlaces;
	}

	public void setNbrPlaces(Integer nbrPlaces) {
		this.nbPlaces = nbrPlaces;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public List<Deplacement> getDeplacement() {
		return deplacements;
	}

	public void setDeplacement(List<Deplacement> deplacements) {
		this.deplacements = deplacements;
	}
	
	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}


}

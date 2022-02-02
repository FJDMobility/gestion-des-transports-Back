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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Voiture")
public abstract class Voiture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	@Column(name = "marque")
	protected String marque;
	@Column(name = "model")
	protected String model;
	@Column(name = "nbPlace")
	protected Integer nbPlaces;
	@Column(name = "immatriculation")
	protected String immatriculation;

	@JsonIgnore
	@OneToMany(mappedBy = "voiture", fetch = FetchType.LAZY)
	protected List<Deplacement> deplacements;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "idPersonne")
	protected Personne personne;

	public Voiture() {
		// TODO Auto-generated constructor stub
	}

	public Voiture(Integer id, String marque, String model, Integer nbPlaces, String immatriculation) {
		super();
		this.id = id;
		this.marque = marque;
		this.model = model;
		this.nbPlaces = nbPlaces;
		this.immatriculation = immatriculation;
	}

	public Voiture(Integer id, String marque, String model, Integer nbPlaces, String immatriculation,
			Personne personne) {
		this(id, marque, model, nbPlaces, immatriculation);
		this.personne = personne;
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

	public Integer getNbPlaces() {
		return nbPlaces;
	}

	public void setNbPlaces(Integer nbPlaces) {
		this.nbPlaces = nbPlaces;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public List<Deplacement> getDeplacements() {
		return deplacements;
	}

	public void setDeplacements(List<Deplacement> deplacements) {
		this.deplacements = deplacements;
	}

}

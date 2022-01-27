package fr.diginamic.gestiondestransportsBack.modeles;

import java.util.Date;

public class Personne {
	
	private Integer id;
	private String nom;
	private String prenom;
	private Date dateNaissance;
	private String mail;
	
	public Personne() {
		// TODO Auto-generated constructor stub
	}
	

	public Personne(Integer id, String nom, String prenom, Date dateNaissance, String mail) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.mail = mail;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		return "Personne [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance
				+ ", mail=" + mail + "]";
	}

	
}

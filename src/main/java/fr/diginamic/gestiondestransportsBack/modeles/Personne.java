package fr.diginamic.gestiondestransportsBack.modeles;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Personne")
public class Personne {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;

	@Column(name = "nom")
	protected String nom;

	@Column(name = "prenom")
	protected String prenom;

	@Column(name = "dateNaissance")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected Date dateNaissance;

	@Column(name = "mail")
	protected String mail;

	@JsonIgnore
	@OneToMany(mappedBy = "personne", fetch = FetchType.LAZY, orphanRemoval = true)
	protected Set<Participant> participants;

	@JsonIgnore
	@OneToMany(mappedBy = "personne", fetch = FetchType.LAZY)
	protected Set<Voiture> voitures;

	@JsonIgnore
	@OneToOne(mappedBy = "personne", fetch = FetchType.LAZY)
	protected User user;

	public Personne() {
		// TODO Auto-generated constructor stub
	}

	public Personne(Integer id, String nom, String prenom, Date dateNaissance, String mail) {
		this(nom, prenom, dateNaissance, mail);
		this.id = id;
	}

	public Personne(String nom, String prenom, Date dateNaissance, String mail) {
		super();
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

	public Set<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<Participant> participants) {
		this.participants = participants;
	}

	public Set<Voiture> getVoitures() {
		return voitures;
	}

	public void setVoitures(Set<Voiture> voitures) {
		this.voitures = voitures;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Personne, id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance
				+ ", mail=" + mail + "]";
	}

	@Override
	public boolean equals(Object personne) {
		if (personne == null) {
			return false;
		}

		if (personne.getClass() != this.getClass()) {
			return false;
		}

		final Personne other = (Personne) personne;
		if (this.id == other.getId() && this.nom.equals(other.getNom()) && this.mail.equals(other.getMail())) {
			return true;
		}
		return false;
	}

}

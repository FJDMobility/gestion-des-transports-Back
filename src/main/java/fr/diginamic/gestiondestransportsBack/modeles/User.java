package fr.diginamic.gestiondestransportsBack.modeles;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import fr.diginamic.gestiondestransportsBack.modeles.enums.RoleUser;
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String password;
	
	@OneToOne
	private Personne personne;
	private RoleUser role;

	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(Integer id, RoleUser role) {
		super();
		this.id = id;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoleUser getRole() {
		return role;
	}

	public void setRole(RoleUser role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", role=" + role + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

}

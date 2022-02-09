package fr.diginamic.gestiondestransportsBack.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import fr.diginamic.gestiondestransportsBack.modeles.User;

public class UserDto {
	
	private int id;
	private String nom;
	private String prenom;
	private String email; 
	private Date dateNaissance;
	private List<String> roles;
	
	
	public UserDto() {
		
	}

	public UserDto(User user) {
		this.id = user.getId();
		this.nom = user.getPersonne().getNom();
		this.prenom = user.getPersonne().getPrenom();
		this.email = user.getPersonne().getMail();
		this.dateNaissance = user.getPersonne().getDateNaissance();
		this.roles = user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList());		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	
	
}

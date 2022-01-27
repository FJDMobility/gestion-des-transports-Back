package fr.diginamic.gestiondestransportsBack.modeles;

import fr.diginamic.gestiondestransportsBack.modeles.enums.RolePerson;

public class Participant {
	
	private Integer id;
	private Integer idPersonne;
	private Integer idDeplacement;
	private RolePerson rolePersonne;

	public Participant() {
		// TODO Auto-generated constructor stub
	}

	public Participant(Integer id, Integer idPersonne, Integer idDeplacement, RolePerson rolePersonne) {
		super();
		this.id = id;
		this.idPersonne = idPersonne;
		this.idDeplacement = idDeplacement;
		this.rolePersonne = rolePersonne;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdPersonne() {
		return idPersonne;
	}

	public void setIdPersonne(Integer idPersonne) {
		this.idPersonne = idPersonne;
	}

	public Integer getIdDeplacement() {
		return idDeplacement;
	}

	public void setIdDeplacement(Integer idDeplacement) {
		this.idDeplacement = idDeplacement;
	}

	public RolePerson getRolePersonne() {
		return rolePersonne;
	}

	public void setRolePersonne(RolePerson rolePersonne) {
		this.rolePersonne = rolePersonne;
	}

	@Override
	public String toString() {
		return "Participant [id=" + id + ", idPersonne=" + idPersonne + ", idDeplacement=" + idDeplacement
				+ ", rolePersonne=" + rolePersonne + "]";
	}

}

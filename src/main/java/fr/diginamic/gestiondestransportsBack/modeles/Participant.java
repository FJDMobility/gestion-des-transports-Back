package fr.diginamic.gestiondestransportsBack.modeles;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.diginamic.gestiondestransportsBack.modeles.enums.RolePerson;

@Entity
@Table(name = "Participant")
public class Participant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "idPersonne")
	private Integer idPersonne;
	@Column(name = "idDeplacement")
	private Integer idDeplacement;
	@Column(name = "rolePersonne")
	private RolePerson rolePersonne;
	@OneToMany(mappedBy = "participant", fetch = FetchType.LAZY)
	private Set<Personne> personnes;

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

	
	public Set<Personne> getPersonnes() {
		return personnes;
	}

	public void setPersonnes(Set<Personne> personnes) {
		this.personnes = personnes;
	}

	@Override
	public String toString() {
		return "Participant [id=" + id + ", idPersonne=" + idPersonne + ", idDeplacement=" + idDeplacement
				+ ", rolePersonne=" + rolePersonne + "]";
	}

}

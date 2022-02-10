package fr.diginamic.gestiondestransportsBack.modeles;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.diginamic.gestiondestransportsBack.modeles.enums.RolePerson;
import fr.diginamic.gestiondestransportsBack.modeles.enums.RolePersonConverter;

@Entity
@Table(name = "Participant")
public class Participant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "idDeplacement")
	private Deplacement deplacement;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "idPerson")
	private Personne personne;
	
	@Column(name = "rolePersonne")
	@Convert(converter = RolePersonConverter.class)
	private RolePerson rolePersonne;

	public Participant() {
		// TODO Auto-generated constructor stub
	}

	public Participant(Integer id, RolePerson rolePersonne) {
		super();
		this.id = id;
		this.rolePersonne = rolePersonne;
	}
	
	public Participant(Integer id, RolePerson rolePersonne, Personne personne, Deplacement deplacement) {
		this(id, rolePersonne);
		this.personne = personne;
		this.deplacement = deplacement;
	}
	
	public Participant(RolePerson rolePersonne, Personne personne, Deplacement deplacement) {
		this.rolePersonne = rolePersonne;
		this.personne = personne;
		this.deplacement = deplacement;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RolePerson getRolePersonne() {
		return rolePersonne;
	}

	public void setRolePersonne(RolePerson rolePersonne) {
		this.rolePersonne = rolePersonne;
	}

	public Deplacement getDeplacement() {
		return deplacement;
	}

	public void setDeplacement(Deplacement deplacement) {
		this.deplacement = deplacement;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	@Override
	public String toString() {
		return "Participant [id=" + id + ", deplacement=" + deplacement.getId() + ", personne=" + personne.getId() + ", rolePersonne="
				+ rolePersonne + "]";
	}

}

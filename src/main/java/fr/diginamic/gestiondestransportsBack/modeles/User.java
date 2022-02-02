package fr.diginamic.gestiondestransportsBack.modeles;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import fr.diginamic.gestiondestransportsBack.modeles.enums.RoleUser;

@Entity
@Table(name = "User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;

	@OneToOne
	@JoinColumn(name = "idPersonne", referencedColumnName = "id")
	private Personne personne;
	
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
            )    
    private Set<Role> roles;
    
    @Column(name = "enabled")
    private boolean enabled;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	

	public User(Integer id, String username, String password, Personne personne, Set<Role> roles, boolean enabled) {
		super();
		this.id=id;
		this.username = username;
		this.password = password;
		this.personne = personne;
		this.roles = roles;
		this.enabled = enabled;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", personne=" + personne.getNom() +" " +personne.getPrenom()
				+ ", roles=" + roles.size() + ", enabled=" + enabled + "]";
	}

}

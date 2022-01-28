package fr.diginamic.gestiondestransportsBack.modeles;

import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Deplacement")
public class Deplacement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Integer id;
	
	@Column(name = "dateDepart")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	protected Date dateDepart;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "idVoiture")
	protected Voiture voiture;
	
	@OneToMany(mappedBy = "deplacement", fetch = FetchType.LAZY)
	protected Set<Participant> participants;

	public Deplacement() {
		// TODO Auto-generated constructor stub
	}

	public Deplacement(Integer id, Date dateDepart) {
		super();
		this.id = id;
		this.dateDepart = dateDepart;
	}
	
	public Deplacement(Integer id, Date dateDepart, Voiture voiture) {
		super();
		this.id = id;
		this.dateDepart = dateDepart;
		this.voiture = voiture;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(Date dateDepart) {
		this.dateDepart = dateDepart;
	}

	@Override
	public String toString() {
		return "Deplacement [id=" + id + ", dateDepart=" + dateDepart + "]";
	}

	public Voiture getVoiture() {
		return voiture;
	}

	public void setVoiture(Voiture voiture) {
		this.voiture = voiture;
	}

	public Set<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<Participant> participants) {
		this.participants = participants;
	}

}

package fr.diginamic.gestiondestransportsBack.modeles.enums;

public enum RolePerson {	
	PASSAGER(1),
	ORGANISATEUR(2), 
	CHAUFFEUR(3);
	
	Integer role;
	
	private RolePerson(Integer role) {
		this.role =role;
	}

	public Integer getRole() {
		return role;
	}
}

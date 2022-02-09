package fr.diginamic.gestiondestransportsBack.utils;

import java.util.Set;
import java.util.stream.Collectors;

import fr.diginamic.gestiondestransportsBack.modeles.Participant;
import fr.diginamic.gestiondestransportsBack.modeles.Personne;
import fr.diginamic.gestiondestransportsBack.modeles.enums.RolePerson;

public class ModeleExtractor {

	public static Set<Personne> extractPassagerFromParticipant(Set<Participant> participants) {
		return participants.stream().filter(pa -> pa.getRolePersonne() == RolePerson.PASSAGER)
				.map(pa -> pa.getPersonne()).collect(Collectors.toSet());
	}
	
	public static Personne extractOrganisateurFromParticipant(Set<Participant> participants) {
		return participants.stream().filter(pa -> pa.getRolePersonne() == RolePerson.ORGANISATEUR)
				.map(pa -> pa.getPersonne()).findFirst().get();
	}

}

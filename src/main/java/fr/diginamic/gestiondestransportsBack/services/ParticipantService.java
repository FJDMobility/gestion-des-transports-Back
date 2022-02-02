package fr.diginamic.gestiondestransportsBack.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.diginamic.gestiondestransportsBack.cruds.CrudParticipant;
import fr.diginamic.gestiondestransportsBack.exceptions.ParticipantNotFoundException;
import fr.diginamic.gestiondestransportsBack.modeles.Participant;

@Service
@Transactional
public class ParticipantService implements ApplicationServiceInterface<Participant> {

	@Autowired
	CrudParticipant cp ; 
	
	@Override
	public List<Participant> getAll() throws ParticipantNotFoundException {
		List<Participant> participants = (List<Participant>) cp.findAll();
		if (participants == null) {
			throw new ParticipantNotFoundException("Aucune participant Trouvée");
		}
		return participants;
	}

	
	@Override
	public Participant getById(Integer id) throws ParticipantNotFoundException {

		Optional<Participant> participant = cp.findById(id);
		if (participant.isEmpty() || participant.get() == null) {
			throw new ParticipantNotFoundException("Participant non trouvée, id : " + id);
		}
		return participant.get();
	}

	@Override
	public Participant addOne(Participant participant) {
		return cp.save(participant);
	}

	@Override
	public Participant updateOne(Integer id,Participant participant) throws ParticipantNotFoundException {
		if (id != participant.getId()) {
			throw new ParticipantNotFoundException("Update impossible, Identifiants différents");
		}
		getById(id);

		return cp.save(participant);
	}

	@Override
	public ResponseEntity<String> deleteById(Integer id) throws ParticipantNotFoundException {
		getById(id);
		cp.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Participant supprimée");
	}


}

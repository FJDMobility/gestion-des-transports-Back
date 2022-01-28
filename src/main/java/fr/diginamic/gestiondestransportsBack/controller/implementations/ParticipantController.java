package fr.diginamic.gestiondestransportsBack.controller.implementations;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import fr.diginamic.gestiondestransportsBack.controller.interfaces.RestControllerInterface;
import fr.diginamic.gestiondestransportsBack.cruds.CrudParticipant;
import fr.diginamic.gestiondestransportsBack.exceptions.ParticipantNotFoundExceptions;
import fr.diginamic.gestiondestransportsBack.exceptions.TransportException;
import fr.diginamic.gestiondestransportsBack.modeles.Participant;

public class ParticipantController implements RestControllerInterface<Participant> {

	@Autowired
	CrudParticipant cd;
	
	public ParticipantController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Participant> getAll() throws ParticipantNotFoundExceptions {
		List<Participant> participants = (List<Participant>) cd.findAll();
		if(participants == null) {
			throw new ParticipantNotFoundExceptions("Aucun Déplacement Trouvé");
		}
		return participants;
	}

	@Override
	public Participant getById(@PathVariable("id") Integer id) throws ParticipantNotFoundExceptions {
		
		Optional<Participant> participant = cd.findById(id);
		if(participant.isEmpty() || participant.get() == null) {
			throw new ParticipantNotFoundExceptions("Déplacement non trouvé, id : "+id);
		}
		return participant.get();
	}

	@Override
	public Participant addOne(@Valid @RequestBody Participant participant, BindingResult result) {
		return cd.save(participant);
	}

	@Override
	public Participant updateOne(@PathVariable("id") Integer id,@Valid @RequestBody Participant participant, BindingResult result) throws TransportException {
		if(id != participant.getId()) {
			throw new TransportException("Update impossible, Identifiants différents");
		}
		getById(id);
		
		return cd.save(participant);
	}

	@Override
	public ResponseEntity<String> deleteById(@PathVariable("id") Integer id) throws ParticipantNotFoundExceptions {
		getById(id);
		deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Compte supprimé");
	}

}

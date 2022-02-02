package fr.diginamic.gestiondestransportsBack.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.gestiondestransportsBack.exceptions.ParticipantNotFoundException;
import fr.diginamic.gestiondestransportsBack.exceptions.TransportException;
import fr.diginamic.gestiondestransportsBack.modeles.Participant;
import fr.diginamic.gestiondestransportsBack.services.ParticipantService;

@RestController
@RequestMapping("/api/participant")
public class ParticipantController {

	@Autowired
	ParticipantService ps;
	
	public ParticipantController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping("all")
	public List<Participant> getAllParticipants() throws ParticipantNotFoundException {
		return ps.getAll();
	}
	
	@GetMapping("{id}")
	public Participant getParticipantById(@PathVariable("id") Integer id) throws ParticipantNotFoundException {
		return ps.getById(id);
	}

	@PostMapping("add")
	public Participant addParticipant(@Valid @RequestBody Participant participant, BindingResult result) {
		return ps.addOne(participant);
	}

	@PutMapping("update/{id}")
	public Participant updateParticipant(@PathVariable("id") Integer id,@Valid @RequestBody Participant participant, BindingResult result) throws TransportException {
		return ps.updateOne(id, participant);
	}

	@DeleteMapping
	public ResponseEntity<String> deleteParticipantById(@PathVariable("id") Integer id) throws ParticipantNotFoundException {
		return ps.deleteById(id);
	}

}

package fr.diginamic.gestiondestransportsBack.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import fr.diginamic.gestiondestransportsBack.exceptions.DeplacementNotFoundException;
import fr.diginamic.gestiondestransportsBack.exceptions.ParticipantNotFoundException;
import fr.diginamic.gestiondestransportsBack.exceptions.PersonneNotFoundException;
import fr.diginamic.gestiondestransportsBack.exceptions.VoitureNotFoundException;

@RestControllerAdvice
public class ErrorController {

//	@ExceptionHandler(value = { Exception.class })
//	public ResponseEntity<String> onError(Exception ex) {
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Other error : " + ex.getMessage());
//	}

	@ExceptionHandler(value = { DeplacementNotFoundException.class })
	public ResponseEntity<String> onErrorDeplacement(DeplacementNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Other error : " + ex.getMessage());
	}

	@ExceptionHandler(value = { ParticipantNotFoundException.class })
	public ResponseEntity<String> onErrorDeplacement(ParticipantNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Other error : " + ex.getMessage());
	}

	@ExceptionHandler(value = { PersonneNotFoundException.class })
	public ResponseEntity<String> onErrorDeplacement(PersonneNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Other error : " + ex.getMessage());
	}
	
	@ExceptionHandler(value = {VoitureNotFoundException.class})
	 public ResponseEntity<String> onErrorDeplacement(VoitureNotFoundException ex) {
     return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Other error : " + ex.getMessage());
 }
}

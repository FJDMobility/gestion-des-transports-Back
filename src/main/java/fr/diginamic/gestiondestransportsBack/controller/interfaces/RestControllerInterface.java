package fr.diginamic.gestiondestransportsBack.controller.interfaces;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import fr.diginamic.gestiondestransportsBack.exceptions.TransportException;

public interface RestControllerInterface<T> {
	
	@GetMapping("all")
	public List<T> getAll() throws TransportException;
	
	@GetMapping("{id}")
	public T getById(@PathVariable("id") Integer id) throws TransportException;
	
	@PostMapping
	public T addOne(@Valid @RequestBody T object, BindingResult result);
	
	@PutMapping("{id}")
	public T updateOne(@PathVariable("id") Integer id, @Valid @RequestBody T object, BindingResult result) throws TransportException;
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") Integer id) throws TransportException;


	
}
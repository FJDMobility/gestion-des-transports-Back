package fr.diginamic.gestiondestransportsBack.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import fr.diginamic.gestiondestransportsBack.exceptions.TransportException;

public interface ApplicationServiceInterface<T> {

	public List<T> getAll() throws TransportException;
	
	public T getById(Integer id) throws TransportException;
	
	public T addOne( T object);
	
	public T updateOne(Integer id,T object) throws TransportException;
	
	public ResponseEntity<String> deleteById (Integer id) throws TransportException;
}

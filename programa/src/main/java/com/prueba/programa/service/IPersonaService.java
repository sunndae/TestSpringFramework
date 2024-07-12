package com.prueba.programa.service;

import com.prueba.programa.entity.Persona;
import java.util.List;
import java.util.Optional;

public interface IPersonaService {
	
	List<Persona> findAll();
	
	Optional<Persona> findById(Long id);
	
	Persona save(Persona persona);
	
	void delete(Persona persona);
	
	void deleteById(Long id);
}

package com.prueba.programa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.programa.entity.Persona;
import com.prueba.programa.repository.PersonaRepository;

@Service
public class ImplPersonaService implements IPersonaService {

	@Autowired
	private PersonaRepository personaRepository;
	
	@Override
	public List<Persona> findAll() { // buscar todos los objetos personas y los deja en una lista
		List<Persona> persona = personaRepository.findAll();
		return persona;
	}

	@Override
	public Optional<Persona> findById(Long id) { // busca UN objeto por medio del ID
		Optional<Persona> persona = personaRepository.findById(id);
		return persona;
	}

	@Override
	public Persona save(Persona persona) { // crea los objetos
		Persona persona1 = personaRepository.save(persona);
		return persona1;
	}

	@Override
	public void delete(Persona persona) { // Elimina un objeto de tipo persona
		personaRepository.delete(persona);
	}

	@Override
	public void deleteById(Long id) { // Elimina un objeto de tipo persona mediante el ID
		personaRepository.deleteById(id);
		
	}

}

package com.prueba.programa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.programa.dto.PersonaDTO;
import com.prueba.programa.entity.Persona;
import com.prueba.programa.repository.PersonaRepository;

@Service
public class ImplPersonaService implements IPersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public List<PersonaDTO> findAll() {
        List<Persona> TodosLasPersonas = (List<Persona>) personaRepository.findAll();
        List<PersonaDTO> listDto = new ArrayList<PersonaDTO>();
        for (Persona personaDTO : TodosLasPersonas) {
            listDto.add(personaDTO.ToDTO());
        }
        return listDto;
    }

    @Override
    public Optional<PersonaDTO> findById(Long id) { // busca UN objeto por medio del ID
        Optional<Persona> persona = personaRepository.findById(id);
        Persona personaObtener = persona.get();
        Optional<PersonaDTO> personaDTO = Optional.ofNullable(personaObtener.ToDTO());
        return personaDTO;
    }

    @Override
    public PersonaDTO save(PersonaDTO personaDTO) { // crea los objetos
        Persona persona = personaRepository.save(personaDTO.ToEntity());
        return persona.ToDTO();
    }

    @Override
    public void delete(PersonaDTO personaDTO) { // Elimina un objeto de tipo persona
        Persona persona = personaDTO.ToEntity();
        personaRepository.delete(persona);
    }

    @Override
    public void deleteById(Long id) { // Elimina un objeto de tipo persona mediante el ID
        personaRepository.deleteById(id);

    }

}
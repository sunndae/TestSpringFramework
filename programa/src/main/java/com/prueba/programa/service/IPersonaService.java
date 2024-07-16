package com.prueba.programa.service;

import com.prueba.programa.dto.PersonaDTO;
import java.util.List;
import java.util.Optional;

public interface IPersonaService {

    List<PersonaDTO> findAll();

    Optional<PersonaDTO> findById(Long id);

    PersonaDTO save(PersonaDTO personaDTO);

    void delete(PersonaDTO personaDTO);

    void deleteById(Long id);
}

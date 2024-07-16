package com.prueba.frontend.service;

import java.util.List;

import com.prueba.frontend.dto.PersonaDTO;

public interface IPersonaService {
	
    public List<PersonaDTO> findAllREST();

    public PersonaDTO findByIdREST(Long id);

    public PersonaDTO saveREST(PersonaDTO personaDTO);

    public PersonaDTO updateREST(Long id, PersonaDTO personaDTO);

    public PersonaDTO deleteREST(Long id);
}

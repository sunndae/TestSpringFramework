package com.prueba.frontend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import com.prueba.frontend.dto.PersonaDTO;

@Service
public class ImplPersonaService implements IPersonaService{
	

    @Override
    public List<PersonaDTO> findAllREST() {
        try {
            ObjectMapper unMapper = new ObjectMapper();

            List<PersonaDTO> empleados = Arrays
                    .asList(unMapper.readValue(new URL("http://localhost:8010/api/persona/findAll"), PersonaDTO[].class));
            return empleados;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public PersonaDTO findByIdREST(Long id) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<PersonaDTO> responseEntity = restTemplate
                    .getForEntity("http://localhost:8010/api/persona/findById" + "/" + id, PersonaDTO.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                PersonaDTO dto = responseEntity.getBody();
                return dto;
            } else {
                System.out.println("A ocurrido un error");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public PersonaDTO saveREST(PersonaDTO PersonaDTO) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<PersonaDTO> requestEntity = new HttpEntity<>(PersonaDTO, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<PersonaDTO> responseEntity = restTemplate.postForEntity("http://localhost:8010/api/persona/create",
                    requestEntity, PersonaDTO.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                PersonaDTO dto = responseEntity.getBody();
                return dto;
            } else {
                System.out.println("A ocurrido un error");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public PersonaDTO updateREST(Long id, PersonaDTO PersonaDTO) { // toma lo que tengo almacenado y lo actualiza segun lo que necesite cambiar
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<PersonaDTO> requestEntity = new HttpEntity<>(PersonaDTO, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<PersonaDTO> responseEntity = restTemplate.exchange(
                    "http://localhost:8010/api/persona/update" + "/" + id,
                    HttpMethod.PUT,
                    requestEntity,
                    PersonaDTO.class
            );

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                PersonaDTO dto = responseEntity.getBody();
                return dto;
            } else {
                System.out.println("Ha ocurrido un error");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public PersonaDTO deleteREST(Long id) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<PersonaDTO> responseEntity = restTemplate
                    .getForEntity("http://localhost:8010/api/persona/findById" + "/" + id, PersonaDTO.class); // buscara el objeto que quiero borrar y me trae todo lo suyo

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                PersonaDTO dto = responseEntity.getBody();

                restTemplate.delete("http://localhost:8010/api/persona/deleteById" + "/" + id); // si es que existe lo borra

                return dto;
            } else {
                System.out.println("A ocurrido un error");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

package com.prueba.frontend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import com.prueba.frontend.dto.PersonaDTO;

public class ImplPersonaService implements IPersonaService{
	
	
    @Override
    public List<PersonaDTO> findAllREST() {
        try {
            ObjectMapper unMapper = new ObjectMapper();

            List<PersonaDTO> empleados = Arrays
                    .asList(unMapper.readValue(new URL(""), PersonaDTO[].class));
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
                    .getForEntity("" + "/" + id, PersonaDTO.class);

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
            ResponseEntity<PersonaDTO> responseEntity = restTemplate.postForEntity("",
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
    public PersonaDTO updateREST(Long id, PersonaDTO PersonaDTO) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<PersonaDTO> requestEntity = new HttpEntity<>(PersonaDTO, headers);

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<PersonaDTO> responseEntity = restTemplate.exchange(
                    "" + "/" + id,
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
                    .getForEntity("" + "/" + id, PersonaDTO.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                PersonaDTO dto = responseEntity.getBody();

                restTemplate.delete("" + "/" + id);

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

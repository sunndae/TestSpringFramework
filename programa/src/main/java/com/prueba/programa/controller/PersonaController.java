package com.prueba.programa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prueba.programa.dto.PersonaDTO;
import com.prueba.programa.service.IPersonaService;


@Controller
@RequestMapping("/api/persona")
public class PersonaController {

	@Autowired
	private IPersonaService PersonaService; // inyección del servicio
	
	@ResponseBody
	@GetMapping("/findAll")
	public List<PersonaDTO> listAllPersonaDTOs(){
		List<PersonaDTO> PersonaDTO = PersonaService.findAll();
		return PersonaDTO;
	}
	
	
	@ResponseBody
	@PostMapping("/create") // url create 
	public PersonaDTO crearPersonaDTO(@RequestBody PersonaDTO personaDTO){ // metodo que devuelve un objeto PersonaDTO
		return PersonaService.save(personaDTO); // con esto ya está listo el metodo.
	}
	
	@ResponseBody
	@DeleteMapping("/delete") // url para borrar
	public void eliminarPersonaDTO(@RequestBody PersonaDTO personaDTO) {
		PersonaService.delete(personaDTO);
	}
	
	@ResponseBody
	@DeleteMapping("/deleteById/{id}") // url para borrar 
	public void eliminarPersonaDTOPorID(@PathVariable Long id) {
		PersonaService.deleteById(id);
	}
	
	@ResponseBody
	@PutMapping("/update/{id}")
	public ResponseEntity<PersonaDTO> updatePersonaDTO(@PathVariable Long id, @RequestBody PersonaDTO personaDTO) { // devuelve un codigo HTTP indicando el caso. (200, 400, 404, 500)
	    Optional<PersonaDTO> PersonaDTOParaActualizar = PersonaService.findById(id); // buscaremos la PersonaDTO por el Id
	    if (PersonaDTOParaActualizar.isPresent()) { // verifica si el objeto existe 
	        PersonaDTO PersonaDTOActualizada = PersonaDTOParaActualizar.get();// en caso de existir, esta es la instancia para poder actualizar 
	        if (personaDTO.getName() != null) { // chequea que no haya un campo nulo en el nombre
	        	PersonaDTOActualizada.setName(personaDTO.getName()); // de no ser asi, lo actualiza 
	        }
	        if (personaDTO.getLastname() != null) {
	        	PersonaDTOActualizada.setLastname(personaDTO.getLastname());// de no ser asi, lo actualiza 
	        }
	        if (personaDTO.getAge() != null) {
	        	PersonaDTOActualizada.setAge(personaDTO.getAge());// de no ser asi, lo actualiza 
	        }
	        PersonaDTO updatedPersonaDTO = PersonaService.save(PersonaDTOActualizada); // creamos el objeto para poder guardarlo en el servicio
 	        return ResponseEntity.ok(updatedPersonaDTO); // si cumple todo lo anterior, devuelve un 200, todo ok y devuelve el objeto actualizado
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // caso contrario, retorna otro error
	    }
	}
}
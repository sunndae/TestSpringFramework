package com.prueba.programa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prueba.programa.entity.Persona;

import com.prueba.programa.service.IPersonaService;


@Controller
@RequestMapping("/api/persona")
public class PersonaController {

	@Autowired
	private IPersonaService personaService; // inyección del servicio
	
	@ResponseBody
	@GetMapping("/findAll")
	public List<Persona> listAllPersonas(){
		List<Persona> persona = personaService.findAll();
		return persona;
	}
	
	@GetMapping("/Index")
	public String Pagina(Model model){ 
		List<Persona> persona = personaService.findAll();
		model.addAttribute("personas",persona);
		Optional<Persona> busqueda = personaService.findById(2L); // busca el id 1 
		model.addAttribute("buscar", busqueda); // yo voy a usar lo que busque en la BDD y lo guardé en busqueda
		return "Index";
	}
	
	@ResponseBody
	@PostMapping("/create") // url create 
	public Persona crearPersona(@RequestBody Persona persona){ // metodo que devuelve un objeto persona
		return personaService.save(persona); // con esto ya está listo el metodo.
	}
	
	@ResponseBody
	@DeleteMapping("/delete") // url para borrar
	public void eliminarPersona(@RequestBody Persona persona) {
		personaService.delete(persona);
	}
	
	@ResponseBody
	@DeleteMapping("/deleteById/{id}") // url para borrar 
	public void eliminarPersonaPorID(@PathVariable Long id) {
		personaService.deleteById(id);
	}
	
	@ResponseBody
	@PutMapping("/update/{id}")
	public ResponseEntity<Persona> updatePersona(@PathVariable Long id, @RequestBody Persona persona) { // devuelve un codigo HTTP indicando el caso. (200, 400, 404, 500)
	    Optional<Persona> personaParaActualizar = personaService.findById(id); // buscaremos la persona por el Id
	    if (personaParaActualizar.isPresent()) { // verifica si el objeto existe 
	        Persona personaActualizada = personaParaActualizar.get();// en caso de existir, esta es la instancia para poder actualizar 
	        if (persona.getName() != null) { // chequea que no haya un campo nulo en el nombre
	        	personaActualizada.setName(persona.getName()); // de no ser asi, lo actualiza 
	        }
	        if (persona.getLastname() != null) {
	        	personaActualizada.setLastname(persona.getLastname());// de no ser asi, lo actualiza 
	        }
	        if (persona.getAge() != null) {
	        	personaActualizada.setAge(persona.getAge());// de no ser asi, lo actualiza 
	        }
	        Persona updatedPersona = personaService.save(personaActualizada); // creamos el objeto para poder guardarlo en el servicio
 	        return ResponseEntity.ok(updatedPersona); // si cumple todo lo anterior, devuelve un 200, todo ok y devuelve el objeto actualizado
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // caso contrario, retorna otro error
	    }
	}
}


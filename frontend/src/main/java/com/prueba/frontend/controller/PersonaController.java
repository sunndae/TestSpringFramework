package com.prueba.frontend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.prueba.frontend.dto.PersonaDTO;
import com.prueba.frontend.service.IPersonaService;


@Controller
@RequestMapping("/api/persona")
@CrossOrigin(origins = "http://localhost:8011")
public class PersonaController {
	
	@Autowired
	private IPersonaService personaService; // inyección del servicio
	
	@GetMapping("/Index")
	public String Pagina(Model model){ 
		List<PersonaDTO> persona = personaService.findAllREST();
		model.addAttribute("personas",persona); 
		//PersonaDTO busqueda = personaService.findByIdREST(1L); // busca el id 1 
		//model.addAttribute("buscar", busqueda); // yo voy a usar lo que busque en la BDD y lo guardé en busqueda
		return "Index";
	}
	

	@GetMapping("/editar/{id}")
	public String editarREST(@PathVariable Long id, Model model){
	    PersonaDTO persona = personaService.findByIdREST(id);
	    model.addAttribute("personaEditar", persona);
	    return "form";
	}
	
	@GetMapping("/crearPersona")
	public String crearPersona(Model model){
		PersonaDTO persona = new PersonaDTO();
		model.addAttribute("Persona", persona);
		return "createPersona"; // pagina que se va a mostrar, apunta al HTML que tiene toda la pagina.
	}
	@PostMapping("/update")
	public String actualizarPersona(@ModelAttribute PersonaDTO personaDTO) {
	    personaService.updateREST(personaDTO.getId(), personaDTO);
	    
	    return "redirect:/api/persona/Index";
	}
	
	@PostMapping("/create")
	public String createPersona(@ModelAttribute PersonaDTO personaDTO) {
	    personaService.saveREST(personaDTO);
	    return "redirect:/api/persona/Index";
	}
	
	@DeleteMapping("/deleteById/{id}")
	public String eliminarPersona(@PathVariable Long id) {
		personaService.deleteREST(id);
		return "redirect:/api/persona/Index";
	}
}
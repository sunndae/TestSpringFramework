package com.prueba.frontend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.prueba.frontend.dto.PersonaDTO;
import com.prueba.frontend.service.IPersonaService;


@Controller
@RequestMapping("/api/persona")
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
}

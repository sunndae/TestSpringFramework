package com.prueba.programa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prueba.programa.entity.Persona;

import com.prueba.programa.service.IPersonaService;


@Controller
@RequestMapping("/api/persona")
public class PersonaController {

	@Autowired
	private IPersonaService personaService;
	
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
		return "Index";
	}
	
}

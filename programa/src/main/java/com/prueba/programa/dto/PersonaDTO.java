package com.prueba.programa.dto;

import com.prueba.programa.entity.Persona;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data
public class PersonaDTO { // aqui van todos los atributos de nuestra entidad 
	
	private Long id;
	private String name;
	private String Lastname;
	private Long age;
	
	public Persona ToEntity() {
		Persona persona = new Persona();
		persona.setId(this.getId());
		persona.setName(this.getName());
		persona.setLastname(this.getLastname());
		persona.setAge(this.getAge());
		return persona;
	}
}
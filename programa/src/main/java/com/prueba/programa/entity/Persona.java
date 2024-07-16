package com.prueba.programa.entity;

import java.time.LocalDate;

import com.prueba.programa.dto.PersonaDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Personas")
public class Persona {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String Lastname;
	private Long age;
	
	public PersonaDTO ToDTO() {
		PersonaDTO personaDTO = new PersonaDTO();
		personaDTO.setId(this.getId());
		personaDTO.setName(this.getName());
		personaDTO.setLastname(this.getLastname());
		personaDTO.setAge(this.getAge());
		return personaDTO;
	}
}

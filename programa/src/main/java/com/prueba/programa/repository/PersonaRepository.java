package com.prueba.programa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.programa.entity.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona,Long>{
	//@Query()
	//Long id;
}

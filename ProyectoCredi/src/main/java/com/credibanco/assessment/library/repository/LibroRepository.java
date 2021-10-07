package com.credibanco.assessment.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.credibanco.assessment.library.model1.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {
	
	List<Libro> findByTitulo(String titulo);

}

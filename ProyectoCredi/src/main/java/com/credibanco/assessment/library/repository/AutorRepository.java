package com.credibanco.assessment.library.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credibanco.assessment.library.model1.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {
	
	List<Autor> findByNombre(String nombre);

}

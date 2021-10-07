package com.credibanco.assessment.library.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.credibanco.assessment.library.model1.Editorial;

public interface EditorialRepository extends JpaRepository<Editorial, Long> {
	
	List<Editorial> findByNombre(String nombre);

}

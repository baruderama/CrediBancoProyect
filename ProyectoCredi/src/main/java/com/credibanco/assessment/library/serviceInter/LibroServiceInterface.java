package com.credibanco.assessment.library.serviceInter;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.credibanco.assessment.library.dto.LibroDto;

public interface LibroServiceInterface {
	public List<LibroDto> getAllLibro();
	public Optional<LibroDto> getLibroById(long id);
	public LibroDto createLibro( LibroDto libroDto);
	public ResponseEntity<HttpStatus> deleteLibro(long id);
	public ResponseEntity<HttpStatus> deleteAllLibro();

}

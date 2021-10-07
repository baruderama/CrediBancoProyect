package com.credibanco.assessment.library.serviceInter;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.credibanco.assessment.library.dto.AutorDto;

public interface AutorServiceInterface {
	
	public List<AutorDto> getAllAutores();
	public Optional<AutorDto> getAutorById(long id);
	public AutorDto createAutor(AutorDto autorDto);
	public ResponseEntity<HttpStatus> deleteAutor(long id);
	public ResponseEntity<HttpStatus> deleteAllAutor();
	

}

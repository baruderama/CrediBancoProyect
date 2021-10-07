package com.credibanco.assessment.library.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.credibanco.assessment.library.dto.AutorDto;
import com.credibanco.assessment.library.model1.Autor;
import com.credibanco.assessment.library.repository.AutorRepository;
import com.credibanco.assessment.library.serviceInter.AutorServiceInterface;

@Service
public class AutorService implements AutorServiceInterface {
	
	@Autowired
	private AutorRepository autorRepository;
	
	private AutorDto convertEntityToDto(Autor autor) {
		
		AutorDto autorDto= new AutorDto();
		autorDto.setId(autor.getId());
		autorDto.setNombre(autor.getNombre());
		autorDto.setCorreo(autor.getCorreo());
		autorDto.setCiudad(autor.getCiudad());
		autorDto.setFechaNacimiento(autor.getFechaNacimiento());
		autorDto.setLibrosAutor(autor.getLibrosAutor());
		
		return autorDto;
		
	}
	
private Autor convertDtoToEntity(AutorDto autorDto) {
		
		Autor autor= new Autor();
		autor.setId(autorDto.getId());
		autor.setNombre(autorDto.getNombre());
		autor.setCorreo(autorDto.getCorreo());
		autor.setCiudad(autorDto.getCiudad());
		autor.setFechaNacimiento(autorDto.getFechaNacimiento());
		autor.setLibrosAutor(autorDto.getLibrosAutor());
		
		return autor;
		
	}
	
	
	public List<AutorDto> getAllAutores() {
		
			return autorRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());		
	}

	
	public Optional<AutorDto> getAutorById(long id) {
		
		Optional<Autor> auOptional= autorRepository.findById(id);
		Autor autor=auOptional.get();
		AutorDto auDto=convertEntityToDto(autor);
		Optional<AutorDto> auDtoOptional= Optional.of(auDto);
		
		return auDtoOptional;
	}

	
	public AutorDto createAutor(AutorDto autorDto) {
			
		Autor au=autorRepository
		.save(convertDtoToEntity(autorDto));
		
			
			return convertEntityToDto(au);
			
		
	}

	
	

	
	public ResponseEntity<HttpStatus> deleteAutor(long id) {
		try {
			autorRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	public ResponseEntity<HttpStatus> deleteAllAutor() {
		try {
			autorRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}

package com.credibanco.assessment.library.api.client;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.credibanco.assessment.library.dto.AutorDto;
import com.credibanco.assessment.library.service.impl.AutorService;



@CrossOrigin(origins = "http://localhost:8081")
@RestController
@Controller
@RequestMapping("/api")
public class AutorApi {

	@Autowired
	AutorService autorService;
	
	
	
	

	@GetMapping("/autor")
	public ResponseEntity<List<AutorDto>> getAllAutores() {
		try {
			List<AutorDto> autores = autorService.getAllAutores();
			
			
			if (autores.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(autores, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/autor/{id}")
	public ResponseEntity<AutorDto> getAutorById(@PathVariable("id") long id) {
		Optional<AutorDto> AutorData = autorService.getAutorById(id);

		if (AutorData.isPresent()) {
			return new ResponseEntity<>(AutorData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/autor")
	public ResponseEntity<AutorDto> createAutor(@RequestBody AutorDto autorDto) {
		try {
			AutorDto _autor = autorService
					.createAutor(autorDto);
			return new ResponseEntity<>(_autor, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/autor/{id}")
	public ResponseEntity<AutorDto> updateAutor(@PathVariable("id") long id, @RequestBody AutorDto autor) {
		Optional<AutorDto> tutorialData = autorService.getAutorById(id);

		if (tutorialData.isPresent()) {
			AutorDto _autor = tutorialData.get();
			_autor.setNombre(autor.getNombre());
			_autor.setCiudad(autor.getCiudad());
			_autor.setCorreo(autor.getCorreo());
			_autor.setFechaNacimiento(autor.getFechaNacimiento());
			return new ResponseEntity<>(autorService.createAutor(_autor), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/autor/{id}")
	public ResponseEntity<HttpStatus> deleteAutor(@PathVariable("id") long id) {
		try {
			autorService.deleteAutor(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/autor")
	public ResponseEntity<HttpStatus> deleteAllAutor() {
		try {
			autorService.deleteAllAutor();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

//	@GetMapping("/tutorials/published")
//	public ResponseEntity<List<Tutorial>> findByPublished() {
//		try {
//			List<Tutorial> tutorials = tutorialRepository.findByPublished(true);
//
//			if (tutorials.isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			}
//			return new ResponseEntity<>(tutorials, HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}

}

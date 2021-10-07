package com.credibanco.assessment.library.controller1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.credibanco.assessment.library.DTOs.AutorDTO;
import com.credibanco.assessment.library.model1.Autor;



@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class AutorController {

	@Autowired
	AutorDTO autorRepository;

	@GetMapping("/autor")
	public ResponseEntity<List<Autor>> getAllTutorials( ) {
		try {
			List<Autor> autores = autorRepository.findAll();


			if (autores.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(autores, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/autor/{id}")
	public ResponseEntity<Autor> getAutorById(@PathVariable("id") long id) {
		Optional<Autor> AutorData = autorRepository.findById(id);

		if (AutorData.isPresent()) {
			return new ResponseEntity<>(AutorData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/autor")
	public ResponseEntity<Autor> createAutor(@RequestBody Autor autor) {
		try {
			Autor _autor = autorRepository
					.save(autor);
			return new ResponseEntity<>(_autor, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/autor/{id}")
	public ResponseEntity<Autor> updateAutor(@PathVariable("id") long id, @RequestBody Autor autor) {
		Optional<Autor> tutorialData = autorRepository.findById(id);

		if (tutorialData.isPresent()) {
			Autor _autor = tutorialData.get();
			_autor.setNombre(autor.getNombre());
			_autor.setCiudad(autor.getCiudad());
			_autor.setCorreo(autor.getCorreo());
			_autor.setFechaNacimiento(autor.getFechaNacimiento());
			return new ResponseEntity<>(autorRepository.save(_autor), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/autor/{id}")
	public ResponseEntity<HttpStatus> deleteAutor(@PathVariable("id") long id) {
		try {
			autorRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/tutorials")
	public ResponseEntity<HttpStatus> deleteAllAutor() {
		try {
			autorRepository.deleteAll();
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

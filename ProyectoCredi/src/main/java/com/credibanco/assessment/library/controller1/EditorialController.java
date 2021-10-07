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

import com.credibanco.assessment.library.DTOs.EditorialDTO;
import com.credibanco.assessment.library.model1.Editorial;



@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class EditorialController {

	@Autowired
	EditorialDTO editorialRepository;

	@GetMapping("/editorial")
	public ResponseEntity<List<Editorial>> getAllTutorials(@RequestParam(required = false) String nombre) {
		try {
			List<Editorial> editoriales = new ArrayList<Editorial>();

			if (nombre == null)
				editorialRepository.findAll().forEach(editoriales::add);
			else
				editorialRepository.findByNombre(nombre).forEach(editoriales::add);

			if (editoriales.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(editoriales, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/editorial/{id}")
	public ResponseEntity<Editorial> getAutorById(@PathVariable("id") long id) {
		Optional<Editorial> editorialData = editorialRepository.findById(id);

		if (editorialData.isPresent()) {
			return new ResponseEntity<>(editorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/editorial")
	public ResponseEntity<Editorial> createAutor(@RequestBody Editorial editorial) {
		try {
			Editorial _editorial = editorialRepository
					.save(editorial);
			return new ResponseEntity<>(_editorial, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/editorial/{id}")
	public ResponseEntity<Editorial> updateAutor(@PathVariable("id") long id, @RequestBody Editorial editorial) {
		Optional<Editorial> editorialData = editorialRepository.findById(id);

		if (editorialData.isPresent()) {
			Editorial _editorial = editorialData.get();
			_editorial.setNombre(editorial.getNombre());
			_editorial.setDireccion(editorial.getDireccion());
			_editorial.setTelefono(editorial.getTelefono());
			_editorial.setCorreo(editorial.getCorreo());
			_editorial.setMaxLibros(editorial.getMaxLibros());
			return new ResponseEntity<>(editorialRepository.save(_editorial), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/editorial/{id}")
	public ResponseEntity<HttpStatus> deleteAutor(@PathVariable("id") long id) {
		try {
			editorialRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/editoriales")
	public ResponseEntity<HttpStatus> deleteAllAutor() {
		try {
			editorialRepository.deleteAll();
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


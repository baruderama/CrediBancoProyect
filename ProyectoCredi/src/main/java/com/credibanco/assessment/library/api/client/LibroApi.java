package com.credibanco.assessment.library.api.client;



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

import com.credibanco.assessment.library.model1.Libro;
import com.credibanco.assessment.library.repository.LibroRepository;



@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class LibroApi {
	
	
	

	@Autowired
	LibroRepository libroRepository;

	@GetMapping("/libro")
	public ResponseEntity<List<Libro>> getAllLibro(@RequestParam(required = false) String titulo) {
		try {
			List<Libro> libros = new ArrayList<Libro>();

			if (titulo == null)
				libroRepository.findAll().forEach(libros::add);
			else
				libroRepository.findByTitulo(titulo).forEach(libros::add);

			if (libros.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(libros, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/libro/{id}")
	public ResponseEntity<Libro> getLibroById(@PathVariable("id") long id) {
		Optional<Libro> libroData = libroRepository.findById(id);

		if (libroData.isPresent()) {
			return new ResponseEntity<>(libroData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/libro")
	public ResponseEntity<Libro> createLibro(@RequestBody Libro libro) {
		try {
			Libro _libro = libroRepository
					.save(libro);
			return new ResponseEntity<>(_libro, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/libro/{id}")
	public ResponseEntity<Libro> updateLibro(@PathVariable("id") long id, @RequestBody Libro libro) {
		Optional<Libro> libroData = libroRepository.findById(id);

		if (libroData.isPresent()) {
			Libro _libro = libroData.get();
			_libro.setTitulo(libro.getTitulo());
			_libro.setAnio(libro.getAnio());
			_libro.setGenero(libro.getGenero());
			_libro.setNoPaginas(libro.getNoPaginas());
			_libro.setAutores(libro.getAutores());
			_libro.setEditoriales(libro.getEditoriales());
			return new ResponseEntity<>(libroRepository.save(_libro), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/libro/{id}")
	public ResponseEntity<HttpStatus> deleteLibro(@PathVariable("id") long id) {
		try {
			libroRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/libros")
	public ResponseEntity<HttpStatus> deleteAllLibro() {
		try {
			libroRepository.deleteAll();
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


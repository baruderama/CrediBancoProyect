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

import com.credibanco.assessment.library.dto.EditorialDto;
import com.credibanco.assessment.library.dto.LibroDto;
import com.credibanco.assessment.library.model1.Libro;
import com.credibanco.assessment.library.repository.LibroRepository;
import com.credibanco.assessment.library.service.impl.EditorialService;
import com.credibanco.assessment.library.service.impl.LibroService;



@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class LibroApi {
	
	
	

	@Autowired
	LibroService libroServicio;

	@GetMapping("/libro")
	public ResponseEntity<List<LibroDto>> getAllLibro() {
		try {
			
			List<LibroDto> libros = libroServicio.getAllLibro();
			

			if (libros.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(libros, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/libro/{id}")
	public ResponseEntity<LibroDto> getLibroById(@PathVariable("id") long id) {
		Optional<LibroDto> libroData = libroServicio.getLibroById(id);

		if (libroData.isPresent()) {
			return new ResponseEntity<>(libroData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/libro/{titulo}")
	public ResponseEntity<LibroDto> getLibroById(@PathVariable("titulo") String titulo) {
		Optional<LibroDto> libroData = libroServicio.getLibroByTitulo(titulo);

		if (libroData.isPresent()) {
			return new ResponseEntity<>(libroData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/libro")
	public ResponseEntity<LibroDto> createLibro(@RequestBody LibroDto libroDto) {
		try {
			LibroDto _libro = libroServicio
					.createLibro(libroDto);
			return new ResponseEntity<>(_libro, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/libro/{id}")
	public ResponseEntity<LibroDto> updateLibro(@PathVariable("id") long id, @RequestBody LibroDto libroDto) {
		Optional<LibroDto> libroData = libroServicio.getLibroById(id);

		if (libroData.isPresent()) {
			LibroDto _libro = libroData.get();
			_libro.setTitulo(libroDto.getTitulo());
			_libro.setAnio(libroDto.getAnio());
			_libro.setGenero(libroDto.getGenero());
			_libro.setNoPaginas(libroDto.getNoPaginas());
			_libro.setAutores(libroDto.getAutores());
			_libro.setEditoriales(libroDto.getEditoriales());
			return new ResponseEntity<>(libroServicio.createLibro(_libro), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/libro/{id}")
	public ResponseEntity<HttpStatus> deleteLibro(@PathVariable("id") long id) {
		try {
			libroServicio.deleteLibro(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/libros")
	public ResponseEntity<HttpStatus> deleteAllLibro() {
		try {
			libroServicio.deleteAllLibro();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}



}


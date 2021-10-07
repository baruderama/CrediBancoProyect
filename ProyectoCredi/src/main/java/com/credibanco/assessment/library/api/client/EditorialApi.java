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

import com.credibanco.assessment.library.dto.AutorDto;
import com.credibanco.assessment.library.dto.EditorialDto;
import com.credibanco.assessment.library.dto.LibroDto;
import com.credibanco.assessment.library.model1.Editorial;
import com.credibanco.assessment.library.repository.EditorialRepository;
import com.credibanco.assessment.library.service.impl.EditorialService;



@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class EditorialApi {

	@Autowired
	EditorialService editorialServicio;

	@GetMapping("/editorial")
	public ResponseEntity<List<EditorialDto>> getAllEditoriales() {
		try {
			List<EditorialDto> editoriales = editorialServicio.getAllEditoriales();

			if (editoriales.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(editoriales, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/editorial/{id}")
	public ResponseEntity<EditorialDto> getEditorialById(@PathVariable("id") long id) {
		Optional<EditorialDto> editorialData = editorialServicio.getEditorialById(id);

		if (editorialData.isPresent()) {
			return new ResponseEntity<>(editorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/editorial/{nombre}")
	public ResponseEntity<EditorialDto> getEditorialById(@PathVariable("nombre") String nombre) {
		Optional<EditorialDto> editorialData = editorialServicio.geEditorialByNombre(nombre);

		if (editorialData.isPresent()) {
			return new ResponseEntity<>(editorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/editorial")
	public ResponseEntity<EditorialDto> createEditorial(@RequestBody EditorialDto editorialDto) {
		try {
			EditorialDto _editorial = editorialServicio
					.createEditorial(editorialDto);
			return new ResponseEntity<>(_editorial, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/editorial/{id}")
	public ResponseEntity<EditorialDto> updateEditorial(@PathVariable("id") long id, @RequestBody EditorialDto editorial) {
		Optional<EditorialDto> editorialData = editorialServicio.getEditorialById(id);

		if (editorialData.isPresent()) {
			EditorialDto _editorial = editorialData.get();
			_editorial.setNombre(editorial.getNombre());
			_editorial.setDireccion(editorial.getDireccion());
			_editorial.setTelefono(editorial.getTelefono());
			_editorial.setCorreo(editorial.getCorreo());
			_editorial.setMaxLibros(editorial.getMaxLibros());
			return new ResponseEntity<>(editorialServicio.createEditorial(_editorial), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/editorial/{id}")
	public ResponseEntity<HttpStatus> deleteEditorial(@PathVariable("id") long id) {
		try {
			editorialServicio.deleteEditorial(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/editoriales")
	public ResponseEntity<HttpStatus> deleteAllEditorial() {
		try {
			editorialServicio.deleteAllEditorial();
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


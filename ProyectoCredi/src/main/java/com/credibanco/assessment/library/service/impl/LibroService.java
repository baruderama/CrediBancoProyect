package com.credibanco.assessment.library.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.credibanco.assessment.library.dto.AutorDto;
import com.credibanco.assessment.library.dto.EditorialDto;
import com.credibanco.assessment.library.dto.LibroDto;
import com.credibanco.assessment.library.model1.Autor;
import com.credibanco.assessment.library.model1.Editorial;
import com.credibanco.assessment.library.model1.Libro;
import com.credibanco.assessment.library.repository.LibroRepository;
import com.credibanco.assessment.library.serviceInter.LibroServiceInterface;

@Service
public class LibroService implements LibroServiceInterface {
	
	@Autowired
	LibroRepository libroRepository;
	
private LibroDto convertEntityToDto(Libro libro) {
		
		LibroDto libroDto= new LibroDto();
		libroDto.setId(libro.getId());
		libroDto.setTitulo(libro.getTitulo());
		libroDto.setGenero(libro.getGenero());
		libroDto.setAnio(libro.getAnio());
		libroDto.setNoPaginas(libro.getNoPaginas());
		libroDto.setAutores(libro.getAutores());
		libroDto.setEditoriales(libro.getEditoriales());
		
		return libroDto;
		
	}

private Libro convertDtoToEntity(LibroDto libroDto) {
	
	Libro libro= new Libro();
	libro.setId(libroDto.getId());
	libro.setTitulo(libroDto.getTitulo());
	libro.setGenero(libroDto.getGenero());
	libro.setAnio(libroDto.getAnio());
	libro.setNoPaginas(libroDto.getNoPaginas());
	libro.setAutores(libroDto.getAutores());
	libro.setEditoriales(libroDto.getEditoriales());
	
	return libro;
	
}

	
	public List<LibroDto> getAllLibro() {
		return libroRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
	}

	
	public Optional<LibroDto> getLibroById(long id) {
		Optional<Libro> libroOptional= libroRepository.findById(id);
		Libro libro=libroOptional.get();
		LibroDto libDto=convertEntityToDto(libro);
		Optional<LibroDto> libDtoOptional= Optional.of(libDto);
		
		return libDtoOptional;
	}
	
	public Optional<LibroDto> getLibroByTitulo(String titulo) {
		Optional<List<Libro>> libroOptional= Optional.of(libroRepository.findByTitulo(titulo));
		Libro libro=(Libro) libroOptional.get();
		LibroDto libDto=convertEntityToDto(libro);
		Optional<LibroDto> libDtoOptional= Optional.of(libDto);
		
		return libDtoOptional;
	}

	
	public LibroDto createLibro( LibroDto libroDto) {
		Libro lib=libroRepository
				.save(convertDtoToEntity(libroDto));
				
					
					return convertEntityToDto(lib);
	}

	

	
	public ResponseEntity<HttpStatus> deleteLibro(long id) {
		try {
			libroRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
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

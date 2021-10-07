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
import com.credibanco.assessment.library.repository.EditorialRepository;
import com.credibanco.assessment.library.serviceInter.EditorialServiceInterface;

@Service
public class EditorialService implements EditorialServiceInterface {
	
	@Autowired
	EditorialRepository editorialRepository;
	
private EditorialDto convertEntityToDto(Editorial editorial) {
		
		EditorialDto editorialDto= new EditorialDto();
		editorialDto.setId(editorial.getId());
		editorialDto.setNombre(editorial.getNombre());
		editorialDto.setCorreo(editorial.getCorreo());
		editorialDto.setDireccion(editorial.getDireccion());
		editorialDto.setTelefono(editorial.getTelefono());
		editorialDto.setMaxLibros(editorial.getMaxLibros());
		editorialDto.setLibrosEditorial(editorial.getLibrosEditorial());
		
		return editorialDto;
		
	}

private Editorial convertDtoToEntity(EditorialDto editorialDto) {
	
	Editorial editorial= new Editorial();
	editorial.setId(editorialDto.getId());
	editorial.setNombre(editorialDto.getNombre());
	editorial.setCorreo(editorialDto.getCorreo());
	editorial.setDireccion(editorialDto.getDireccion());
	editorial.setTelefono(editorialDto.getTelefono());
	editorial.setMaxLibros(editorialDto.getMaxLibros());
	editorial.setLibrosEditorial(editorialDto.getLibrosEditorial());
	
	return editorial;
	
}

	
	public List<EditorialDto> getAllEditoriales( ) {
		return editorialRepository.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
			
				

			
	}

	
	public Optional<EditorialDto> getEditorialById( long id) {
		Optional<Editorial> ediOptional= editorialRepository.findById(id);
		Editorial editorial=ediOptional.get();
		EditorialDto ediDto=convertEntityToDto(editorial);
		Optional<EditorialDto> ediDtoOptional= Optional.of(ediDto);
		
		return ediDtoOptional;
	}
	
	public Optional<EditorialDto> geEditorialByNombre(String nombre) {
		Optional<List<Editorial>> editorialOptional= Optional.of(editorialRepository.findByNombre(nombre));
		Editorial editorial=(Editorial) editorialOptional.get();
		EditorialDto ediDto=convertEntityToDto(editorial);
		Optional<EditorialDto> ediDtoOptional= Optional.of(ediDto);
		
		return ediDtoOptional;
	}

	
	public EditorialDto createEditorial(EditorialDto editorialDto) {
		Editorial edi=editorialRepository
				.save(convertDtoToEntity(editorialDto));
				
					
					return convertEntityToDto(edi);
	}

	
	

	
	public ResponseEntity<HttpStatus> deleteEditorial( long id) {
		try {
			
			editorialRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	public ResponseEntity<HttpStatus> deleteAllEditorial() {
		try {
			editorialRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}



}

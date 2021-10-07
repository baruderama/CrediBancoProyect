package com.credibanco.assessment.library.serviceInter;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.credibanco.assessment.library.dto.EditorialDto;

public interface EditorialServiceInterface {
	public List<EditorialDto> getAllEditoriales();
	public Optional<EditorialDto> getEditorialById( long id);
	public EditorialDto createEditorial(EditorialDto editorialDto);
	public ResponseEntity<HttpStatus> deleteEditorial( long id);
	public ResponseEntity<HttpStatus> deleteAllEditorial();
	

}

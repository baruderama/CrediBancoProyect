package com.credibanco.assessment.library.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.credibanco.assessment.library.api.client.AutorApi;
import com.credibanco.assessment.library.api.client.EditorialApi;
import com.credibanco.assessment.library.dto.AutorDto;
import com.credibanco.assessment.library.dto.EditorialDto;
import com.credibanco.assessment.library.model.front.AutorFront;
import com.credibanco.assessment.library.model1.Libro;

@Controller
public class EditorialController1 {
	
	
	@Autowired
	EditorialApi editorialController;
	
	
	
	@GetMapping("/editoriales")
	public String getEditoriales(Model model) {
		
		ResponseEntity<List<EditorialDto>> list=editorialController.getAllEditoriales();
		
		
		List<EditorialDto> editoriales =new ArrayList<>();
		editoriales=list.getBody();
		if(editoriales== null)
			editoriales=new ArrayList<>();
		//List<AutorFront> autorFront = new ArrayList<>();
		
		
//		for(AutorDto au : editoriales) {
//			AutorFront auF= new AutorFront();
//			auF.setNombre(au.getNombre());
//			auF.setCiudad(au.getCiudad());
//			auF.setCorreo(au.getCorreo());
//			auF.setFechaNacimiento(au.getFechaNacimiento());
//			auF.setId(au.getId());
//			
//			for(Libro l : au.getLibrosAutor()) {
//				String titulo=l.getTitulo();
//				auF.getLibrosAutor().add(titulo);
//				
//			}
//			autorFront.add(auF);
//		}
		
		
		
		
		
		
		
		model.addAttribute("editoriales",editoriales);
		
		
		//AutorController au=new AutorController();
		return "editoriales";
	}
	
	@GetMapping("/editoriales/findById")
	public Optional<EditorialDto> getEditorialById( long id) {
		return Optional.of( editorialController.getEditorialById(id).getBody());
		

		
	}
	
	@PostMapping("/editoriales")
	public String createEditorial(EditorialDto editorialDto) {
		editorialController.createEditorial(editorialDto);
		return "redirect:/editoriales";
		
	}
	
	@RequestMapping(value ="/editoriales/update",method= {RequestMethod.PUT,RequestMethod.GET})
	public String updateEditorial(EditorialDto editorial) {
		editorialController.updateEditorial(editorial.getId(),editorial);
		
		return "redirect:/editoriales";
		
	}
	
	@RequestMapping(value ="/editoriales/delete",method= {RequestMethod.DELETE,RequestMethod.GET})
	public String deleteEditorial(long  id) {
		editorialController.deleteEditorial(id);
		
		return "redirect:/editoriales";
		
	}

}

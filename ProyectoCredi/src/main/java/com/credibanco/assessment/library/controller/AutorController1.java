package com.credibanco.assessment.library.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credibanco.assessment.library.api.client.AutorApi;
import com.credibanco.assessment.library.dto.AutorDto;
import com.credibanco.assessment.library.dto.LibroDto;
import com.credibanco.assessment.library.model.front.AutorFront;
import com.credibanco.assessment.library.model1.Libro;
import com.credibanco.assessment.library.service.impl.AutorService;

@Controller
public class AutorController1 {
	
	
	
	@Autowired
	AutorApi autorController;
	
	@GetMapping("/autores")
	public String getAutores(Model model) {
		
		ResponseEntity<List<AutorDto>> list=autorController.getAllAutores();
		
		
		List<AutorDto> autores =list.getBody();
		List<AutorFront> autorFront = new ArrayList<>();
		
		
		for(AutorDto au : autores) {
			AutorFront auF= new AutorFront();
			auF.setNombre(au.getNombre());
			auF.setCiudad(au.getCiudad());
			auF.setCorreo(au.getCorreo());
			auF.setFechaNacimiento(au.getFechaNacimiento());
			auF.setId(au.getId());
			
			for(Libro l : au.getLibrosAutor()) {
				String titulo=l.getTitulo();
				auF.getLibrosAutor().add(titulo);
				
			}
			autorFront.add(auF);
		}
		
		
		
		
		
		
		
		model.addAttribute("autores",autorFront);
		
		
		//AutorController au=new AutorController();
		return "autores";
	}

}

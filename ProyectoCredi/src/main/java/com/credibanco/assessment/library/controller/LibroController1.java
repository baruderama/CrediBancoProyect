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
import com.credibanco.assessment.library.api.client.LibroApi;
import com.credibanco.assessment.library.dto.EditorialDto;
import com.credibanco.assessment.library.dto.LibroDto;
import com.credibanco.assessment.library.model.front.AutorFront;
import com.credibanco.assessment.library.model.front.LibroFront;
import com.credibanco.assessment.library.model1.Autor;
import com.credibanco.assessment.library.model1.Editorial;

@Controller
public class LibroController1 {
	
	@Autowired
	LibroApi libroController;
	
	@Autowired
	EditorialApi editorialController;
	
	@Autowired
	AutorApi autorController;
	
	@GetMapping("/libros")
	public String getLibros(Model model) {
		
ResponseEntity<List<LibroDto>> list=libroController.getAllLibro();
		
		
		List<LibroDto> libros =new ArrayList<>();
		libros=list.getBody();
		if(libros== null)
			libros=new ArrayList<>();
		
		List<LibroFront> autorFront = new ArrayList<>();
		
		
		for(LibroDto au : libros) {
			LibroFront auF= new LibroFront();
			auF.setTitulo(au.getTitulo());
			auF.setAnio(au.getAnio());
			auF.setGenero(au.getGenero());
			auF.setNoPaginas(au.getNoPaginas());
			auF.setId(au.getId());
			
			for(Editorial e : au.getEditoriales()) {
				String nombre=e.getNombre();
				auF.getEditoriales().add(nombre);
				
			}
			
			for(Autor a : au.getAutores()) {
				String nombre=a.getNombre();
				auF.getAutores().add(nombre);
				
			}
			autorFront.add(auF);
		}
		
		model.addAttribute("libros",autorFront);
		
		return "libros";
	}
	
	
	@GetMapping("/libros/findById")
	public Optional<LibroDto> getLibroById( long id) {
		return Optional.of( libroController.getLibroById(id).getBody());
		

		
	}
	
	@PostMapping("/libros")
	public String createLibro(LibroFront libroFront) {
		
		ResponseEntity<EditorialDto> edi=editorialController.getEditorialById(libroFront.getEditoriales().get(0));
		autorController.getAutorByNombre(libroFront.getAutores().get(0));
		
		LibroDto libroDto= new LibroDto();
		libroDto.setTitulo(libroFront.getTitulo());
		libroDto.setAnio(libroFront.getAnio());
		libroDto.setGenero(libroFront.getGenero());
		libroDto.setNoPaginas(libroFront.getNoPaginas());
		//libroDto.getAutores().add((Autor)edi.getBody());
		//libroDto.getEditoriales().add(edi.getBody());
		
		
		libroController.createLibro(libroDto);
		//AutorDto autor
		
		
		
		return "redirect:/libros";
		
	}
	
	@RequestMapping(value ="/libros/update",method= {RequestMethod.PUT,RequestMethod.GET})
	public String updateLibro(LibroDto libro) {
		libroController.updateLibro(libro.getId(),libro);
		
		return "redirect:/libros";
		
	}
	
	@RequestMapping(value ="/libros/delete",method= {RequestMethod.DELETE,RequestMethod.GET})
	public String deleteLibro(long  id) {
		libroController.deleteLibro(id);
		
		return "redirect:/libros";
		
	}

}

package com.credibanco.assessment.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibroController1 {
	
	@GetMapping("/biblioteca")
	public String getLibros() {
		return "biblioteca";
	}

}

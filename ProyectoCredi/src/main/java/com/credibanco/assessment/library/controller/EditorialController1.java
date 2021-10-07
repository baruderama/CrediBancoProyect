package com.credibanco.assessment.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.credibanco.assessment.library.api.client.AutorApi;
import com.credibanco.assessment.library.api.client.EditorialApi;

@Controller
public class EditorialController1 {
	
	
	@Autowired
	EditorialApi editorialController;
	
	@GetMapping("/editoriales")
	public String getEditoriales() {
		return "editoriales";
	}

}

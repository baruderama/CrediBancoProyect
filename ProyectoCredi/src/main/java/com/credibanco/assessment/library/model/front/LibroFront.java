package com.credibanco.assessment.library.model.front;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.credibanco.assessment.library.model1.Autor;
import com.credibanco.assessment.library.model1.Editorial;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LibroFront {
	
	private long id;
	private String titulo;	
	private int anio;
	private String genero;
	private String noPaginas;
	private List<String> editoriales = new ArrayList<>();
	private List<String> autores = new ArrayList<>();

}

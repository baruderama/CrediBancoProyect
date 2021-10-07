package com.credibanco.assessment.library.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import com.credibanco.assessment.library.model1.Autor;
import com.credibanco.assessment.library.model1.Editorial;
import com.credibanco.assessment.library.model1.Libro;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LibroDto {
	
private long id;
private String titulo;	
private int anio;
private String genero;
private String noPaginas;
private Set<Editorial> editoriales = new HashSet<>();
private Set<Autor> autores = new HashSet<>();
	
    

}

package com.credibanco.assessment.library.dto;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.credibanco.assessment.library.model1.Libro;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutorDto {
	
private long id;
private String nombre;
private Date fechaNacimiento;
private String ciudad;
private String correo;
private Set<Libro> librosAutor = new HashSet<>();
	
	
	


}

package com.credibanco.assessment.library.model.front;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.credibanco.assessment.library.model1.Libro;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutorFront {
	
	private long id;
	private String nombre;
	private Date fechaNacimiento;
	private String ciudad;
	private String correo;
	private List<String> librosAutor = new ArrayList<>();

}

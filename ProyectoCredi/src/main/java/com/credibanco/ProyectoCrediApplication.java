package com.credibanco;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.credibanco.assessment.library.model1.Autor;
import com.credibanco.assessment.library.model1.Editorial;
import com.credibanco.assessment.library.model1.Libro;
import com.credibanco.assessment.library.repository.AutorRepository;
import com.credibanco.assessment.library.repository.EditorialRepository;
import com.credibanco.assessment.library.repository.LibroRepository;

@SpringBootApplication
@EnableJpaAuditing
public class ProyectoCrediApplication implements CommandLineRunner {
	
	@Autowired
	private AutorRepository autorDto;
	
	@Autowired
	private EditorialRepository editorialDto;
	
	@Autowired
	private LibroRepository libroDto;
	
	

	public static void main(String[] args) {
		
		SpringApplication.run(ProyectoCrediApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		autorDto.deleteAllInBatch();
		editorialDto.deleteAllInBatch();
		libroDto.deleteAllInBatch();
		
		
		
		
		Editorial edi= new Editorial();
		edi.setNombre("sebs");
		edi.setDireccion("calle63");
		edi.setTelefono("1234");
		edi.setCorreo("corr@adfasd.com");
		edi.setMaxLibros(100);
		
		
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		
		Autor au= new Autor();
		au.setNombre("juan");
		au.setFechaNacimiento(date);
		au.setCiudad("bogota");
		au.setCorreo("ci@qsd.com");
		
		Libro libro=new Libro();
		
		libro.getEditoriales().add(edi);
		libro.getAutores().add(au);
		libro.setTitulo("primero");
		libro.setAnio(2020);
		libro.setGenero("terror");
		libro.setNoPaginas("100");
		
		au.getLibrosAutor().add(libro);
		edi.getLibrosEditorial().add(libro);
		
		
		libroDto.save(libro);
		
		
		
	}

}

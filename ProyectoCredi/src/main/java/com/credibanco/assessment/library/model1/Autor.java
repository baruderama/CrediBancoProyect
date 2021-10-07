package com.credibanco.assessment.library.model1;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="autor")
public class Autor {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	@Column(name="nombre",nullable= false, unique=true)
	private String nombre;
	
	@Column(name="fechanacimiento")
	private Date fechaNacimiento;
	
	@Column(name="ciudad")
	private String ciudad;
	
	@Column(name="correo")
	private String correo;
	
	
//	@ManyToMany()
//	@JoinTable(name = "autor_libro")
//	private Set<Libro> librosAutor = new HashSet<Libro>();
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(name = "autor_libro", 
            joinColumns = { @JoinColumn(name = "autor_id") },
            inverseJoinColumns = { @JoinColumn(name = "libro_id") })
	private Set<Libro> librosAutor = new HashSet<>();
	
	
	
	
	
	
	
	
	

}

package com.credibanco.assessment.library.model1;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

@Table(name= "libro")
public class Libro {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	@Column(name="titulo",nullable= false,unique=true)
	private String titulo;
	
	@Column(name="anio",nullable= false)
	private int anio;
	
	@Column(name="genero",nullable= false)
	private String genero;
	
	@Column(name="nopaginas",nullable= false)
	private String noPaginas;
	
//	@JsonIgnore
//	@ManyToMany( mappedBy = "librosEditorial")
//	private Set<Editorial> editorial = new HashSet<Editorial>();
	
	//@JsonBackReference
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            },
            mappedBy = "librosEditorial")
    private Set<Editorial> editoriales = new HashSet<>();
	
//	@JsonIgnore
//	@ManyToMany( mappedBy = "librosAutor")
//	private Set<Autor> autores = new HashSet<Autor>();
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            },
            mappedBy = "librosAutor")
    private Set<Autor> autores = new HashSet<>();
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

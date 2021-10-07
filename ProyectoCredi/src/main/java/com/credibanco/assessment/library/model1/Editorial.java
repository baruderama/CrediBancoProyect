package com.credibanco.assessment.library.model1;



import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

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

@Table(name="editorial")

public class Editorial {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id;
	
	@Column(name="nombre", nullable= false,unique=true)
	private String nombre;
	
	@Column(name="direccion",nullable= false)
	private String direccion;
	
	@Column(name="telefono",nullable= false)
	private String telefono;
	
	@Column(name="correo")
	private String correo;
	
	@Column(name="maxlibros",nullable= false)
	private long maxLibros;
	
//	@ManyToMany()
//	@JoinTable(name = "editorial_libro")
//	private Set<Libro> librosEditorial = new HashSet<Libro>();
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(name = "editorial_libro",
            joinColumns = { @JoinColumn(name = "editorial_id") },
            inverseJoinColumns = { @JoinColumn(name = "libro_id") })
	private Set<Libro> librosEditorial = new HashSet<>();
	

	
	
	
	
	

}

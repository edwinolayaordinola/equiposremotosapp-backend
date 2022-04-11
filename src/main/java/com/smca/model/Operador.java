package com.smca.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Operador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nombre",nullable = true,length=150)
	private String nombre;
	
	@Column(name="apellido",nullable = true,length=150)
	private String apellido;
	
	@Column(name="dni",nullable = true,length=8)
	private String dni;
	
	@Column(name="telefono",nullable = true,length=9)
	private String telefono;
	
	@Column(name="correo",nullable = true,length=150)
	private String correo;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="idestacion",nullable=false,foreignKey = @ForeignKey(name="FK_estacion_operador"))
	private Estacion estacion;

	public Operador() {
		super();
	}

	public Operador(String nombre, String apellido, String dni, String telefono, String correo, Estacion estacion) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.telefono = telefono;
		this.correo = correo;
		this.estacion = estacion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Estacion getEstacion() {
		return estacion;
	}

	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}
}

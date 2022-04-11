package com.smca.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="provincia")
public class Provincia {
	
	@Id
	@Column(name="id", length=4)
	private String id;
	
	@Column(name="nombre",nullable=false,length=80)
	private String nombre;
	
	//@JsonIgnore
	@ManyToOne
	@JoinColumn	(name="iddepartamento",nullable=false,foreignKey=@ForeignKey(name="FK_departamento_provincia"))
	private Departamento departamento;
	
	@JsonIgnore
	@OneToMany(mappedBy="provincia", cascade = {CascadeType.ALL}, orphanRemoval = true) /*cascade cuando yo inserto en provincia tambien tengo  que insertar en distrito*/
	private List<Distrito> distritos;
	

	public Provincia(String id, String nombre, Departamento departamento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.departamento = departamento;
	}

	public Provincia() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public List<Distrito> getDistritos() {
		return distritos;
	}

	public void setDistritos(List<Distrito> distritos) {
		this.distritos = distritos;
	}	

}

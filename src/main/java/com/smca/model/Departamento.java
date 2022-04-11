package com.smca.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "departamento")
public class Departamento {

	@Id
	@Column(name = "id", length = 2)
	private String id;

	@Column(name = "nombre", length = 80)
	private String nombre;
	
	@JsonIgnore
	@OneToMany(mappedBy = "departamento", cascade = {CascadeType.ALL }, orphanRemoval = true) /*(OrphanRemoval = true) =>permite eliminar un elemento en particular , sino le indico me fuerza a eliminar todo o nada*/
	private List<Provincia> provincias;

	public Departamento() {
		super();
	}

	public Departamento(String id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
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

	public List<Provincia> getProvincias() {
		return provincias;
	}

	public void setProvincias(List<Provincia> provincias) {
		this.provincias = provincias;
	}

}

package com.smca.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="estacion")
public class Estacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nombre", nullable=false, length=50)
	private String nombre;
	
	@Column(name="did", nullable=false, length=8)
	private String did;
	
	@Column(name="coordx", nullable=false, length=50)
	private String coordx;
	
	@Column(name="coordy", nullable=false, length=50)
	private String coordy;	
	
	public Estacion() {
		super();
	}

	public Estacion(Integer id, String did,String nombre, String coordx, String coordy) {
		super();
		this.id = id;
		this.did = did;
		this.nombre = nombre;
		this.coordx = coordx;
		this.coordy = coordy;
	}	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getCoordx() {
		return coordx;
	}

	public void setCoordx(String coordx) {
		this.coordx = coordx;
	}

	public String getCoordy() {
		return coordy;
	}

	public void setCoordy(String coordy) {
		this.coordy = coordy;
	}

}

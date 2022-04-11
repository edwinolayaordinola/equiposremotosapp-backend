package com.smca.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="distrito")
public class Distrito {
	
	@Id
	@Column(name="id", length=6)
	private String id;
	
	@Column(name="id_geo",nullable=true,length=6)
	private String id_geo;
	
	@Column(name="nombre", nullable=false, length=80)
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name="idprovincia", nullable=false, foreignKey = @ForeignKey(name="FK_provincia_distrito"))
	private Provincia provincia;
	
	@Column(name="coordx", nullable=true,length=20)
	private String coordx;
	
	@Column(name="coordy", nullable=true,length=20)
	private String coordy;
	
	public Distrito() {
		super();
		// TODO Auto-generated constructor stub
	}	

	public Distrito(String id, String id_geo, String nombre, Provincia provincia, String coordx, String coordy) {
		super();
		this.id = id;
		this.id_geo = id_geo;
		this.nombre = nombre;
		this.provincia = provincia;
		this.coordx = coordx;
		this.coordy = coordy;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getId_geo() {
		return id_geo;
	}

	public void setId_geo(String id_geo) {
		this.id_geo = id_geo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
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

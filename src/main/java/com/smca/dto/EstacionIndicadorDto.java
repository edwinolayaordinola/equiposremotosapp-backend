package com.smca.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class EstacionIndicadorDto implements Serializable{
	
	private Integer id;
	private String did;
	private String nombre;
	private String coordx;
	private String coordy;
	private Float cloro;
	private Float ph;
	private Float temperatura;
	private LocalDateTime fecharegistro;
	/**
	 * 
	 */
	private static final long serialVersionUID = -2481812735132005374L;
	
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
	public Float getCloro() {
		return cloro;
	}
	public void setCloro(Float cloro) {
		this.cloro = cloro;
	}
	public Float getPh() {
		return ph;
	}
	public void setPh(Float ph) {
		this.ph = ph;
	}
	public Float getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(Float temperatura) {
		this.temperatura = temperatura;
	}
	public LocalDateTime getFecharegistro() {
		return fecharegistro;
	}
	public void setFecharegistro(LocalDateTime fecharegistro) {
		this.fecharegistro = fecharegistro;
	}
	
}
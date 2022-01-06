package com.smca.dto;

import java.io.Serializable;

public class EstacionDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9164073463072611402L;
	
	private String estacion;
	private Float ph;
	private Float cloro;
	private Float temperatura;	
	
	public EstacionDto(String estacion, Float ph, Float cloro, Float temperatura) {
		super();
		this.estacion = estacion;
		this.ph = ph;
		this.cloro = cloro;
		this.temperatura = temperatura;
	}
	public String getEstacion() {
		return estacion;
	}
	public void setEstacion(String estacion) {
		this.estacion = estacion;
	}
	public Float getPh() {
		return ph;
	}
	public void setPh(Float ph) {
		this.ph = ph;
	}
	public Float getCloro() {
		return cloro;
	}
	public void setCloro(Float cloro) {
		this.cloro = cloro;
	}
	public Float getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(Float temperatura) {
		this.temperatura = temperatura;
	}
}
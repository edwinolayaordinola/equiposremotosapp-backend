package com.smca.dto;

import java.time.LocalDateTime;

import com.smca.model.Estacion;

public class ReporteDto implements Comparable<ReporteDto>{
	
	public String nombre;
	public String fecharegistro;
	public Float cloro;
	public Float ph;
	public Float temperatura;
	
	public ReporteDto() {
		super();
	}
	
	public ReporteDto(String nombre, String fecharegistro, Float cloro, Float ph, Float temperatura) {
		super();
		this.nombre = nombre;
		this.fecharegistro = fecharegistro;
		this.cloro = cloro;
		this.ph = ph;
		this.temperatura = temperatura;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFecharegistro() {
		return fecharegistro;
	}

	public void setFecharegistro(String fecharegistro) {
		this.fecharegistro = fecharegistro;
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

	@Override
	public int compareTo(ReporteDto o) {
		// TODO Auto-generated method stub
		return this.getFecharegistro().compareTo(o.getFecharegistro());
	}
	
}

package com.smca.dto;

import java.time.LocalDateTime;

public class IndicadorCorrienteRecienteDto {
	
	Integer id;
	LocalDateTime fechaRegistro;	
	
	public IndicadorCorrienteRecienteDto(Integer id, LocalDateTime fechaRegistro) {
		super();
		this.id = id;
		this.fechaRegistro = fechaRegistro;
	}
	public IndicadorCorrienteRecienteDto() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
}

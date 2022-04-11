package com.smca.dto;

import java.time.LocalDateTime;

public class IndicadorRecienteDto {
	
	Integer idEstacion;
	LocalDateTime fechaRegistro;
	
	public IndicadorRecienteDto() {
		
	}
	
	public IndicadorRecienteDto(Integer idEstacion, LocalDateTime fechaRegistro) {
		super();
		this.idEstacion = idEstacion;
		this.fechaRegistro = fechaRegistro;
	}

	public Integer getIdEstacion() {
		return idEstacion;
	}

	public void setIdEstacion(Integer idEstacion) {
		this.idEstacion = idEstacion;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}	

}

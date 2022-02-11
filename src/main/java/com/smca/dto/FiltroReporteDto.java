package com.smca.dto;

import java.time.LocalDateTime;

public class FiltroReporteDto {
	
	private Integer idEstacion;
	private LocalDateTime fechaInicioConsulta;
	private LocalDateTime fechaFinConsulta;
	
	public FiltroReporteDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FiltroReporteDto(Integer idEstacion, LocalDateTime fechaInicioConsulta, LocalDateTime fechaFinConsulta) {
		super();
		this.idEstacion = idEstacion;
		this.fechaInicioConsulta = fechaInicioConsulta;
		this.fechaFinConsulta = fechaFinConsulta;
	}

	public Integer getIdEstacion() {
		return idEstacion;
	}

	public void setIdEstacion(Integer idEstacion) {
		this.idEstacion = idEstacion;
	}

	public LocalDateTime getFechaInicioConsulta() {
		return fechaInicioConsulta;
	}

	public void setFechaInicioConsulta(LocalDateTime fechaInicioConsulta) {
		this.fechaInicioConsulta = fechaInicioConsulta;
	}

	public LocalDateTime getFechaFinConsulta() {
		return fechaFinConsulta;
	}

	public void setFechaFinConsulta(LocalDateTime fechaFinConsulta) {
		this.fechaFinConsulta = fechaFinConsulta;
	}	
}

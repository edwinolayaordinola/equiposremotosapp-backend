package com.smca.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="indicadorcorriente")
public class IndicadorCorriente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="corrientedc",nullable=false)
	private Float corrientedc;
	
	@Column(name="corrienteac",nullable=false)
	private Float corrienteac;
	
	@Column(name="fecharegistro",nullable=false)
	private LocalDateTime fecharegistro;
	
	@ManyToOne
	@JoinColumn(name="idestacion")
	private Estacion estacion;
	
	public IndicadorCorriente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IndicadorCorriente(Float corrientedc, Float corrienteac, LocalDateTime fecharegistro, Estacion estacion) {
		super();
		this.corrientedc = corrientedc;
		this.corrienteac = corrienteac;
		this.fecharegistro = fecharegistro;
		this.estacion = estacion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getCorrientedc() {
		return corrientedc;
	}

	public void setCorrientedc(Float corrientedc) {
		this.corrientedc = corrientedc;
	}

	public Float getCorrienteac() {
		return corrienteac;
	}

	public void setCorrienteac(Float corrienteac) {
		this.corrienteac = corrienteac;
	}

	public LocalDateTime getFecharegistro() {
		return fecharegistro;
	}

	public void setFecharegistro(LocalDateTime fecharegistro) {
		this.fecharegistro = fecharegistro;
	}

	public Estacion getEstacion() {
		return estacion;
	}

	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}
}

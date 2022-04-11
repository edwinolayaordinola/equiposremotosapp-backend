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
@Table(name="indicador")
public class Indicador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="ph",nullable=false)
	private Float ph;
	
	@Column(name="cloro",nullable=false)
	private Float cloro;
	
	@Column(name="temperatura",nullable=false)
	private Float temperatura;
	
	@Column(name="caudal",nullable=true)
	private Float caudal;
	
	@Column(name="fecharegistro",nullable=false)
	private LocalDateTime fecharegistro;
	
	@ManyToOne
	@JoinColumn(name="idestacion")
	private Estacion estacion;

	public Indicador() {
		super();
	}

	public Indicador(Float ph, Float cloro, Float temperatura, Float caudal, LocalDateTime fecharegistro, Estacion estacion) {
		super();
		this.ph = ph;
		this.cloro = cloro;
		this.temperatura = temperatura;
		this.caudal = caudal;
		this.fecharegistro = fecharegistro;
		this.estacion = estacion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public LocalDateTime getFecharegistro() {
		return fecharegistro;
	}

	public Float getCaudal() {
		return caudal;
	}

	public void setCaudal(Float caudal) {
		this.caudal = caudal;
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

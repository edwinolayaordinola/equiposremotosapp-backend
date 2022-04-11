package com.smca.dto;

import java.util.List;

import com.smca.model.Estacion;
import com.smca.model.Operador;

public class EstacionOperadorDTO {
	
	private Estacion estacion;
	private List<Operador> operador;	
	
	public EstacionOperadorDTO() {
		super();
	}
	
	public EstacionOperadorDTO(Estacion estacion, List<Operador> operador) {
		super();
		this.estacion = estacion;
		this.operador = operador;
	}
	
	public Estacion getEstacion() {
		return estacion;
	}
	
	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}
	
	public List<Operador> getOperador() {
		return operador;
	}
	
	public void setOperador(List<Operador> operador) {
		this.operador = operador;
	}

}

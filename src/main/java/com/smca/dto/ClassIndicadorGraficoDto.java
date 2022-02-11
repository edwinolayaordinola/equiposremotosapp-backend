package com.smca.dto;

public class ClassIndicadorGraficoDto {
	
	private String estacion;
	private Float ph;
	private Float cloro;
	private Float temperatura;
	private String fecharegistro;
	
	public ClassIndicadorGraficoDto() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getFecharegistro() {
		return fecharegistro;
	}
	public void setFecharegistro(String fecharegistro) {
		this.fecharegistro = fecharegistro;
	}
	
	

}

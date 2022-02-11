package com.smca.dto;

public class EstacionDto{
	
	private Integer id;
	private String nombre;
	private String did;
	private String coordx;
	private String coordy;
	private String chip;
	
	public EstacionDto() {
		super();
	}

	public EstacionDto(Integer id, String nombre, String did, String coordx, String coordy, String chip) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.did = did;
		this.coordx = coordx;
		this.coordy = coordy;
		this.chip = chip;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
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

	public String getChip() {
		return chip;
	}
	public void setChip(String chip) {
		this.chip = chip;
	}	
}
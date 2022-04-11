package com.smca.dto;

public class EstacionDto{
	
	private Integer id;
	private String nombre;
	private String did;
	private String coordx;
	private String coordy;
	private String chip;
	private String departamento;
	private String provincia;
	private String distrito;
	private boolean estado;
	
	public EstacionDto() {
		super();
	}

	public EstacionDto(Integer id, String nombre, String did, String coordx, String coordy, String chip,String departamento,String provincia,String distrito,boolean estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.did = did;
		this.coordx = coordx;
		this.coordy = coordy;
		this.chip = chip;
		this.departamento = departamento;
		this.provincia = provincia;
		this.distrito = distrito;
		this.estado = estado;
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

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}
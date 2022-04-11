package com.smca.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="estacion")
public class Estacion implements Comparable<Estacion>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nombre", nullable=false, length=150)
	private String nombre;
	
	@Column(name="did", nullable=false, length=8)
	private String did;
	
	@Column(name="coordx", nullable=false, length=50)
	private String coordx;
	
	@Column(name="coordy", nullable=false, length=50)
	private String coordy;	
	
	@Column(name="chip", nullable=true, length=50)
	private String chip;
	
	@Column(name="orden", nullable=true)
	private Integer orden;
	
	@Column(name="direccion", nullable=true,length=250)
	private String direccion;
	
	@Column(name="nombreadministrador", nullable=true,length=150)
	private String nombreadministrador;
	
	@Column(name="apellidoadministrador", nullable=true,length=150)
	private String apellidoadministrador;
	
	@Column(name="dniadministrador", nullable=true,length=8)
	private String dniadministrador;
	
	@Column(name="telefonoadministrador", nullable=true,length=9)
	private String telefonoadministrador;
	
	@Column(name="correoadministrador", nullable=true,length=150)
	private String correoadministrador;
	
	@Column(name="estado", nullable=true)
	private boolean estado;
	
	@ManyToOne
	@JoinColumn(name="iddistrito", nullable = true)
	private Distrito distrito;
	
	@OneToMany(mappedBy="estacion", cascade = {CascadeType.ALL }, orphanRemoval = true)
	private List<Operador> operadores;
	
	public Estacion() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	
	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String dirección) {
		this.direccion = dirección;
	}

	public String getNombreadministrador() {
		return nombreadministrador;
	}

	public void setNombreadministrador(String nombreadministrador) {
		this.nombreadministrador = nombreadministrador;
	}

	public String getApellidoadministrador() {
		return apellidoadministrador;
	}

	public void setApellidodirector(String apellidoadministrador) {
		this.apellidoadministrador = apellidoadministrador;
	}

	public String getDniadministrador() {
		return dniadministrador;
	}

	public void setDniadministrador(String dniadministrador) {
		this.dniadministrador = dniadministrador;
	}

	public String getTelefonoadministrador() {
		return telefonoadministrador;
	}

	public void setTelefonoadministrador(String telefonoadministrador) {
		this.telefonoadministrador = telefonoadministrador;
	}

	public String getCorreoadministrador() {
		return correoadministrador;
	}

	public void setCorreoadministrador(String correoadministrador) {
		this.correoadministrador = correoadministrador;
	}

	public List<Operador> getOperadores() {
		return operadores;
	}

	public void setOperador(List<Operador> operadores) {
		this.operadores = operadores;
	}

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	@Override
	public int compareTo(Estacion o) {
		return this.getNombre().compareTo(o.getNombre());
	}
	
}

package com.smca.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="receptorsms")
public class Receptorsms implements Comparable<Receptorsms>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nombre",length=150,nullable=false)
	private String nombre;
	
	@Column(name="apellido",length=150,nullable=false)
	private String apellido;
	
	@Column(name="cargo",length=150,nullable=false)
	private String cargo;
	
	@Column(name="entidad",length=150,nullable=false)
	private String entidad;
	
	@Column(name="dni",length=8,nullable=false)
	private String dni;
	
	@Column(name="celular",length=150,nullable=false)
	private String celular;
	
	@Column(name="correo",length=150,nullable=false)
	private String correo;
	
	@Column(name="estado", nullable=true)
	private boolean estado;
	
	@ManyToOne
	@JoinColumn(name="idestacion")
	private Estacion estacion;	

	public Receptorsms() {
		super();
	}
	
	public Receptorsms(String nombre, String apellido, String cargo, String entidad, String dni,
			String celular, String correo, Estacion estacion) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.cargo = cargo;
		this.entidad = entidad;
		this.dni = dni;
		this.celular = celular;
		this.correo = correo;
		this.estacion = estacion;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Estacion getEstacion() {
		return estacion;
	}

	public void setEstacion(Estacion estacion) {
		this.estacion = estacion;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public int compareTo(Receptorsms o) {
		return this.getNombre().compareTo(o.getNombre());
	}

}

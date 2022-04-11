package com.smca.service;

import java.util.List;

import com.smca.model.Estacion;

public interface IEstacionService extends ICRUD<Estacion, Integer>{

	Estacion getDid(String did);
	int cambiarEstadoHabilitado(Integer idEstacion, boolean estado);
	Integer maxId();
	List<Estacion> listarHabilitadas();
}
package com.smca.service;

import com.smca.model.Receptorsms;

public interface IReceptorsmsService extends ICRUD<Receptorsms, Integer>{
	
	int cambiarEstadoHabilitado(Integer idReceptorsms, boolean estado);
}

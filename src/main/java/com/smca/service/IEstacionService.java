package com.smca.service;

import com.smca.model.Estacion;

public interface IEstacionService extends ICRUD<Estacion, Integer>{

	Estacion getDid(String did);

}
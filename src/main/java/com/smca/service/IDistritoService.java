package com.smca.service;

import java.util.List;

import com.smca.model.Distrito;

public interface IDistritoService {
	
	List<Distrito>	findByProvincia(String idprovincia);
	
	Distrito buscarPorId(String id);

}

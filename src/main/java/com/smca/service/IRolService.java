package com.smca.service;

import java.util.List;

import com.smca.model.Rol;

public interface IRolService {
	
	List<Rol> getAll();
	
	Rol getById(Integer idRol);

}

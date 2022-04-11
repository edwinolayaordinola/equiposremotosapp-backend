package com.smca.service;

import java.util.List;

import com.smca.model.Provincia;

public interface IProvinciaService {
	
	List<Provincia> getListByDepartamento(String idDepartamento);

}

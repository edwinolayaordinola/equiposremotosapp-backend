package com.smca.service;

import com.smca.model.Operador;

public interface IOperadorService extends ICRUD<Operador,Integer>{
	
	boolean habilitar(Integer id);
	
	boolean inhabilitar(Integer id);

}

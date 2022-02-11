package com.smca.service;

import java.util.List;

import com.smca.model.Usuario;

public interface IUsuarioService extends ICRUD<Usuario, Integer>{
	
	Integer maxId();
	
	List<Usuario> listarHabilitados();
	
	int eliminado(Integer id);

}

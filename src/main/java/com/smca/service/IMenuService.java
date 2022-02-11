package com.smca.service;

import java.util.List;

import com.smca.model.Menu;

public interface IMenuService {
	
	List<Menu> listarMenuPorUsuario(String username);
	
	List<Menu> listar();

}

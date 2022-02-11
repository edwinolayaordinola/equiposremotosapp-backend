package com.smca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smca.model.Menu;
import com.smca.repo.IMenuRepo;
import com.smca.service.IMenuService;

@Service
public class MenuServiceImpl implements IMenuService{

	@Autowired
	private IMenuRepo repo;

	@Override
	public List<Menu> listarMenuPorUsuario(String username) {
		/*List<Menu> menus = new ArrayList<>();
		repo.listarMenuPorUsuario(nombre).forEach(x -> {
			Menu m = new Menu();
			m.setIdMenu((Integer.parseInt(String.valueOf(x[0]))));
			m.setIcono(String.valueOf(x[1]));
			m.setNombre(String.valueOf(x[2]));
			m.setUrl(String.valueOf(x[3]));		
			
			menus.add(m);
		});
		return menus;*/
		return repo.listarMenuPorUsuario(username);
	}
	
	@Override 
	public List<Menu> listar(){
		return repo.findAll();
	}

}

package com.smca.service;

import java.util.List;

import com.smca.dto.UsuarioItemDto;
import com.smca.model.Usuario;

public interface IUsuarioService extends ICRUD<Usuario, Integer>{
	
	Integer maxId();	
	List<Usuario> listarHabilitados();	
	int eliminado(Integer id);	
	int habilitar(Integer id);	
	int inhabilitar(Integer id);	
	boolean getEstado(String usuario);
	List<UsuarioItemDto> listarItem();
	List<Usuario> estadoUsuario(String usuario);

}

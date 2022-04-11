package com.smca.dto;

import com.smca.model.Usuario;

public class UsuarioItemDto {
	
	Integer item;
	Usuario usuario;
	
	public UsuarioItemDto(Integer item, Usuario usuario) {
		super();
		this.item = item;
		this.usuario = usuario;
	}
	public Integer getItem() {
		return item;
	}
	public void setItem(Integer item) {
		this.item = item;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}

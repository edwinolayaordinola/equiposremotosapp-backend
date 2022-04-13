package com.smca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smca.componente.Ldap;
import com.smca.dto.UsuarioLoginDto;
import com.smca.service.IUsuarioService;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	IUsuarioService usuarioService;
	
	@PostMapping(value="/usuariologinldap")
	public ResponseEntity<Boolean> loginLdap(@RequestBody UsuarioLoginDto usuarioLoginDto) throws Exception{
		
		Ldap mildap = new Ldap();
		int rpta = mildap.validarUsuario(usuarioLoginDto.getUsuario(), usuarioLoginDto.getContrasenia());
		if(rpta == 1) {
			if(usuarioService.getEstado(usuarioLoginDto.getUsuario())) {
				return new ResponseEntity<Boolean>(true,HttpStatus.OK);
			}
		}
		return new ResponseEntity<Boolean>(false,HttpStatus.OK);
	}
	
	@GetMapping(value="/estadousuario/{usuario}")
	public ResponseEntity<Boolean> estadoUsuario(@PathVariable(value="usuario") String usuario) throws Exception{
		System.out.println(usuario);
		System.out.println(usuarioService.estadoUsuario(usuario).size());
		Boolean estado = usuarioService.estadoUsuario(usuario).size()>0 ? true : false; 
		return new ResponseEntity<Boolean>(estado,HttpStatus.OK);
	}

}

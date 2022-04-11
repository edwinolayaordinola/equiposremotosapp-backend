package com.smca.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smca.model.Usuario;
import com.smca.service.IUsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	IUsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listar(){		
		
		List<Usuario> usuarios = new ArrayList<>();
		usuarioService.listar().stream().sorted().forEach(obj->{
			usuarios.add(obj);
		});		
		return new ResponseEntity<List<Usuario>>(usuarios,HttpStatus.OK);
	}
	
	/*@GetMapping
	public ResponseEntity<List<UsuarioItemDto>> listar(){
		
		return new ResponseEntity<List<UsuarioItemDto>>(usuarioService.listarItem(),HttpStatus.OK); 
	}*/
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Usuario p = usuarioService.listarPorId(id);
		return new ResponseEntity<Usuario>(p, HttpStatus.OK);
	}	
	
	@PostMapping
	public ResponseEntity<Usuario> registrar(@RequestBody Usuario usuario){
		
		/*if(usuario.getPassword().equals("123456")) {
			usuario.setIdUsuario(usuarioService.maxId()+1);
			usuario.setPassword(BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt(11)));
		}*/
		usuario.setIdUsuario(usuarioService.maxId()+1);
		usuario.setPassword(BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt(11)));		
		return new ResponseEntity<Usuario>(usuarioService.registrar(usuario),HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Usuario> modificar(@RequestBody Usuario usuario){		
		
		/*if(usuario.getPassword().equals("123456")) {
			usuario.setPassword(BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt(11)));
		}*/
		usuario.setPassword(BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt(11)));
		Usuario obj = usuarioService.modificar(usuario);
		return new ResponseEntity<Usuario>(obj, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}/{estado}")
	public ResponseEntity<Integer> eliminado(@PathVariable("id") Integer id,@PathVariable("estado") Integer estado){
		
		if(estado.equals(0)) {
			return new ResponseEntity<Integer>(usuarioService.inhabilitar(id), HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(usuarioService.habilitar(id), HttpStatus.OK);
	}

}

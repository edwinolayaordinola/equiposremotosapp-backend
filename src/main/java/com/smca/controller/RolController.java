package com.smca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smca.model.Rol;
import com.smca.service.IRolService;

@RestController
@RequestMapping("/roles")
public class RolController {
		
	@Autowired
	IRolService rolService;
	
	@GetMapping
	public ResponseEntity<List<Rol>> listar(){
		return new ResponseEntity<List<Rol>>(rolService.getAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Rol> getById(@PathVariable("id") Integer id){
		return new ResponseEntity<Rol>(rolService.getById(id),HttpStatus.OK);
	}

}

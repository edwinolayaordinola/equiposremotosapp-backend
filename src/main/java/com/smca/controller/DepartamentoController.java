package com.smca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smca.model.Departamento;
import com.smca.service.IDepartamentoService;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {
	
	@Autowired
	private IDepartamentoService departamentoService;
	
	@GetMapping
	public ResponseEntity<List<Departamento>> getAll(){
		return new ResponseEntity<List<Departamento>>(departamentoService.getAll(),HttpStatus.OK);
	}

}

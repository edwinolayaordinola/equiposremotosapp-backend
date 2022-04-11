package com.smca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smca.model.Distrito;
import com.smca.service.IDistritoService;

@RestController
@RequestMapping("/distritos")
public class DistritoController {	
		
	@Autowired
	private IDistritoService distritoService;
	
	@GetMapping("/{id}")
	public ResponseEntity<Distrito> buscarPorId(@PathVariable("id") String id){
		return new ResponseEntity<Distrito>(distritoService.buscarPorId(id), HttpStatus.OK);
	}
	
	@GetMapping(path="/provincia/{idprovincia}")
	public ResponseEntity<List<Distrito>> findByProvincia(@PathVariable("idprovincia") String idprovincia){
		return new ResponseEntity<List<Distrito>>(distritoService.findByProvincia(idprovincia),HttpStatus.OK);
	}
	
}

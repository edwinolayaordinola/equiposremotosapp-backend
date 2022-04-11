package com.smca.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smca.model.Receptorsms;
import com.smca.service.IReceptorsmsService;

@RestController
@RequestMapping("/receptorsms")
public class ReceptorsmsController {
	
	@Autowired
	private IReceptorsmsService receptorsmsService;
	
	
	@GetMapping
	public ResponseEntity<List<Receptorsms>> listar(){		
		List<Receptorsms> receptores  = new ArrayList<>();
		receptorsmsService.listar().stream().sorted().forEach(obj->{
			receptores.add(obj);
		});
		return new ResponseEntity<List<Receptorsms>>(receptores, HttpStatus.OK);
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<Receptorsms> listarPorId(@PathVariable Integer id){		
		return new ResponseEntity<Receptorsms>(receptorsmsService.listarPorId(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Integer> registrar(@RequestBody Receptorsms receptorsms){
		
		return new ResponseEntity<Integer>(receptorsmsService.registrar(receptorsms).getId(), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Integer> modificar(@RequestBody Receptorsms receptorsms){
		
		return new ResponseEntity<Integer>(receptorsmsService.modificar(receptorsms).getId(), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}/{estado}")
	public ResponseEntity<Integer> cambiarEstadoHabilitar(@PathVariable("id") Integer id, @PathVariable("estado") Boolean estado){
		return new ResponseEntity<Integer>(receptorsmsService.cambiarEstadoHabilitado(id, estado),HttpStatus.OK);
	}

}

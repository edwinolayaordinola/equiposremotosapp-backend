package com.smca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smca.model.Provincia;
import com.smca.service.IProvinciaService;

@RestController
@RequestMapping("/provincias")
public class ProvinciaController {
	
	@Autowired
	private IProvinciaService provinciaService;
	
	@GetMapping("/{iddepartamento}")
	public ResponseEntity<List<Provincia>> getListByDepartamento(@PathVariable("iddepartamento") String iddepartamento){
		return new ResponseEntity<List<Provincia>>(provinciaService.getListByDepartamento(iddepartamento),HttpStatus.OK);
		
	}
}

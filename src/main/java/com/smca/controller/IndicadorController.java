package com.smca.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smca.dto.ClassIndicadorGraficoDto;
import com.smca.service.IEstacionService;
import com.smca.service.IIndicadorService;

@RestController
@RequestMapping("/indicadores")
public class IndicadorController {
	
	@Autowired
	private IIndicadorService service;
	@Autowired
	private IEstacionService estacionService;
	String fechas[];
	
	@GetMapping("/{id}")
	public ResponseEntity<List<ClassIndicadorGraficoDto>> listaIndicadorGrafico(@PathVariable("id") Integer id){
		
		List<ClassIndicadorGraficoDto> dto = new ArrayList<ClassIndicadorGraficoDto>();
		
		service.listaIndicadorPorEstacion(id).forEach(obj->{
			ClassIndicadorGraficoDto igdto = new ClassIndicadorGraficoDto();
			igdto.setCloro(obj.getCloro());
			igdto.setPh(obj.getPh());
			igdto.setTemperatura(obj.getTemperatura());
			igdto.setEstacion(estacionService.listarPorId(obj.getIdestacion()).getNombre());
			fechas = obj.getFecharegistro().split("-");
			igdto.setFecharegistro(fechas[2]+"/"+fechas[1]+"/"+fechas[0].substring(2,4));
			dto.add(igdto);
		});
		
		return new ResponseEntity<List<ClassIndicadorGraficoDto>>(dto, HttpStatus.OK);
	}

}

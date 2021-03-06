package com.smca.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smca.dto.IndicadorGraficoDto;
import com.smca.dto.IndicadorRecienteDto;
import com.smca.dto.IndicadoresRecientesDto;
import com.smca.model.Indicador;
import com.smca.repo.IIndicadorRepo;
import com.smca.service.IIndicadorService;

@Service
public class IndicadorServiceImpl implements IIndicadorService{
	
	@Autowired
	IIndicadorRepo repo;
	
	DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");	
	LocalDateTime fecha;

	@Override
	public Indicador registrar(Indicador obj) {
		return repo.save(obj);
	}

	@Override
	public Indicador modificar(Indicador obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public List<Indicador> listar() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Indicador listarPorId(Integer id) {
		// TODO Auto-generated method stub
		Optional<Indicador> ind = repo.findById(id);
		return ind.isPresent() ? ind.get() : new Indicador();
		
	}

	@Override
	public boolean eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<IndicadoresRecientesDto> getRecientes(){		
		return repo.getRecientes();
	}
	
	@Override
	public Indicador get(Integer idestacion, LocalDateTime fecharegistro) {
		return repo.get(idestacion, fecharegistro);
	}

	@Override
	public List<IndicadorGraficoDto> listaIndicadorPorEstacion(Integer idestacion) {
		return repo.listaIndicadorPorEstacion(idestacion);
	}

	@Override
	public Indicador getReciente(Integer idEstacion) {
		return repo.getReciente(idEstacion);
		
	}
	
}
package com.smca.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smca.dto.IndicadorCorrienteRecienteDto;
import com.smca.dto.IndicadoresCorrienteRecientesDto;
import com.smca.model.IndicadorCorriente;
import com.smca.repo.IIndicadorCorrienteRepo;
import com.smca.service.IIndicadorCorrienteService;

@Service
public class IndicadorCorrienteServiceImpl implements IIndicadorCorrienteService{
	
	@Autowired
	IIndicadorCorrienteRepo repo;

	
	@Override
	public IndicadorCorriente registrar(IndicadorCorriente obj) {
		return repo.save(obj);
	}

	@Override
	public IndicadorCorriente modificar(IndicadorCorriente obj) {
		return repo.save(obj);
	}

	@Override
	public List<IndicadorCorriente> listar() {
		return repo.findAll();
	}

	@Override
	public IndicadorCorriente listarPorId(Integer id) {
		Optional<IndicadorCorriente> ind = repo.findById(id);
		return ind.isPresent() ? ind.get() : new IndicadorCorriente();
	}

	@Override
	public boolean eliminar(Integer id) {
		return false;
	}

	@Override
	public List<IndicadoresCorrienteRecientesDto> getRecientes() {
		return repo.getRecientes();
	}
	
	

	@Override
	public IndicadorCorriente get(Integer idestacion, LocalDateTime fecharegistro) {
		return repo.get(idestacion, fecharegistro);
	}

	@Override
	public IndicadorCorrienteRecienteDto reciente(Integer idEstacion) {
		return repo.reciente(idEstacion);
	}

	@Override
	public IndicadorCorriente getReciente(Integer idEstacion) {
		return repo.getReciente(idEstacion);
	}

}

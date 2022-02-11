package com.smca.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smca.dto.FiltroReporteDto;
import com.smca.model.Indicador;
import com.smca.repo.IReporteRepo;
import com.smca.service.IReporteService;

@Service
public class ReporteServiceImpl implements IReporteService{
	
	@Autowired
	IReporteRepo repo;
	
	@Override
	public List<Indicador> getReporte(FiltroReporteDto filtro) {
		LocalDateTime fechaFin = filtro.getFechaFinConsulta().plusDays(1);
		return repo.getReporte(filtro.getIdEstacion(),filtro.getFechaInicioConsulta(),fechaFin);
	}

}

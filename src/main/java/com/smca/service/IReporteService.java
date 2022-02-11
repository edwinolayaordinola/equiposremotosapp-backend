package com.smca.service;

import java.util.List;

import com.smca.dto.FiltroReporteDto;
import com.smca.model.Indicador;

public interface IReporteService{
	
	List<Indicador> getReporte(FiltroReporteDto filtro);
}

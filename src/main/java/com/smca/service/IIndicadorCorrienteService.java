package com.smca.service;

import java.time.LocalDateTime;
import java.util.List;

import com.smca.dto.IndicadorCorrienteRecienteDto;
import com.smca.dto.IndicadoresCorrienteRecientesDto;
import com.smca.model.IndicadorCorriente;

public interface IIndicadorCorrienteService extends ICRUD<IndicadorCorriente, Integer>{
	
	List<IndicadoresCorrienteRecientesDto> getRecientes();
	IndicadorCorrienteRecienteDto reciente(Integer idEstacion);	
	IndicadorCorriente get(Integer idestacion, LocalDateTime fecharegistro);
	IndicadorCorriente getReciente(Integer idEstacion);
}

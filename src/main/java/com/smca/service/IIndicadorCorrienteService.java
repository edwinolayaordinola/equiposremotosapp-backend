package com.smca.service;

import java.time.LocalDateTime;
import java.util.List;

import com.smca.dto.IndicadoresCorrienteRecientesDto;
import com.smca.model.IndicadorCorriente;

public interface IIndicadorCorrienteService extends ICRUD<IndicadorCorriente, Integer>{
	
	List<IndicadoresCorrienteRecientesDto> getRecientes();
	
	IndicadorCorriente get(Integer idestacion, LocalDateTime fecharegistro);
}

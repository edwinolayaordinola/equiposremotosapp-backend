package com.smca.service;

import java.time.LocalDateTime;
import java.util.List;

import com.smca.dto.IndicadorGraficoDto;
import com.smca.dto.IndicadorRecienteDto;
import com.smca.dto.IndicadoresRecientesDto;
import com.smca.model.Indicador;

public interface IIndicadorService extends ICRUD<Indicador, Integer>{
	
	List<IndicadoresRecientesDto> getRecientes();
	Indicador getReciente(Integer idEstacion);
	Indicador get(Integer idestacion, LocalDateTime fecharegistro);	
	List<IndicadorGraficoDto> listaIndicadorPorEstacion(Integer idestacion);
}

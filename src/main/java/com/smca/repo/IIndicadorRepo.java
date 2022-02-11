package com.smca.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smca.dto.IndicadorGraficoDto;
import com.smca.dto.IndicadoresRecientesDto;
import com.smca.model.Indicador;

@Repository
public interface IIndicadorRepo extends JpaRepository<Indicador, Integer>{
	
	@Query(value="select  idestacion, max(fecharegistro) as fecharegistro from indicador group by idestacion",nativeQuery=true)
	List<IndicadoresRecientesDto> getRecientes();
	
	
	@Query(value="select id,cloro, ph, temperatura,caudal,fecharegistro,idestacion from indicador where idestacion=?1 and fecharegistro=?2 limit 1",nativeQuery=true)
	Indicador get(Integer idestacion, LocalDateTime fecharegistro);
	
	//@Query(value="select cloro, ph, temperatura,fecharegistro from indicador where idestacion=?1 order by fecharegistro asc",nativeQuery=true)
	@Query(value="select ROUND(cast(avg(cloro) as numeric),2)  cloro, ROUND(cast(avg(ph) as numeric),2)  ph, ROUND(cast(avg(temperatura) as numeric),2)  temperatura, idestacion,cast(cast(fecharegistro as date) as varchar) fecharegistro from indicador where idestacion=?1 group by idestacion, cast(fecharegistro as date) order by fecharegistro asc limit 10",nativeQuery=true)
	List<IndicadorGraficoDto> listaIndicadorPorEstacion(Integer idestacion);
	
}

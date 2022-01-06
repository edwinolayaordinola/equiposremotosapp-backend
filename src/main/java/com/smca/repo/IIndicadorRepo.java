package com.smca.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smca.dto.IndicadoresRecientesDto;
import com.smca.model.Indicador;

@Repository
public interface IIndicadorRepo extends JpaRepository<Indicador, Integer>{
	
	@Query(value="select  idestacion, max(fecharegistro) as fecharegistro from indicador group by idestacion",nativeQuery=true)
	List<IndicadoresRecientesDto> getRecientes();
	
	
	@Query(value="select id,cloro, ph, temperatura,fecharegistro,idestacion from indicador where idestacion=?1 and fecharegistro=?2 limit 1",nativeQuery=true)
	Indicador get(Integer idestacion, LocalDateTime fecharegistro);
	
}

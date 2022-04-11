package com.smca.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smca.dto.IndicadorCorrienteRecienteDto;
import com.smca.dto.IndicadoresCorrienteRecientesDto;
import com.smca.model.Indicador;
import com.smca.model.IndicadorCorriente;

@Repository
public interface IIndicadorCorrienteRepo extends JpaRepository<IndicadorCorriente, Integer>{
	
	@Query(value="select  idestacion, max(fecharegistro) as fecharegistro from indicadorcorriente group by idestacion",nativeQuery=true)
	List<IndicadoresCorrienteRecientesDto> getRecientes();
	
	@Query(value="select  idestacion, max(fecharegistro) as fecharegistro from indicadorcorriente where idestacion=?1 group by idestacion",nativeQuery=true)
	IndicadorCorrienteRecienteDto reciente(Integer idEstacion);
	
	@Query(value="select ind.* from indicadorcorriente ind , (select  idestacion, max(fecharegistro) as fecharegistro from indicadorcorriente where idestacion=?1 group by idestacion) tmp where ind.idestacion=tmp.idestacion and ind.fecharegistro=tmp.fecharegistro order by ind.fecharegistro",nativeQuery=true)
	IndicadorCorriente getReciente(Integer idEstacion);
	
	
	@Query(value="select id,corrientedc,corrienteac,fecharegistro,idestacion from indicadorcorriente where idestacion=?1 and fecharegistro=?2 limit 1",nativeQuery=true)
	IndicadorCorriente get(Integer idestacion, LocalDateTime fecharegistro);

}

package com.smca.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smca.model.Indicador;

@Repository
public interface IReporteRepo extends JpaRepository<Indicador, Integer> {
	
	//JPQL
	@Query(value="FROM Indicador ind where ind.estacion.id=?1 and ind.fecharegistro BETWEEN ?2 and ?3")
	List<Indicador> getReporte(Integer idEstación, LocalDateTime fechaInicio, LocalDateTime fechaFin);
	
	
}

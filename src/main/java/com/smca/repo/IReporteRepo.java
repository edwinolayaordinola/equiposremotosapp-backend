package com.smca.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smca.model.Indicador;

@Repository
public interface IReporteRepo extends JpaRepository<Indicador, Integer> {
	
	//JPQL
	@Query(value="select * FROM Indicador ind where ind.idestacion=?1 and ind.fecharegistro BETWEEN ?2 and ?3",nativeQuery=true)
	List<Indicador> getReporte(Integer idEstación, LocalDate fechaInicio, LocalDate fechaFin);
	
	
}

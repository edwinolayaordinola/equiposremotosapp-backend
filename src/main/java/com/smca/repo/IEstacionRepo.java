package com.smca.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.smca.model.Estacion;

//@Repository
public interface IEstacionRepo extends JpaRepository<Estacion, Integer>{
	
	@Query(value="select * from estacion where did=?1",nativeQuery=true)
	Estacion getDid(String did);
	
	@Query(value="select * from estacion where estado=true order by orden asc",nativeQuery=true)
	List<Estacion> listarHabilitadas();
	
	@Transactional
	@Modifying
	@Query(value="update estacion set estado=?2 where id=?1",nativeQuery=true)
	int cambiarEstadoHabilitado(Integer idEstacion, boolean estado);
	
	@Query(value="select max(id) from estacion",nativeQuery=true)
	Integer maxId();
}

package com.smca.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smca.model.Estacion;

//@Repository
public interface IEstacionRepo extends JpaRepository<Estacion, Integer>{
	
	@Query(value="select * from estacion where did=?1",nativeQuery=true)
	Estacion getDid(String did);
}

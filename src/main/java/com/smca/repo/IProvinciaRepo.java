package com.smca.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smca.model.Provincia;

@Repository
public interface IProvinciaRepo extends JpaRepository<Provincia, Integer>{
	
	@Query(value = "select * from provincia where iddepartamento=?1",nativeQuery=true)
	List<Provincia> getListByDepartamento(String idDepartamento);
	

}

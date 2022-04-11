package com.smca.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.smca.model.Operador;

public interface IOperadorRepo extends JpaRepository<Operador, Integer>{
	
	@Transactional
	@Query(value="update estacion set estado= true where id=?1",nativeQuery=true)
	boolean habilitar(Integer id);
	
	@Transactional
	@Query(value="update estacion set estado= false where id=?1",nativeQuery=true)
	boolean inhabilitar(Integer id);

}

package com.smca.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.smca.model.Receptorsms;

@Repository
public interface IReceptorsmsRepo extends JpaRepository<Receptorsms,Integer>{
	
	@Transactional
	@Modifying
	@Query(value="update receptorsms set estado=?2 where id=?1",nativeQuery=true)
	int cambiarEstadoHabilitado(Integer idReceptorsms, boolean estado);
}

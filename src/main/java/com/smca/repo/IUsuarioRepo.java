package com.smca.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smca.model.Usuario;

@Repository
public interface IUsuarioRepo extends JpaRepository<Usuario, Integer>{
	
	Usuario findOneByUsername(String username);
	
	@Query(value="select max(id_usuario) from usuario",nativeQuery=true)
	Integer maxId();
	
	@Query(value="select * from usuario where estado=true",nativeQuery=true)
	List<Usuario> listarHabilitados();
	
	@Transactional
	@Modifying	
	@Query(value="update usuario set estado=false where id_usuario=?1",nativeQuery=true)
	int eliminado(Integer id);
	
	@Transactional
	@Modifying	
	@Query(value="update usuario set estado=true where id_usuario=?1",nativeQuery=true)
	int habilitar(Integer id);
	
	@Transactional
	@Modifying	
	@Query(value="update usuario set estado=false where id_usuario=?1",nativeQuery=true)
	int inhabilitar(Integer id);
	
	@Query(value="select estado from usuario where username=?1",nativeQuery=true)
	boolean getEstado(String usuario);

}

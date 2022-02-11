package com.smca;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.smca.model.Usuario;
import com.smca.repo.IUsuarioRepo;

@SpringBootTest
class EquiposremotosappBackendApplicationTests {
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Autowired
	private IUsuarioRepo repo;

	@Test
	void crearUsuario() {
		
		Usuario us = new Usuario();
		us.setIdUsuario(1);
		us.setUsername("eolaya@sunass.gob.pe");
		us.setPassword(bcrypt.encode("123456"));
		us.setEnabled(true);
		
		Usuario retorno = repo.save(us);
		
		assertTrue(retorno.getPassword().equalsIgnoreCase(us.getPassword()));
	}

}

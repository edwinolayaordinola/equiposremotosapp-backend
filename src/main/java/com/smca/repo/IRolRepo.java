package com.smca.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smca.model.Rol;

public interface IRolRepo extends JpaRepository<Rol, Integer>{
	
}

package com.smca.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smca.model.Rol;
import com.smca.repo.IRolRepo;
import com.smca.service.IRolService;

@Service
public class RolServiceImpl implements IRolService{
	
	@Autowired
	IRolRepo repo;
	
	@Override
	public List<Rol> getAll() {
		return repo.findAll();
	}
	
	@Override
	public Rol getById(Integer idRol) {
		// TODO Auto-generated method stub
		Optional<Rol> op= repo.findById(idRol);
		return op.isPresent() ? op.get()  : new Rol();				
	}

}

package com.smca.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smca.model.Distrito;
import com.smca.repo.IDistritoRepo;
import com.smca.service.IDistritoService;

@Service
public class DistritoServiceImpl implements IDistritoService{
	
	@Autowired
	private IDistritoRepo repo;
	
	@Override
	public List<Distrito> findByProvincia(String idprovincia) {
		return repo.findByProvincia(idprovincia);
	}

	@Override
	public Distrito buscarPorId(String id) {
		return repo.buscarPorId(id);
	}

}

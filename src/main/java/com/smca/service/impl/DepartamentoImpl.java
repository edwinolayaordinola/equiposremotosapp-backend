package com.smca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smca.model.Departamento;
import com.smca.repo.IDepartamentoRepo;
import com.smca.service.IDepartamentoService;

@Service
public class DepartamentoImpl implements IDepartamentoService{
	
	@Autowired
	private IDepartamentoRepo repo;
	
	@Override
	public List<Departamento> getAll() {
		return repo.findAll();
	}

}

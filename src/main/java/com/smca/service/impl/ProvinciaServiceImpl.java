package com.smca.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smca.model.Provincia;
import com.smca.repo.IProvinciaRepo;
import com.smca.service.IProvinciaService;

@Service
public class ProvinciaServiceImpl implements IProvinciaService{
	
	@Autowired
	private IProvinciaRepo repo;

	@Override
	public List<Provincia> getListByDepartamento(String idDepartamento) {
		// TODO Auto-generated method stub
		return repo.getListByDepartamento(idDepartamento);
	}
	
	

}

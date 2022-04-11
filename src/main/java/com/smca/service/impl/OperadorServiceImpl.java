package com.smca.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.smca.model.Operador;
import com.smca.repo.IOperadorRepo;
import com.smca.service.IOperadorService;

public class OperadorServiceImpl implements IOperadorService{
	
	@Autowired
	IOperadorRepo repo;

	@Override
	public Operador registrar(Operador obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public Operador modificar(Operador obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public List<Operador> listar() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Operador listarPorId(Integer id) {
		// TODO Auto-generated method stub
		Optional<Operador> op = repo.findById(id);
		return op.isPresent() ? op.get() : new Operador();
	}

	@Override
	public boolean eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean habilitar(Integer id) {
		// TODO Auto-generated method stub
		return repo.habilitar(id);
	}

	@Override
	public boolean inhabilitar(Integer id) {
		// TODO Auto-generated method stub
		return repo.inhabilitar(id);
	}

}

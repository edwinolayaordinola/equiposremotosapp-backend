package com.smca.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smca.model.Estacion;
import com.smca.repo.IEstacionRepo;
import com.smca.service.IEstacionService;

@Service
public class EstacionServiceImpl implements IEstacionService{
	
	@Autowired
	private IEstacionRepo repo;

	@Override
	public Estacion registrar(Estacion obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public Estacion modificar(Estacion obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public List<Estacion> listar() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Estacion listarPorId(Integer id) {
		// TODO Auto-generated method stub
		Optional<Estacion> op = repo.findById(id);
		return op.isPresent() ? op.get() : new Estacion();
	}

	@Override
	public boolean eliminar(Integer id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
		return true;
	}

	@Override
	public Estacion getDid(String did) {
		// TODO Auto-generated method stub
		return repo.getDid(did);
	}

}

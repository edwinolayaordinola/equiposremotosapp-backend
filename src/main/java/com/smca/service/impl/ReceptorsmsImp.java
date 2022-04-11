package com.smca.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smca.controller.ReceptorsmsController;
import com.smca.model.Receptorsms;
import com.smca.repo.IReceptorsmsRepo;
import com.smca.service.IReceptorsmsService;

@Service
public class ReceptorsmsImp implements IReceptorsmsService{
	
	@Autowired
	IReceptorsmsRepo repo;	

	@Override
	public Receptorsms registrar(Receptorsms obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public Receptorsms modificar(Receptorsms obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public List<Receptorsms> listar() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Receptorsms listarPorId(Integer id) {
		// TODO Auto-generated method stub
		Optional<Receptorsms> op = repo.findById(id);
		return op.isPresent() ? op.get() : new Receptorsms();
	}

	@Override
	public boolean eliminar(Integer id) {
		// TODO Auto-generated method stub
		repo.delete(null);
		return true;
	}
	
	@Transactional
	@Override
	public int cambiarEstadoHabilitado(Integer idReceptorsms, boolean estado) {
		// TODO Auto-generated method stub
		return repo.cambiarEstadoHabilitado(idReceptorsms, estado);
	}

}

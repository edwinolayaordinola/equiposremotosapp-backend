package com.smca.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smca.model.Estacion;
import com.smca.repo.IEstacionRepo;
import com.smca.repo.IOperadorRepo;
import com.smca.service.IEstacionService;

@Service
public class EstacionServiceImpl implements IEstacionService{
	
	@Autowired
	private IEstacionRepo estacionRepo;
	
	@Transactional
	@Override
	public Estacion registrar(Estacion estacion) {
		
		estacion.getOperadores().forEach(obj->{
			obj.setEstacion(estacion);
		});		
		return estacionRepo.save(estacion);
	}

	@Override
	public Estacion modificar(Estacion estacion) {
		estacion.getOperadores().forEach(obj->{
			obj.setEstacion(estacion);
		});		
		return estacionRepo.save(estacion);
	}

	@Override
	public List<Estacion> listar() {
		return estacionRepo.findAll();
	}

	@Override
	public Estacion listarPorId(Integer id) {
		Optional<Estacion> op = estacionRepo.findById(id);
		return op.isPresent() ? op.get() : new Estacion();
	}

	@Override
	public boolean eliminar(Integer id) {
		estacionRepo.deleteById(id);
		return true;
	}

	@Override
	public Estacion getDid(String did) {
		return estacionRepo.getDid(did);
	}
	
	@Transactional
	@Override
	public int cambiarEstadoHabilitado(Integer idEstacion, boolean estado) {
		return estacionRepo.cambiarEstadoHabilitado(idEstacion, estado);
	}	
	
	@Override
	public Integer maxId() {
		return estacionRepo.maxId();
	}

	@Override
	public List<Estacion> listarHabilitadas() {
		// TODO Auto-generated method stub
		return estacionRepo.listarHabilitadas();
	}

}

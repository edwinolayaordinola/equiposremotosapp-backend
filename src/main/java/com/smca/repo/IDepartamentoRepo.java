package com.smca.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smca.model.Departamento;

@Repository
public interface IDepartamentoRepo extends JpaRepository<Departamento, Integer>{	

}

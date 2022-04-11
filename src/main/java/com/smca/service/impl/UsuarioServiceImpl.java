package com.smca.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smca.dto.UsuarioItemDto;
import com.smca.model.Usuario;
import com.smca.repo.IUsuarioRepo;
import com.smca.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements UserDetailsService, IUsuarioService{

	@Autowired
	private IUsuarioRepo repo;		
	int item = 0; 
	List<UsuarioItemDto> usuariosItemsDto;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = repo.findOneByUsername(username);
		
		if(usuario == null) {
			throw new UsernameNotFoundException(String.format("Usuario no existe", username));
		}
		
		List<GrantedAuthority> roles = new ArrayList<>();
		
		usuario.getRoles().forEach(rol -> {
			roles.add(new SimpleGrantedAuthority(rol.getNombre()));
		});
		
		UserDetails ud = new User(usuario.getUsername(), usuario.getPassword(), roles);
		
		return ud;
	}

	@Override
	public Usuario registrar(Usuario obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public Usuario modificar(Usuario obj) {
		// TODO Auto-generated method stub
		return repo.save(obj);
	}

	@Override
	public List<Usuario> listar() {
		return repo.findAll();
	}
	
	@Override
	public List<UsuarioItemDto> listarItem() {
		
		item = 0;
		usuariosItemsDto = new ArrayList<>();
		repo.findAll().forEach(obj->{			
			item++;
			UsuarioItemDto dto = new UsuarioItemDto(item,obj);
			usuariosItemsDto.add(dto);
		});		
		return usuariosItemsDto;
	}

	@Override
	public Usuario listarPorId(Integer id) {
		// TODO Auto-generated method stub
		Optional<Usuario> op = repo.findById(id);
		return op.isPresent() ? op.get()  : new Usuario();
	}

	@Override
	public boolean eliminar(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Integer maxId() {
		// TODO Auto-generated method stub
		return repo.maxId();
	}

	@Override
	public List<Usuario> listarHabilitados() {
		// TODO Auto-generated method stub
		return repo.listarHabilitados();
	}

	@Override
	public int eliminado(Integer id) {
		// TODO Auto-generated method stub
		return repo.eliminado(id);
	}

	@Override
	public int habilitar(Integer id) {
		// TODO Auto-generated method stub
		return repo.habilitar(id);
	}

	@Override
	public int inhabilitar(Integer id) {
		// TODO Auto-generated method stub
		return repo.inhabilitar(id);
	}

	@Override
	public boolean getEstado(String usuario) {
		// TODO Auto-generated method stub
		return repo.getEstado(usuario);
	}

}

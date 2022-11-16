package com.nocountry.servicios;

import java.util.Optional;

import com.nocountry.entidades.User;

public interface UserDAO {
	
	public Optional<User> buscarPorId(Long id);
	
	public User guardar(User entidad);
	
	public User actualizar(Long userId, User user);
	
	public void eliminarPorId(Long id);

//	public User asociarService(Long userId, Long serviceId);

}

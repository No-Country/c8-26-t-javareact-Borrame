package com.nocountry.servicios;


import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nocountry.entidades.User;
import com.nocountry.excepciones.NotFoundException;
import com.nocountry.repositorios.UserRepository;

@Service
public class UserDAOImpl implements UserDAO {
	
	
	private final UserRepository userRepository;
	
//	@Autowired
//	private ServiceDAO serviceDao;
	
	@Autowired
	public UserDAOImpl(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	@Override
	@Transactional
	public Optional<User> buscarPorId(Long id) {
		return userRepository.findById(id);
	}

	@Override
	@Transactional
	public User guardar(User user) {
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public User actualizar(Long userId, User user) {
			Optional<User> oUser = userRepository.findById(userId);

			if (!oUser.isPresent())
				throw new NotFoundException(String.format("El usuario con id: %d no existe", userId));

			User userActualizado = null;

			oUser.get().setUserName(user.getUserName());
			oUser.get().setUserSurname(user.getUserSurname());
			userActualizado = userRepository.save(oUser.get());

			return userActualizado;
		}
	
	/*
	@Override
	@Transactional
	public User asociarService(Long userId, Long serviceId){
	
	return
	} 
	*/ 
	 


	@Override
	@Transactional
	public void eliminarPorId(Long id) {
		userRepository.deleteById(id);
	}
	

}

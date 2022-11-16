package com.nocountry.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nocountry.entidades.User;
import com.nocountry.excepciones.NotFoundException;
import com.nocountry.servicios.UserDAO;

@RestController
@RequestMapping("/")
public class UserController {
	
	private final static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserDAO userDao;
	
	@PostMapping("/usuario")
	public ResponseEntity<?> crearUsuario(@RequestBody User user){
		
		User userSaved = userDao.guardar(user);
		return new ResponseEntity<User>(userSaved,HttpStatus.CREATED);
	}
	
	@PutMapping("/usuario/actualizar/userId/{userId}")
	public ResponseEntity<?> actualuzarUsuario(@PathVariable Long userId, @RequestBody User user, BindingResult result){
		
		Map<String, Object> validaciones = new HashMap<String, Object>();
		if (result.hasErrors()) {
			List<String> listaErrores = result.getFieldErrors().stream()
					.map(errores -> "Campo: " + errores.getField() + " " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
		}
		
		User userUpdate = null;
		try {
		userUpdate=userDao.actualizar(userId, userUpdate);
		} catch (Exception e) {
			logger.info(e.getMessage());
			throw e;
		}
		return new ResponseEntity<User>(userUpdate, HttpStatus.OK);
	}
	
	@DeleteMapping("/usuario/eliminar/userId/{userId}")
	public ResponseEntity<?> eliminarUsuario(@PathVariable Long userId){
		
		Optional<User> oUser = userDao.buscarPorId(userId);
		if(!oUser.isPresent())
			throw new NotFoundException(String.format("El usuario con esa id: %d no existe" , userId));
		
		userDao.eliminarPorId(oUser.get().getIdUser());
		
		
		return new ResponseEntity <String> ("El cliente con id: " + userId + " se elimino ", HttpStatus.NO_CONTENT);
		
	}
	
	

}

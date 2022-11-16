package com.nocountry.repositorios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nocountry.entidades.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}

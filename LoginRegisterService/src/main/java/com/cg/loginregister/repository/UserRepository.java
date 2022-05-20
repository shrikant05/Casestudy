package com.cg.loginregister.repository;

import java.util.Optional;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.cg.loginregister.model.User;


public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}

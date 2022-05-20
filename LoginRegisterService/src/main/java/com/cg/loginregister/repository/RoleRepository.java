package com.cg.loginregister.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cg.loginregister.model.EnumRole;
import com.cg.loginregister.model.Role;



public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(EnumRole name);
}

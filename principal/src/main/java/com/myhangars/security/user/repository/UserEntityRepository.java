package com.myhangars.security.user.repository;

import com.myhangars.security.user.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserEntityRepository extends CrudRepository<UserEntity, Integer>{

    Optional<UserEntity> findByUsername(String usernname);
    Boolean existsByUsername(String name);
}

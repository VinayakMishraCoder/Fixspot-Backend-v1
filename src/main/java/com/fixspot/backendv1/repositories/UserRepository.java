package com.fixspot.backendv1.repositories;


import com.fixspot.backendv1.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

//    Optional<User> findByUsername(String username);
}


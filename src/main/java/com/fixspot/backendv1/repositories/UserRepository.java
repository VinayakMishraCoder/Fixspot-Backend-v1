package com.fixspot.backendv1.repositories;


import com.fixspot.backendv1.entities.IssueEntity;
import com.fixspot.backendv1.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUsername(String username);

    Optional<List<IssueEntity>> findByUpVoters(Integer id);
}


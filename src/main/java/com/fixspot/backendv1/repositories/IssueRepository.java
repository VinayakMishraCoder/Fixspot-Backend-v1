package com.fixspot.backendv1.repositories;


import com.fixspot.backendv1.entities.IssueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IssueRepository extends JpaRepository<IssueEntity, Integer> {
    @Query("SELECT i FROM IssueEntity i WHERE i.issuer.id = ?1")
    List<IssueEntity> findByIssuer(Integer issuer);
}


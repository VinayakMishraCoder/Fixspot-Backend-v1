package com.fixspot.backendv1.repositories;


import com.fixspot.backendv1.entities.IssueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<IssueEntity, Integer> {

}


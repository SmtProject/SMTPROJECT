package com.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.model.entity.ProjectEntity;

@Repository
public interface ProjectEntityRepository extends JpaRepository<ProjectEntity, Integer>,QueryDslPredicateExecutor<ProjectEntity>{
	
}

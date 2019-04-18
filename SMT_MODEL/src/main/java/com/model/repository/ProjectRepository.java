package com.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.model.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>,QueryDslPredicateExecutor<Project>{
	
}

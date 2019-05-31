package com.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.model.entity.EntityRelation;


@Repository
public interface EntityRelationRepository extends JpaRepository<EntityRelation, Integer>,QueryDslPredicateExecutor<EntityRelation>{
	
}


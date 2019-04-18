package com.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.model.entity.Attribute;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Integer>,QueryDslPredicateExecutor<Attribute>{
	
}

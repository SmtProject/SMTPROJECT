package com.smt.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.smt.data.entity.TeachingGrades;


@Repository
public interface TeachingGradesRepository extends JpaRepository<TeachingGrades, Integer>,QueryDslPredicateExecutor<TeachingGrades>{
	
}
package com.smt.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.smt.data.entity.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>,QueryDslPredicateExecutor<Student>{
	
}
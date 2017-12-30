package com.smt.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smt.data.entity.SmtUser;


@Repository
public interface SmtUserRepository extends JpaRepository<SmtUser, Integer> {
	

}
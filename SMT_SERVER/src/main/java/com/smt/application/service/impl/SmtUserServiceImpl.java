package com.smt.application.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smt.application.service.SmtUserService;
import com.smt.data.entity.SmtUser;
import com.smt.data.repository.SmtUserRepository;

/**
 * Service layer. Specify transactional behavior and mainly delegate calls to
 * Repository.
 */
@Service
public class SmtUserServiceImpl implements SmtUserService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SmtUserRepository smtUserRepository;

	@Transactional
	@Override
	public void add(SmtUser smtUser) {
		smtUserRepository.save(smtUser);
	}

	@Transactional(readOnly = true)
	@Override
	public List<SmtUser> findAll() {
		return smtUserRepository.findAll();
	}

	@Transactional
	@Override
	public void addAll(Collection<SmtUser> users) {
		for (SmtUser smtUser : users) {
			smtUserRepository.save(smtUser);
		}
	}
	
	@Transactional(readOnly = true)
	@Override
	public long getCount() {
		return smtUserRepository.count();
	}

}

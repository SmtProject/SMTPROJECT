package com.smt.application.service.impl;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.types.Predicate;
import com.smt.application.service.SmtUserService;
import com.smt.data.entity.QSmtUser;
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
	public void save(SmtUser smtUser) throws ValidationException{
		if(smtUser!=null) {
			if(smtUser.getFirstName()==null || smtUser.getFirstName().isEmpty()) {
				throw new ValidationException("Emty First Name");
			}
			if(smtUser.getMiddleName()==null || smtUser.getMiddleName().isEmpty()) {
				throw new ValidationException("Emty Middle Name");
			}
			if(smtUser.getLastName()==null || smtUser.getLastName().isEmpty()) {
				throw new ValidationException("Emty Last Name");
			}
			if(smtUser.getPassword()==null || smtUser.getPassword().isEmpty()) {
				throw new ValidationException("Emty password");
			}
			if(smtUser.getPassword()==null || smtUser.getPassword().isEmpty() || smtUser.getPassword().length()<6) {
				throw new ValidationException("Password Should be At least 6 characters");
			}
			_validateSmtUserUnidnes(smtUser);
			smtUserRepository.save(smtUser);
		}else {
			throw new ValidationException("Emty smtUser");
		}
	}
	
	private void _validateSmtUserUnidnes(SmtUser smtUserToSave) throws ValidationException{
		if(smtUserToSave!=null) {
			SmtUser serverSmtUser = smtUserRepository.findOne(QSmtUser.smtUser.userName.equalsIgnoreCase(smtUserToSave.getUserName()));
			if(serverSmtUser!=null && !serverSmtUser.getId().equals(smtUserToSave.getId())) {
				throw new ValidationException("UserName Already Exists");
			}
		}else {
			throw new ValidationException("Emty smtUser");
		}
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
	@Transactional(readOnly = true)
	@Override
	public SmtUser login(String username, String password){
		Predicate predicate = QSmtUser.smtUser.userName.eq(username).and(QSmtUser.smtUser.password.eq(password));
		SmtUser res = smtUserRepository.findOne(predicate);
		return res;
	}

}

package com.smt.application.service.impl;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.smt.application.service.SmtUserService;
import com.smt.data.entity.SmtUser;
import com.smt.data.entity.Admin;
import com.smt.data.entity.QAdmin;
import com.smt.data.repository.AdminRepository;

@Service
public class SmtUserServiceImpl implements SmtUserService {


	private static final long serialVersionUID = 1L;
	@Autowired
	private AdminRepository adminRepository;

	@Transactional
	@Override
	public void saveAdmin(Admin admin) throws ValidationException{
		if(admin!=null) {
			if(admin.getRole()==null) {
				throw new ValidationException("Emty Admin Role");
			}
			_smtUserValidation(admin);
			_validateAdminUnidnes(admin);
			adminRepository.save(admin);
		}else {
			throw new ValidationException("Emty Admin Info");
		}
	}
	
	private void _validateAdminUnidnes(Admin adminToSave) throws ValidationException{
		if(adminToSave!=null) {
			Admin serverSmtUser = adminRepository.findOne(QAdmin.admin.userName.equalsIgnoreCase(adminToSave.getUserName()));
			if(serverSmtUser!=null && !serverSmtUser.getId().equals(adminToSave.getId())) {
				throw new ValidationException("UserName Already Exists");
			}
		}else {
			throw new ValidationException("Emty Admin Info");
		}
	}
	private void _smtUserValidation(SmtUser user)throws ValidationException{
		if(user.getFirstName()==null || user.getFirstName().isEmpty()) {
			throw new ValidationException("Emty First Name");
		}
		if(user.getMiddleName()==null || user.getMiddleName().isEmpty()) {
			throw new ValidationException("Emty Middle Name");
		}
		if(user.getLastName()==null || user.getLastName().isEmpty()) {
			throw new ValidationException("Emty Last Name");
		}
		if(user.getPassword()==null || user.getPassword().isEmpty()) {
			throw new ValidationException("Emty password");
		}
		if(user.getPassword()==null || user.getPassword().isEmpty() || user.getPassword().length()<6) {
			throw new ValidationException("Password Should be At least 6 characters");
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<Admin> findAllAdmins() {
		return adminRepository.findAll();
	}

	@Transactional
	@Override
	public void addAllAdmins(Collection<Admin> users) {
		for (Admin admin : users) {
			adminRepository.save(admin);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public long getAdminsCount() {
		return adminRepository.count();
	}
	@Transactional(readOnly = true)
	@Override
	public SmtUser login(String username, String password){
		Admin result=adminRepository.findOne(QAdmin.admin.userName.eq(username).and(QAdmin.admin.password.eq(password)));
		return result;
	}

}

package com.smt.application.service.impl;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smt.application.service.SmtUserService;
import com.smt.data.entity.Admin;
import com.smt.data.entity.QAdmin;
import com.smt.data.entity.QTeacher;
import com.smt.data.entity.SmtUser;
import com.smt.data.entity.Teacher;
import com.smt.data.repository.AdminRepository;
import com.smt.data.repository.TeacherRepository;

@Service
public class SmtUserServiceImpl implements SmtUserService {


	private static final long serialVersionUID = 1L;
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private TeacherRepository teacherRepository;
	
	//--------------------------------------Login-------------------------------------------------------------------

	@Transactional(readOnly = true)
	@Override
	public SmtUser login(String username, String password){
		SmtUser result=adminRepository.findOne(QAdmin.admin.userName.eq(username).and(QAdmin.admin.password.eq(password)));
		if(result==null)
			result=teacherRepository.findOne(QTeacher.teacher.userName.eq(username).and(QTeacher.teacher.password.eq(password)));
		return result;
	}
	
	//--------------------------------------Admin-API----------------------------------------------------------------

	@Transactional
	@Override
	public Admin saveAdmin(Admin admin) throws ValidationException{
		if(admin!=null) {
			if(admin.getRole()==null) {
				throw new ValidationException("Empty Admin Role");
			}
			_smtUserValidation(admin);
			_validateAdminUnidnes(admin);
			return adminRepository.save(admin);
		}else {
			throw new ValidationException("Empty Admin Info");
		}
	}
	
	private void _validateAdminUnidnes(Admin adminToSave) throws ValidationException{
		if(adminToSave!=null) {
			Admin serverSmtUser = adminRepository.findOne(QAdmin.admin.userName.equalsIgnoreCase(adminToSave.getUserName()));
			if(serverSmtUser!=null && !serverSmtUser.getId().equals(adminToSave.getId())) {
				throw new ValidationException("Admin Already has Same User");
			}
		}else {
			throw new ValidationException("Empty Admin Info");
		}
	}
	private void _smtUserValidation(SmtUser user)throws ValidationException{
		if(user.getFirstName()==null || user.getFirstName().isEmpty()) {
			throw new ValidationException("Empty First Name");
		}
		if(user.getMiddleName()==null || user.getMiddleName().isEmpty()) {
			throw new ValidationException("Empty Middle Name");
		}
		if(user.getLastName()==null || user.getLastName().isEmpty()) {
			throw new ValidationException("Empty Last Name");
		}
		if(user.getPassword()==null || user.getPassword().isEmpty()) {
			throw new ValidationException("Empty password");
		}
		if(user.getStatus()==null ) {
			throw new ValidationException("Empty Status");
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
	public void saveAdmin(List<Admin> users) throws ValidationException {
		String buffer = "";
		int i = 0;
		for (Admin admin : users) {
			try {
				saveAdmin(admin);
			} catch (ValidationException e) {
				buffer += e.getMessage() +" for row " + i++ +"\n";
			}
		}
		if (buffer.length() > 0)
			throw new ValidationException(buffer);
	}

	@Transactional(readOnly = true)
	@Override
	public long getAdminsCount() {
		return adminRepository.count();
	}
	
//--------------------------------------Techers-API----------------------------------------------------------------

	@Transactional(readOnly = true)
	@Override
	public List<Teacher> findAllATeachers() {
		return teacherRepository.findAll();
	}

	@Override
	public Teacher saveTeacher(Teacher teacher) throws ValidationException {
		if(teacher!=null) {
			_smtUserValidation(teacher);
			_validateTeacherUnidnes(teacher);
			return teacherRepository.save(teacher);
		}else {
			throw new ValidationException("Emty Teacher Info");
		}

	}
	private void _validateTeacherUnidnes(Teacher teacherToSave) throws ValidationException{
		if(teacherToSave!=null) {
			Teacher serverSmtUser = teacherRepository.findOne(QTeacher.teacher.userName.equalsIgnoreCase(teacherToSave.getUserName()));
			if(serverSmtUser!=null && !serverSmtUser.getId().equals(teacherToSave.getId())) {
				throw new ValidationException("Teacher Already has Same User");
			}
		}else {
			throw new ValidationException("Emty Teacher Info");
		}
	}

}

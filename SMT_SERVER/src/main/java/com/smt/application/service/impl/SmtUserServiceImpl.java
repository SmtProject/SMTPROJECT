package com.smt.application.service.impl;

import java.util.List;

import javax.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.smt.application.service.SmtUserService;
import com.smt.data.entity.SmtUser;
import com.smt.data.entity.Teacher;
import com.smt.data.entity.Admin;
import com.smt.data.entity.QAdmin;
import com.smt.data.entity.QTeacher;
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
				throw new ValidationException("Emty Admin Role");
			}
			_smtUserValidation(admin);
			_validateAdminUnidnes(admin);
			return adminRepository.save(admin);
		}else {
			throw new ValidationException("Emty Admin Info");
		}
	}
	
	private void _validateAdminUnidnes(Admin adminToSave) throws ValidationException{
		if(adminToSave!=null) {
			Admin serverSmtUser = adminRepository.findOne(QAdmin.admin.userName.equalsIgnoreCase(adminToSave.getUserName()));
			if(serverSmtUser!=null && !serverSmtUser.getId().equals(adminToSave.getId())) {
				throw new ValidationException("Admin Already has Same User");
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
		if(user.getStatus()==null ) {
			throw new ValidationException("Emty Status");
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
	public void saveAdmin(List<Admin> users) throws ValidationException{
		String errorBuffer="";
		int line=0;
		for (Admin admin : users) {
			line++;
			if(admin.getUserName()!=null && _validateSmtUserNameExists(admin.getUserName())) {
				errorBuffer+="User Name "+admin.getUserName()+" Already Exists! at line number "+line+"\n";
				continue;	
			}
			try {
				saveAdmin(admin);
			} catch (ValidationException e) {
				errorBuffer+=e.getMessage()+" at line number "+line+"\n";
			}
		}
		if(errorBuffer.length()>0) {
			throw new ValidationException(errorBuffer);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public long getAdminsCount() {
		return adminRepository.count();
	}
	

	public Admin _getAdminByUserName(String userName){
		return adminRepository.findOne(QAdmin.admin.userName.eq(userName));
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

	public Teacher _getTeaherByUserName(String userName){
		return teacherRepository.findOne(QTeacher.teacher.userName.eq(userName));
	}
	
	private boolean _validateSmtUserNameExists(String userName) {
		if(_getAdminByUserName(userName)!=null)
			return true;
		if(_getTeaherByUserName(userName)!=null)
			return true;
		return false;

	}

}

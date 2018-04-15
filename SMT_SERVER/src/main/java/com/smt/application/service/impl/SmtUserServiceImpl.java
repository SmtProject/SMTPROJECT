package com.smt.application.service.impl;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.mysema.query.types.expr.BooleanExpression;
import com.smt.application.service.SmtUserService;
import com.smt.data.entity.Admin;
import com.smt.data.entity.Payment;
import com.smt.data.entity.QAdmin;
import com.smt.data.entity.QStudent;
import com.smt.data.entity.QTeacher;
import com.smt.data.entity.QTeachingGrades;
import com.smt.data.entity.SmtUser;
import com.smt.data.entity.SmtUser.SmtUserStatus;
import com.smt.data.entity.Student;
import com.smt.data.entity.Teacher;
import com.smt.data.entity.TeachingGrades;
import com.smt.data.repository.AdminRepository;
import com.smt.data.repository.PaymentRepository;
import com.smt.data.repository.StudentRepository;
import com.smt.data.repository.TeacherRepository;
import com.smt.data.repository.TeachingGradesRepository;

@Service
public class SmtUserServiceImpl implements SmtUserService {


	private static final long serialVersionUID = 1L;
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private TeachingGradesRepository teachingGradesRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	
	//--------------------------------------Login-------------------------------------------------------------------

	@Transactional(readOnly = true)
	@Override
	public SmtUser login(String username, String password){
		SmtUser result=adminRepository.findOne(QAdmin.admin.userName.eq(username).and(QAdmin.admin.password.eq(password)).and(QAdmin.admin.status.eq(SmtUserStatus.ACTIVE)));
		if(result==null)
			result=teacherRepository.findOne(QTeacher.teacher.userName.eq(username).and(QTeacher.teacher.password.eq(password)).and(QTeacher.teacher.status.eq(SmtUserStatus.ACTIVE)));
		if(result==null)
			result=studentRepository.findOne(QStudent.student.userName.eq(username).and(QStudent.student.password.eq(password)).and(QStudent.student.status.eq(SmtUserStatus.ACTIVE)));
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
			_validateSmtUserUniqueness(admin);
			return adminRepository.save(admin);
		}else {
			throw new ValidationException("Empty Admin Info");
		}
	}
	
	private void _validateAdminUniqueness(SmtUser adminToSave) throws ValidationException{
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
	public List<Teacher> findAllTeachers() {
		return teacherRepository.findAll();
	}

	@Override
	public Teacher saveTeacher(Teacher teacher) throws ValidationException {
		if(teacher!=null) {
			_smtUserValidation(teacher);
			_validateSmtUserUniqueness(teacher);
			return teacherRepository.save(teacher);
		}else {
			throw new ValidationException("Empty Teacher Info");
		}

	}
	@Transactional
	@Override
	public void saveTeachers(List<Teacher> teachers) throws ValidationException{
		String errorBuffer="";
		int line=0;
		for (Teacher teacher : teachers) {
			line++;
			if(teacher.getUserName()!=null && _validateSmtUserNameExists(teacher.getUserName())) {
				errorBuffer+="User Name "+teacher.getUserName()+" Already Exists! at line number "+line+"\n";
				continue;	
			}
			try {
				saveTeacher(teacher);
			} catch (ValidationException e) {
				errorBuffer+=e.getMessage()+" at line number "+line+"\n";
			}
		}
		if(errorBuffer.length()>0) {
			throw new ValidationException(errorBuffer);
		}
	}
	private void _validateTeacherUniqueness(SmtUser teacherToSave) throws ValidationException{
		if(teacherToSave!=null) {
			Teacher serverSmtUser = teacherRepository.findOne(QTeacher.teacher.userName.equalsIgnoreCase(teacherToSave.getUserName()));
			if(serverSmtUser!=null && !serverSmtUser.getId().equals(teacherToSave.getId())) {
				throw new ValidationException("Teacher Already has Same User");
			}
		}else {
			throw new ValidationException("Empty Teacher Info");
		}
	}

	public Teacher _getTeaherByUserName(String userName){
		return teacherRepository.findOne(QTeacher.teacher.userName.eq(userName));
	}
	
	//--------------------------------------Students Api-------------------------------------------------------------------
	
	@Override
	public List<Student> findAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student saveStudent(Student student) throws ValidationException {
		if(student!=null) {
			_smtUserValidation(student);
			_validateSmtUserUniqueness(student);
			return studentRepository.save(student);
		}else {
			throw new ValidationException("Empty Student Info");
		}
	}

	@Override
	public void saveStudents(List<Student> students) throws ValidationException {
		String errorBuffer="";
		int line=0;
		for (Student student : students) {
			line++;
			if(student.getUserName()!=null && _validateSmtUserNameExists(student.getUserName())) {
				errorBuffer+="User Name "+student.getUserName()+" Already Exists! at line number "+line+"\n";
				continue;	
			}
			try {
				saveStudent(student);
			} catch (ValidationException e) {
				errorBuffer+=e.getMessage()+" at line number "+line+"\n";
			}
		}
		if(errorBuffer.length()>0) {
			throw new ValidationException(errorBuffer);
		}
		
	}
	private Student _getStudentByUserName(String userName){
		return studentRepository.findOne(QStudent.student.userName.eq(userName));
	}
	private void _validateStudentsUniqueness(SmtUser studentToSave) throws ValidationException{
		if(studentToSave!=null) {
			Student serverSmtUser = studentRepository.findOne(QStudent.student.userName.equalsIgnoreCase(studentToSave.getUserName()));
			if(serverSmtUser!=null && !serverSmtUser.getId().equals(studentToSave.getId())) {
				throw new ValidationException("Student Already has Same User");
			}
		}else {
			throw new ValidationException("Empty Student Info");
		}
	}

	@Override
	public List<Payment> findAllStudentsPayment() {
		return paymentRepository.findAll();
	}

	@Override
	public Payment saveStudentPayment(Payment payment) throws ValidationException {
		return paymentRepository.save(payment);
	}

	@Override
	public void saveStudentsPayment(List<Payment> studentsPayment) throws ValidationException {
		paymentRepository.save(studentsPayment);

	}
	
	//--------------------------------------smtUser Api-------------------------------------------------------------------

	private boolean _validateSmtUserNameExists(String userName) {
		if(_getAdminByUserName(userName)!=null)
			return true;
		if(_getTeaherByUserName(userName)!=null)
			return true;
		if(_getStudentByUserName(userName)!=null)
			return true;
		return false;

	}
	
	private void _validateSmtUserUniqueness(SmtUser smtUser) throws ValidationException{
		_validateAdminUniqueness(smtUser);
		_validateTeacherUniqueness(smtUser);
		_validateStudentsUniqueness(smtUser);
	}

	@Override
	public void saveTeachingGrades(Integer teacherId,List<TeachingGrades>gradesToSave)throws ValidationException {
		gradesToSave.forEach(e -> teachingGradesRepository.save(e));
	}

	@Override
	public List<TeachingGrades> findTeachingGradesByTeacher(Integer teacherId) {
		BooleanExpression be = QTeachingGrades.teachingGrades.teacherId.eq(teacherId);
		Iterable<TeachingGrades> findAll = teachingGradesRepository.findAll(be);
		return Lists.newArrayList(findAll);
		
	}

	@Override
	public void saveTeachingGrades(Integer teacherId, TeachingGrades grade) throws ValidationException {
		teachingGradesRepository.save(grade);
	}

	@Override
	public Payment findPaymentById(Integer id) {
		if(id == null)
			return null;
		return paymentRepository.findOne(id);
	}
	
}

package com.smt.application.service;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.ValidationException;

import com.smt.data.entity.Admin;
import com.smt.data.entity.Payment;
import com.smt.data.entity.SmtUser;
import com.smt.data.entity.Student;
import com.smt.data.entity.Teacher;
import com.smt.data.entity.TeachingGrades;

public interface SmtUserService extends Serializable{
	//--------------------------------------Login----------------------------------------------------------------
	
	SmtUser login(String username,String password);
	
	//--------------------------------------Admin-API----------------------------------------------------------------
	
	long getAdminsCount();

	void saveAdmin(List<Admin> users) throws ValidationException;

	List<Admin> findAllAdmins();

	Admin saveAdmin(Admin admin) throws ValidationException;
	
	//--------------------------------------Techers-API----------------------------------------------------------------
	
	List<Teacher> findAllTeachers();
	
	Teacher saveTeacher(Teacher teacher) throws ValidationException;
	
	void saveTeachers(List<Teacher> teachers) throws ValidationException;
	
	void saveTeachingGrades(Integer teacherId, List<TeachingGrades> gradesToSaveList)throws ValidationException;

	void saveTeachingGrades(Integer teacherId, TeachingGrades grade)throws ValidationException;

	List<TeachingGrades> findTeachingGradesByTeacher(Integer teacherId);
	
	//--------------------------------------Students-API----------------------------------------------------------------
	List<Student> findAllStudents();
	
	Student saveStudent(Student student) throws ValidationException;
	
	void saveStudents(List<Student> students) throws ValidationException;

	List<Payment> findAllStudentsPayment();
	
	Payment saveStudentPayment(Payment payment) throws ValidationException;
	
	void saveStudentsPayment(List<Payment> studentsPayment) throws ValidationException;
}

package com.smt.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import smt.model.tools.ClassesEnum;
import smt.model.tools.GradesEnum;

@Entity
@Table(name = "TEACHING_GRADES")
public class TeachingGrades implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer teacherId;
	private GradesEnum grade;
	private ClassesEnum teachingClass;
	
	public TeachingGrades() {
	}

	public TeachingGrades(Integer teacherId, GradesEnum grade, ClassesEnum teachingClass) {
		this.teacherId = teacherId;
		this.grade = grade;
		this.teachingClass = teachingClass;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "TEACHER_ID")
	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	
	@Column(name = "GRADE")
	@Enumerated(EnumType.STRING) 
	public GradesEnum getGrade() {
		return grade;
	}

	public void setGrade(GradesEnum grade) {
		this.grade = grade;
	}

	@Column(name = "TEACHING_CLASS")
	@Enumerated(EnumType.STRING) 
	public ClassesEnum getTeachingClass() {
		return teachingClass;
	}
	public void setTeachingClass(ClassesEnum teachingClass) {
		this.teachingClass = teachingClass;
	}

}

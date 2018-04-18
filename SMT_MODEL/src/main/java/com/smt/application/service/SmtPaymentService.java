package com.smt.application.service;

import java.util.List;

import javax.xml.bind.ValidationException;

import com.smt.data.entity.Payment;

public interface SmtPaymentService {

	List<Payment> findAllStudentsPayment();

	Payment saveStudentPayment(Payment payment) throws ValidationException;

	void saveStudentsPayment(List<Payment> studentsPayment) throws ValidationException;

	public Payment findPaymentById(Integer id);
}

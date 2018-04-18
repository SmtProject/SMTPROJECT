package com.smt.application.service.impl;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;

import com.smt.application.service.SmtPaymentService;
import com.smt.data.entity.Payment;
import com.smt.data.repository.PaymentRepository;

public class SmtPaymentServiceImpl implements SmtPaymentService{
	@Autowired
	private PaymentRepository paymentRepository;
	
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
	@Override
	public Payment findPaymentById(Integer id) {
		if(id == null)
			return null;
		return paymentRepository.findOne(id);
	}
}

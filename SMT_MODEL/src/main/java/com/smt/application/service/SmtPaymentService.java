package com.smt.application.service;

import java.util.List;

import javax.xml.bind.ValidationException;

import com.smt.data.entity.Payment;
import com.smt.data.entity.PaymentDetail;

public interface SmtPaymentService {

	List<Payment> findAllStudentsPayment()throws ValidationException;

	Payment saveStudentPayment(Payment payment) throws ValidationException;

	void saveStudentsPayment(List<Payment> studentsPayment) throws ValidationException;

	public Payment findPaymentById(Integer id)throws ValidationException;
	
	public PaymentDetail findPaymentDetailsById(Integer id) throws ValidationException;
	
	public void transfereAmmount(Integer paymentDetailsFromId,Integer paymentDetailsToId,Integer amount) throws ValidationException;
	
	public PaymentDetail updatePaymentDetail(PaymentDetail paymentDetail)throws ValidationException;
}

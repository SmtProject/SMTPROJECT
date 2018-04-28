package com.smt.application.service;

import java.util.List;

import javax.xml.bind.ValidationException;

import com.smt.data.entity.Payment;
import com.smt.data.entity.PaymentDetail;

public interface SmtPaymentService {

	List<Payment> findAllStudentsPayment();
	
	public List<PaymentDetail>getPaymentDeatails(Integer paymentId)throws ValidationException;

	Payment saveStudentPayment(Payment payment) throws ValidationException;

	void saveStudentsPayment(List<Payment> studentsPayment) throws ValidationException;

	public Payment findPaymentById(Integer id);
	
	public PaymentDetail findPaymentDetailsById(Integer id) throws ValidationException;
	
	public PaymentDetail updatePaymentDetail(PaymentDetail paymentDetail)throws ValidationException;
}

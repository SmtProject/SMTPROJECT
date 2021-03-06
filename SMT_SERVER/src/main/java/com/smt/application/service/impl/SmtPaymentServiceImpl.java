package com.smt.application.service.impl;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;

import com.smt.application.service.SmtPaymentService;
import com.smt.data.entity.Payment;
import com.smt.data.entity.PaymentDetail;
import com.smt.data.repository.PaymentDetailRepository;
import com.smt.data.repository.PaymentRepository;

public class SmtPaymentServiceImpl implements SmtPaymentService{
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private PaymentDetailRepository paymentDetailRepository;
	
	@Override
	public List<Payment> findAllStudentsPayment() {
		return paymentRepository.findAll();
	}

	@Override
	public Payment saveStudentPayment(Payment payment) throws ValidationException {
		if(payment==null) 
			throw new ValidationException("empty payment");
		if(payment.getSplitNumber()==null)
			throw new ValidationException("empty split");
		boolean isInsert=payment.getId()==null;
		Payment savedPayment= paymentRepository.save(payment);
		if(isInsert)
		for(int i=1;i<=payment.getSplitNumber();i++) {
			int amount=savedPayment.getPaymentAmount()/savedPayment.getSplitNumber();
			paymentDetailRepository.save(new PaymentDetail(savedPayment.getId(), i, amount, false));
		}
		return savedPayment;
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

	@Override
	public PaymentDetail updatePaymentDetail(PaymentDetail paymentDetail) throws ValidationException {
		if(paymentDetail==null) 
			throw new ValidationException("empty payment Detail");
		return paymentDetailRepository.save(paymentDetail);
	}

	@Override
	public PaymentDetail findPaymentDetailsById(Integer id) throws ValidationException {
		if(id==null) 
			throw new ValidationException("empty payment Detail by id");
		return paymentDetailRepository.findOne(id);
	}

	@Override
	public void transfereAmmount(Integer paymentDetailsFromId, Integer paymentDetailsToId, Integer amount)throws ValidationException {
		if(paymentDetailsFromId==null) 
			throw new ValidationException("empty paymentDetailsFromId");
		if(paymentDetailsToId==null) 
			throw new ValidationException("empty paymentDetailsToId");
		if(amount==null) 
			throw new ValidationException("empty amount");
		PaymentDetail paymentDetailfrom= findPaymentDetailsById(paymentDetailsFromId);
		if(amount>paymentDetailfrom.getAmount())
			throw new ValidationException("paymentDetailfrom has not enough amount");
		PaymentDetail paymentDetailTo=findPaymentDetailsById(paymentDetailsToId);
		paymentDetailfrom.setAmount(paymentDetailfrom.getAmount()-amount);
		paymentDetailTo.setAmount(paymentDetailTo.getAmount()+amount);
		paymentDetailRepository.save(paymentDetailfrom);
		paymentDetailRepository.save(paymentDetailTo);
	}
}

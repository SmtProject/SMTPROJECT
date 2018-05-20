package com.smt.servlet.client.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;

import com.smt.application.service.SmtPaymentService;
import com.smt.data.entity.Payment;
import com.smt.data.entity.PaymentDetail;
import com.smt.servlet.client.service.SmtServiceProvider;

@WebServlet("/pay")
public class PayActionController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id =Integer.parseInt(req.getParameter("paymentDetail"));
		SmtPaymentService smtUserService = SmtServiceProvider.getInstance().getSmtPaymentService();
		PaymentDetail paymentDetails;
		try {
			paymentDetails = SmtServiceProvider.getInstance().getSmtPaymentService().findPaymentDetailsById(id);
		} catch (ValidationException e1) {
			return;
		}
		paymentDetails.setPayed(true);
		Payment payment;
		try {
			payment = SmtServiceProvider.getInstance().getSmtPaymentService().findPaymentById(paymentDetails.getPaymentId());
		} catch (ValidationException e1) {
			return;
		}
		int paymentAmount=0;
		if(payment.getPayedAmount()!=null) {
			paymentAmount+=payment.getPayedAmount();
		}
		paymentAmount+=paymentDetails.getAmount();
		
		payment.setPayedAmount(paymentAmount);
		try {
			smtUserService.saveStudentPayment(payment);
			smtUserService.updatePaymentDetail(paymentDetails);
		} catch (ValidationException e) {
			e.printStackTrace();
		}
			req.getRequestDispatcher("/view/MainPage.jsp").forward(req, resp);
	}
}

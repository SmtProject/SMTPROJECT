package com.smt.servlet.client.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;

import com.smt.application.service.SmtUserService;
import com.smt.data.entity.Payment;
import com.smt.servlet.client.service.SmtServiceProvider;

@WebServlet("/pay")
public class PayActionController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("payment");
		SmtUserService smtUserService = SmtServiceProvider.getInstance().getSmtUserService();
		Payment payment = smtUserService.findPaymentById(Integer.parseInt(id));
		
		int amount = payment.getPaymentAmount()/5;
		Integer payedAmount = payment.getPayedAmount() == null ? 0 : payment.getPayedAmount();
		payment.setPayedAmount(payedAmount+amount);
		try {
			smtUserService.saveStudentPayment(payment);
		} catch (ValidationException e) {
			e.printStackTrace();
		}
		req.setAttribute("payment", req.getParameter("payment"));
		req.getRequestDispatcher("/view/StudentPaymentDetailsGrid.jsp").forward(req, resp);
	}
}

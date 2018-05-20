package com.smt.servlet.client.controller;

import java.io.IOException;

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

@WebServlet("/transfere")
public class TransfereActionController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer paymentDetailfrom =Integer.parseInt(req.getParameter("paymentDetailfrom"));
		Integer paymentDetailto =Integer.parseInt(req.getParameter("paymentDetailto"));
		Integer amount =Integer.parseInt(req.getParameter("amount"));
		
		SmtPaymentService smtUserService = SmtServiceProvider.getInstance().getSmtPaymentService();
		PaymentDetail paymentDetails;
		try {
			 SmtServiceProvider.getInstance().getSmtPaymentService().transfereAmmount(paymentDetailfrom,paymentDetailto,amount);
		} catch (ValidationException e1) {
			return;
		}
		
			req.getRequestDispatcher("/view/MainPage.jsp").forward(req, resp);
	}
}

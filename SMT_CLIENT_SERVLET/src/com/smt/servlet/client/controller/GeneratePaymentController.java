package com.smt.servlet.client.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;

import com.smt.application.service.SmtPaymentService;
import com.smt.data.entity.Payment;
import com.smt.data.entity.Student;
import com.smt.servlet.client.service.SmtServiceProvider;

@WebServlet("/generatePayment")
public class GeneratePaymentController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("student");
		SmtPaymentService smtUserService = SmtServiceProvider.getInstance().getSmtPaymentService();
		Student student = SmtServiceProvider.getInstance().getSmtUserService().findStudentById(Integer.parseInt(id));
		Payment payment = new Payment(student, "invoice 2017-2018", 2000, 0, 5, 0, new Date(), new Date(), "", "");
		try {
			smtUserService.saveStudentPayment(payment);
		} catch (ValidationException e1) {
			e1.printStackTrace();
		}
		req.getRequestDispatcher("/view/StudentPaymentGrid.jsp").forward(req, resp);
	}
}

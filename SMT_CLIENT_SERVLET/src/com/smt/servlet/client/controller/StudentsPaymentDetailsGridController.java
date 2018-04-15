package com.smt.servlet.client.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/financeDetails")
public class StudentsPaymentDetailsGridController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("payment", req.getParameter("payment"));
		req.getRequestDispatcher("/view/StudentPaymentDetailsGrid.jsp").forward(req, resp);
	}
}

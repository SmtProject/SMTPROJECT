package com.smt.servlet.client.taghandler;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.smt.data.entity.Payment;
import com.smt.servlet.client.service.SmtServiceProvider;


public class StudentPaymentGridTagHandler extends SimpleTagSupport {


	@Override
	public void doTag() throws JspException, IOException {

		JspWriter out = getJspContext().getOut();
		out.print("<div class=\"container\">");
		out.print("<table class=\"table table-striped\">");
		out.print("<thead>");
		out.print("<tr>");
		out.print("<th>Name</th>");
		out.print("<th>Student Name</th>");
		out.print("<th>Payment Amount</th>");
		out.print("<th>Payed Amount</th>");
		out.print("<th>Payement Discount</th>");
		out.print("<th>Split Number</th>");
		out.print("<th>Manage</th>");

		out.print("</tr>");

		out.print("<tbody>");

		List<Payment> studentsPayment = SmtServiceProvider.getInstance().getSmtPaymentService().findAllStudentsPayment();
		for (Payment payment : studentsPayment) {
			out.print("<tr>");
			out.print("<td>" + payment.getName() + "</td>");
			out.print("<td>" + payment.getStudent().getFirstName() + "</td>");
			out.print("<td>" + payment.getPaymentAmount() + "</td>");
			Integer payedAmount = payment.getPayedAmount() == null ? 0 : payment.getPayedAmount();
			Integer payementDiscount = payment.getPaymentDiscount() == null ? 0 : payment.getPaymentDiscount();
			out.print("<td>" + payedAmount + "</td>");
			out.print("<td>" + payementDiscount + "</td>");
			out.print("<td>" + payment.getSplitNumber() + "</td>");
			out.print("<form method='GET' action='financeDetails' >");
			out.print(" <input type='hidden' value='"+payment.getId()+"' name='payment'>");
			
			out.print("<td>"); 
			out.print("<input type='submit' value='View' class=\"btn btn-danger\">");
			out.print("</td>");

			out.print("</form>");


			out.print("</tr>");

		}

		out.print("</tbody>");

		out.print("</table>");

		out.print("</div>");

	}


}

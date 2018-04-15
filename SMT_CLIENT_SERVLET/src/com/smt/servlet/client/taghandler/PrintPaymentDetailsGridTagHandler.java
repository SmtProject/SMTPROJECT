package com.smt.servlet.client.taghandler;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.smt.data.entity.Payment;


public class PrintPaymentDetailsGridTagHandler extends SimpleTagSupport {

	private Payment payment;
	
	@Override
	public void doTag() throws JspException, IOException {
		if(payment == null)
			return;
		JspWriter out = getJspContext().getOut();
		out.print("<div class=\"container\">");
		out.print("<table class=\"table table-striped\">");
		out.print("<thead>");
		out.print("<tr>");
		out.print("<th>Name</th>");
		out.print("<th>Student Name</th>");
		out.print("<th>Payment Amount</th>");
		out.print("<th>Status</th>");
		out.print("<th>Manage</th>");
		
		out.print("</tr>");

		out.print("<tbody>");
		Integer splitNumber = payment.getSplitNumber();
		int payed=0;
		for (int i = 0 ;i<splitNumber;i++) {
			out.print("<tr>");
			out.print("<td>" + payment.getName() + "</td>");
			out.print("<td>" + payment.getStudent().getFirstName() + "</td>");
			int amount = payment.getPaymentAmount()/splitNumber;
			out.print("<td>" + amount + "</td>");
			payed += amount;
			Integer payedAmount = payment.getPayedAmount()==null ?0 : payment.getPayedAmount();
			if(payedAmount<=payed){
				out.print("<td>" + "unpayed" + "</td>");
			}else
			out.print("<td>" + "payed" + "</td>");

			String paymentId = " "+payment.getId()+"  "; 
			if(payedAmount<=payed)
			out.print("<td> <button data-type="+paymentId + "id =\"print-payment\" type=\"button\" class=\"btn btn-success\"  >print</button> "
					+ "<button data-type="+paymentId + "id =\"pay-payment\" type=\"button\" class=\"btn btn-danger\" >pay</button></td>");
			else
				out.print("<td> <button data-type="+paymentId + "id =\"print-payment\" type=\"button\" class=\"btn btn-success\" disabled>print</button> "
						+ "<button data-type="+paymentId + "id =\"pay-payment\" type=\"button\" class=\"btn btn-danger\" disabled>pay</button></td>");
				
			out.print("</tr>");

		}

		out.print("</tbody>");

		out.print("</table>");

		out.print("</div>");

	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}

}

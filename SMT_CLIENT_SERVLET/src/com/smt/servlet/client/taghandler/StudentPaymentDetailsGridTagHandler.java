package com.smt.servlet.client.taghandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.xml.bind.ValidationException;

import com.smt.data.entity.Payment;
import com.smt.data.entity.PaymentDetail;
import com.smt.servlet.client.service.SmtServiceProvider;


public class StudentPaymentDetailsGridTagHandler extends SimpleTagSupport {

	private Payment payment;
	
	@Override
	public void doTag() throws JspException, IOException {
		if(payment == null)
			return;
		List<PaymentDetail>paymentDetails=null;
		try {
			paymentDetails=SmtServiceProvider.getInstance().getSmtPaymentService().getPaymentDeatails(payment.getId());
		} catch (ValidationException e) {
			paymentDetails=new ArrayList<PaymentDetail>();
		}
		JspWriter out = getJspContext().getOut();
		out.print("<div class=\"container\">");
		out.print("<table class=\"table table-striped\">");
		out.print("<thead>");
		out.print("<tr>");
		out.print("<th>Name</th>");
		out.print("<th>Student Name</th>");
		out.print("<th>Number</th>");
		out.print("<th>Payment Amount</th>");
		out.print("<th>Status</th>");
		out.print("<th>Manage</th>");

		out.print("</tr>");

		out.print("<tbody>");

		for (PaymentDetail paymentDetail : paymentDetails) {
			out.print("<tr>");
			out.print("<td>" + payment.getName() + "</td>");
			out.print("<td>" + payment.getStudent().getFirstName() + "</td>");
			out.print("<td>" +paymentDetail.getNumber() + "</td>");
			out.print("<td>" + paymentDetail.getAmount() + "</td>");
		
			
			if(!paymentDetail.getPayed()){
				out.print("<td>" + "unpayed" + "</td>");
			}else
				out.print("<td>" + "payed" + "</td>");

			String paymentId = " "+payment.getId()+"  "; 
			if(!paymentDetail.getPayed()) {
				out.print("<form method='GET' action='pay'>");
				out.print("<input type='hidden' value='"+paymentDetail.getId()+"' name='paymentDetail'>");
				out.print("<td>"); 
				out.print("<button data-type="+paymentId + "id =\"print-payment\" type=\"button\" class=\"btn btn-success\" >print</button> ");
				out.print("<input type='submit' value='Pay' class=\"btn btn-danger\">");
				out.print("</td>");
				out.print("</form>");
			}
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

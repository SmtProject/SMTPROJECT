package com.smt.servlet.client.taghandler;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.smt.data.entity.Payment;
import com.smt.servlet.client.service.SmtServiceProvider;


public class PrintStudentPaymentGridTagHandler extends SimpleTagSupport {


	@Override
	public void doTag() throws JspException, IOException {

		JspWriter out = getJspContext().getOut();
		out.print("<div class=\"container\">");
		out.print("<table class=\"table table-striped\">");
		out.print("<thead>");
		out.print("<tr>");
		out.print("<th>Name</th>");
		out.print("</tr>");

		out.print("<tbody>");

		for (Payment student : SmtServiceProvider.getInstance().getSmtUserService().findAllStudentsPayment()) {
			out.print("<tr>");
			out.print("<td>" + student.getName() + "</td>");
			out.print("</tr>");

		}

		out.print("</tbody>");

		out.print("</table>");

		out.print("</div>");

	}


}

package com.smt.servlet.client.taghandler;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.smt.data.entity.Student;
import com.smt.servlet.client.service.SmtServiceProvider;


public class MainPageTagHandler extends SimpleTagSupport {


	@Override
	public void doTag() throws JspException, IOException {

		JspWriter out = getJspContext().getOut();
		out.print("<div class=\"container\">");
		out.print("<table class=\"table table-striped\">");
		out.print("<thead>");
		out.print("<tr>");
		out.print("<th>Student Name</th>");
		out.print("<th>username</th>");
		out.print("<th>amount</th>");
		out.print("<th>nb OF Bills</th>");
		out.print("<th>generate</th>");
		
		out.print("</tr>");

		out.print("<tbody>");

		List<Student> students = SmtServiceProvider.getInstance().getSmtUserService().findAllStudents();
		for (Student student : students) {
		//	generatePayment	
			String id = student.getId()+""; 
			out.print("<form method='GET' action='generatePayment'>");
			out.print("<tr>");
			out.print("<td>" + student.getFormatedName() + "</td>");
			out.print("<td>" + student.getUserName() + "</td>");
			
			out.print("<td>");
			out.print("<input type='number' min='0' step='1' name='amount' required>");
			out.print("</td>");
			
			out.print("<td>");
			out.print("<input type='number' min='0' step='1' name='nbOFBills' required>");
			out.print("</td>");

			out.print("<input type=\"hidden\" name=\"student\" value=\""+id+"\" />");

			out.print("<td>");
			out.print("<input type='submit' value='generate' class=\"btn btn-danger\">");
			out.print("</td>");
			
			out.print("</tr>");
			out.print("</form>");
		}

		out.print("</tbody>");

		out.print("</table>");

		out.print("</div>");

	}


}

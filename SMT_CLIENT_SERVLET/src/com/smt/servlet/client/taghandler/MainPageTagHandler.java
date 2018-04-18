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
		
		out.print("</tr>");

		out.print("<tbody>");

		List<Student> students = SmtServiceProvider.getInstance().getSmtUserService().findAllStudents();
		for (Student student : students) {
			out.print("<tr>");
			out.print("<td>" + student.getFormatedName() + "</td>");
			out.print("<td>" + student.getUserName() + "</td>");

			String id = " "+student.getId()+"  "; 
			out.print("<td> <button data-type="+id + "id =\"generate-payment\" type=\"button\" class=\"btn btn-success\" >generate payment</button> ");
			
			out.print("</tr>");
		}

		out.print("</tbody>");

		out.print("</table>");

		out.print("</div>");

	}


}

package com.smt.servlet.client.taghandler;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.example.model.Student;

public class PrintStudentPaymentGridTagHandler extends SimpleTagSupport {

	private ArrayList<Student> studentList;

	@Override
	public void doTag() throws JspException, IOException {

		JspWriter out = getJspContext().getOut();
		out.print("<div class=\"container\">");
		out.print("<table class=\"table table-striped\">");
		out.print("<thead>");
		out.print("<tr>");
		out.print("<th>Firstname</th>");
		out.print("<th>LastName</th>");
		out.print("</tr>");

		out.print("<tbody>");

		for (Student student : studentList) {
			out.print("<tr>");
			out.print("<td>" + student.getFirstName() + "</td>");
			out.print("<td>" + student.getLastName() + "</td>");
			out.print("</tr>");

		}

		out.print("</tbody>");

		out.print("</table>");

		out.print("</div>");

	}

	public ArrayList<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(ArrayList<Student> studentList) {
		this.studentList = studentList;
	}

}

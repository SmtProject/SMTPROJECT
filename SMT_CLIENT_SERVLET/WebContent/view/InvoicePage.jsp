<%@page import="com.smt.data.entity.PaymentDetail"%>
<%@page import="com.smt.servlet.client.service.SmtServiceProvider"%>
<%@page import="com.smt.data.entity.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Invoice</title>
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<link href="assets/css/custom.css" rel="stylesheet" />
<style>
.invoice-box {
	max-width: 800px;
	margin: auto;
	padding: 30px;
	border: 1px solid #eee;
	box-shadow: 0 0 10px rgba(0, 0, 0, .15);
	font-size: 16px;
	line-height: 24px;
	font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;
	color: #555;
}

.invoice-box table {
	width: 100%;
	line-height: inherit;
	text-align: left;
}

.invoice-box table td {
	padding: 5px;
	vertical-align: top;
}

.invoice-box table tr td:nth-child(2) {
	text-align: right;
}

.invoice-box table tr.top table td {
	padding-bottom: 20px;
}

.invoice-box table tr.top table td.title {
	font-size: 45px;
	line-height: 45px;
	color: #333;
}

.invoice-box table tr.information table td {
	padding-bottom: 40px;
}

.invoice-box table tr.heading td {
	background: #eee;
	border-bottom: 1px solid #ddd;
	font-weight: bold;
}

.invoice-box table tr.details td {
	padding-bottom: 20px;
}

.invoice-box table tr.item td {
	border-bottom: 1px solid #eee;
}

.invoice-box table tr.item.last td {
	border-bottom: none;
}

.invoice-box table tr.total td:nth-child(2) {
	border-top: 2px solid #eee;
	font-weight: bold;
}

@media only screen and (max-width: 600px) {
	.invoice-box table tr.top table td {
		width: 100%;
		display: block;
		text-align: center;
	}
	.invoice-box table tr.information table td {
		width: 100%;
		display: block;
		text-align: center;
	}
}

/** RTL **/
.rtl {
	direction: rtl;
	font-family: Tahoma, 'Helvetica Neue', 'Helvetica', Helvetica, Arial,
		sans-serif;
}

.rtl table {
	text-align: right;
}

.rtl table tr td:nth-child(2) {
	text-align: left;
}
</style>
</head>

<body>
	<%
		String idAtrr = request.getAttribute("payment") == null ? "" : request.getAttribute("payment").toString();
		Integer id = idAtrr.isEmpty() ? null : Integer.parseInt(idAtrr.toString());
		PaymentDetail paymentDetailed = SmtServiceProvider.getInstance().getSmtPaymentService().findPaymentDetailsById(id);;
		Payment payment = SmtServiceProvider.getInstance().getSmtPaymentService().findPaymentById(paymentDetailed.getPaymentId());;
	%>
	<div class="invoice-box">
		<table cellpadding="0" cellspacing="0">
			<tr class="top">
				<td colspan="2">
					<table>
						<tr>
							<td class="title"></td>

							<td>
								<%
									out.print(payment.getName());
								%><br> <%
 	out.print(payment.getStartDate());
 %><br>
								Due: <%
 	out.print(payment.getEndDate());
 %>
							</td>
						</tr>
					</table>
				</td>
			</tr>

			<tr class="information">
				<td colspan="2">
					<table>
						<tr>
							<td><br> <br></td>

							<td>
								<%
									out.print(payment.getStudent().getFormatedName());
								%><br> <%
 	out.print(payment.getStudent().getEmail());
 %><br>

							</td>
						</tr>
					</table>
				</td>
			</tr>

			<tr class="heading">
				<td>Payment Method</td>

				<td>Check #</td>
			</tr>


			<tr class="heading">
				<td>Item</td>

				<td>Price</td>
			</tr>

			<tr class="total">
				<td></td>

				<td>Total: $<%
					out.print(paymentDetailed.getAmount());
				%>
				</td>
			</tr>
		</table>
		<input type="button" class="btn btn-success" value="Print Invoice"
			onClick="window.print()">
	</div>

</body>
</html>

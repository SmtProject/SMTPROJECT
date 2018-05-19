
<%@page import="com.smt.data.entity.Payment"%>
<%@page import="com.smt.servlet.client.service.SmtServiceProvider"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="MainMenu.jsp" />
<%@taglib uri="/WEB-INF/tlds/AppTagHandler.tld"
	prefix="printPaymentGrid"%>
<!DOCTYPE html>
<html>
<head>
<script src="assets/js/jquery-3.3.1.min.js"></script>
<script>
	$(document).on("click", "#pay-payment", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
		$.get("financeDetails", function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
			var type=$("#pay-payment").attr("data-type");
			window.location = "pay"+"?payment="+type;	
			});
	});
	$(document).on("click", "#print-payment", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
		$.get("financeDetails", function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
			var type=$("#print-payment").attr("data-type");
			window.location = "print"+"?payment="+type;	
			});
	});
</script>
<title>Payment Details</title>
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<link href="assets/css/custom.css" rel="stylesheet" />
</head>
<body>
  
  <% 
  String idAtrr = request.getAttribute("payment") == null ? "" : request.getAttribute("payment").toString();
  Integer id = idAtrr.isEmpty() ? null : Integer.parseInt(idAtrr.toString()); 
  Payment payment =	SmtServiceProvider.getInstance().getSmtPaymentService().findPaymentById(id);
  
  %>
  
<printPaymentGrid:printPaymentDetailsGrid payment="<%=payment%>"/>
</html>

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
	$(document).on("click", "#view-payment", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
		$.get("finance", function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
			var type=$("#view-payment").attr("data-type");
			window.location = "financeDetails"+"?payment="+type;
		});
	});
	
</script>
<title>Finance</title>
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<link href="assets/css/custom.css" rel="stylesheet" />
</head>
<body>
  
<printPaymentGrid:printPaymentGrid />
</body>
</html>
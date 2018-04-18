<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="MainMenu.jsp" />
<%@taglib uri="/WEB-INF/tlds/AppTagHandler.tld"
	prefix="printStudentGrid"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="assets/js/jquery-3.3.1.min.js"></script>
<script>
	$(document).on("click", "#generate-payment", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
		$.get("index", function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
			var type=$("#generate-payment").attr("data-type");
			window.location = "generatePayment"+"?student="+type;	
			});
	});
</script>
<title>SMT</title>
</head>
<body>
<printStudentGrid:printStudentGrid/>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shopping Cart  | Cart List </title>
<link rel="stylesheet" type="text/css" href='${pageContext.request.getContextPath()}/webjars/bootstrap/5.3.0/css/bootstrap.min.css' />
<script type="text/javascript" src="${pageContext.request.getContextPath()}/webjars/bootstrap/5.3.0/js/bootstrap.min.js"></script>

<%@ include file="headerCart.jsp" %>
</head>

<body>
<h3>Please fill information</h3>
<mvc:form action="placeOrder" modelAttribute="orderList">
	<table>
	<tr>
	<td>Customer Name (*)</td>
	<td><mvc:input path="customerName" required="true" placeholder="Customer Name"/></td>
	</tr>

    <tr>
    	<td>Customer Address (*)</td>
    	<td><mvc:input path="customerAddress" required="true" placeholder="Customer Address"/></td>
    </tr>


	</table>
<button type="submit" class="btn btn-primary">Continue</button>
	</mvc:form>

</body>
</html>
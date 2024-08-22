<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shopping Cart  | Product List </title>
<link rel="stylesheet" type="text/css" href='${pageContext.request.getContextPath()}/webjars/bootstrap/5.3.0/css/bootstrap.min.css' />
<script type="text/javascript" src="${pageContext.request.getContextPath()}/webjars/bootstrap/5.3.0/js/bootstrap.min.js"></script>

<%@ include file="headerCart.jsp" %>
</head>

<body>
<h2>My Order List</h2>
<c:if test="${not empty productList}">
<table class="table">

  <thead>
    <tr>
      <th scope="col">Id</th>
      <th scope="col">Customer Name</th>
      <th scope="col">Customer Address</th>
      <th scope="col">Order Detail</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach var="order" items="${ordersList}">
    <tr>
      <th scope="row">${order.id}</th>
      <td>${order.customerName}</td>
      <td>${order.customerAddress}</td>
      <td>
      <button class="btn btn-primary" onclick="location.href='/orderDetails/${orders.id}'">View Details</button>
      </td>
    </tr>
    </c:forEach>

  </tbody>
</table>
</c:if>

</body>
</html>
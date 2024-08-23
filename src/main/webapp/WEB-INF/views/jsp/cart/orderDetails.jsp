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
<h2>Order Detail List</h2>
<c:if test="${empty orderDetails}">
    <p>Your order detail is empty.</p>
</c:if>
<c:if test="${not empty orderDetails}">
<table class="table">
  <thead>
  <h2>Order Id: ${orderId}</h2>
    <tr>
      <th scope="col">Order Detail Id</th>
      <th scope="col">Product Name </th>
      <th scope="col">Description </th>
      <th scope="col">Unit Price</th>
      <th scope="col">Quantity</th>

    </tr>
  </thead>

<tbody>
  <c:forEach var="detail" items="${orderDetails}">
    <tr>
      <th scope="row">${detail.id}</th>
      <td>${detail.product.name}</td>
      <td>${detail.product.description}</td>
      <td>${detail.product.unitPrice}</td>
      <td>${detail.quantity}</td>

    </tr>
  </c:forEach>
</tbody>
</table>

</c:if>

</body>
</html>


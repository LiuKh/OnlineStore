<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Orders</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<br/><br/><br/>
  	<h2 align="center">My Orders</h2>
  	<br/><br/><br/><br/>
    <table border="1" width="750" align="center">
    	<tr>
	    	<th>Order Number</th>
	    	<th>Pruduct Number</th>
	    	<th>Total Amount</th>
	    	<th>Order Status</th>
	    </tr>
	    <br/>
    	<c:forEach items="${MyOrders}" var="o">
	    	<tr>
	    		<td>${o.ordernum }</td>
	    		<td>${o.quantity }</td>
	    		<td>${o.money }</td>
	    		<td>
	    			<c:choose>
	    				<c:when test="${o.status == 0}">
	    					<B>OBLIGATION</B>&nbsp;<a href="${pageContext.request.contextPath }/payment.jsp?orderNum=${o.ordernum}&orderMoney=${o.money}">Proceed to Check</a>
	    				</c:when>
	    				<c:when test="${o.status == 1 }">
	    					<B>PAID</B>
	    				</c:when>
	    				<c:when test="${o.status == 2 }">
	    					<B>DELIVERED</B>
	    				</c:when>
	    			</c:choose>
	    		</td>
	    	</tr>
    	</c:forEach>
    </table>
  </body>
</html>

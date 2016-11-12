<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Main Welcome Page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/all.css">

  </head>
  	
  <body>
	    <br/>
	    <br/>
	    <h1>Shopping at Online Store</h1>
	    <br/>
	    <a href="${pageContext.request.contextPath }">Home</a>&nbsp;
	    <c:if test="${sessionScope.customer_session==null}">
	    	<a href="${pageContext.request.contextPath }/cLogin.jsp">Login</a>&nbsp;
	    	<a href="${pageContext.request.contextPath }/Register.jsp">Register</a>&nbsp;
	    	<a href="${pageContext.request.contextPath }/cart.jsp">Shopping Cart</a>&nbsp;
	    </c:if>
	    <c:if test="${sessionScope.customer_session!=null }">
	    	Welcome: ${sessionScope.customer_session.nickname}&nbsp;
	    	<a href="${pageContext.request.contextPath }/servlet/client/ClientServlet?op=logout">Logout</a>&nbsp;
	   		<a href="${pageContext.request.contextPath }/servlet/client/ClientServlet?op=showOrders">My Orders</a>&nbsp;
	    	<a href="${pageContext.request.contextPath }/cart.jsp">Shopping Cart</a>&nbsp;
	    </c:if>
	    <br/>
	    <br/>
  </body>
</html>

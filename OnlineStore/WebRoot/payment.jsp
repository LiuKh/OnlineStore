<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>PAYMENT</title>
    
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
    <h2>Payment Page</h2>
    <br/>
    <br/>
    
    <form action="" method="post">
    	<table align="center" width="900">
    		<tr>
    			<td>
    				Order Number: <input type="text" name="ordernum" value="${order.ordernum }${param.orderNum}" readonly="readonly"/>
    			</td>
    			<td>
    				Amount: <input type="text" name="money" value="${order.money }${param.orderMoney}" size="3"/>$
    			</td>
    		</tr>
    	</table>
    </form>
  </body>
</html>

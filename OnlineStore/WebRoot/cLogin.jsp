<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="token.tag.com" prefix="t" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>Registrition</title>
    
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
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    
    <form action="${pageContext.request.contextPath }/servlet/client/ClientServlet?op=cLogin" method="post">
    	<table  border="1" align="center" width="350">
    		<tr>
    			<td >User Name: </td>
    			<td>
    				<input type="text" name="username"/>
    			</td>
    		</tr>
    	
    		<tr>
    			<td >Password: </td>
    			<td>
    				<input type="text" name="password"/>
    			</td>
    		</tr>
    		<tr>
    			<td colspan="2" align="center">
    				<input type="submit" value="SUBMIT"/>
    			</td>
    		</tr>
    	</table>
    </form>
    
    <br/>
    <br/>
    <br/>
  </body>
</html>

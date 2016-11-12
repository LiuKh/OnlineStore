<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="token.tag.com" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    
    <form action="${pageContext.request.contextPath }/servlet/client/ClientServlet?op=Registrition" method="post">
    	<table  border="1" align="center" width="450">
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
    			<td >Nick Name: </td>
    			<td>
    				<input type="text" name="nickname"/>
    			</td>
    		</tr>
    		
    		<tr>
    			<td >Phone Number: </td>
    			<td>
    				<input type="text" name="phone"/>
    			</td>
    		</tr>
    		
    		<tr>
    			<td >Email: </td>
    			<td>
    				<input type="text" name="email"/>
    			</td>
    		</tr>
    		
    		<tr>
    			<td >Address: </td>
    			<td>
    				<input type="text" name="address" size="20"/>
    			</td>
    		</tr>
    		
    		<tr>
    			<td colspan="2" align="center">
    				<input type="submit" value="save"/>
    			</td>
    		</tr>
    	</table>
    </form>
    
    <br/>
    <br/>
    <br/>
  </body>
</html>

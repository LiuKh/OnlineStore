<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>${product.productName} shopping page</title>
    
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
  	<br/>
	  <table align="center" >
	  	<tr>
	  		<td align="center" >
	    		<img src="${pageContext.request.contextPath }/photos/${product.path}/${product.fileName}" height="300" width="225" alt="${product.fileName}"/>
	    	</td>
	    </tr>
	    <tr>
	  		<td align="center" >
	  		<br/>
	  		<br/>
	    		Product Name: ${product.productName}<br/>
	    		Seller: ${product.seller}<br/>
	    		Price: $ ${product.price} <br/>
	    		Description: ${product.description}<br/>
	    		<br/>
	    		<a href="${pageContext.request.contextPath }/servlet/client/ClientServlet?op=addToCart&productId=${product.id}">ADD TO SHOPPING CART</a><br/>
	    		<br/>
	    		<a href="javascript:window.history.back()">RETURN</a>
	    	</td>
	    </tr>
	    
	    
	  </table>
  </body>
</html>

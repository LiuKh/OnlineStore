<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Management</title>
    
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
	    <br/>
	    <h1>MANAGEMENT</h1>
	    <br/>
	    <a href="${pageContext.request.contextPath }/management/addCategory.jsp?op=addCategory">Add Category</a>
	    <a href="${pageContext.request.contextPath }/servlet/management/ManagementServlet?op=showCategories">Show Categories</a>
	    <a href="${pageContext.request.contextPath }/servlet/management/ManagementServlet?op=addProduct">Add Products</a>
	    <a href="${pageContext.request.contextPath }/servlet/management/ManagementServlet?op=showProducts">Show Products</a>
	    <a href="">Processing Order</a>
	    <a href="">Processed Order</a>
	    <br/>
	    <br/>
  </body>
</html>

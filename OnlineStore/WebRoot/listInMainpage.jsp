<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/welcomePage.jsp"%>
  <br/>
  <br/>
  <body>
	<a href="${pageContext.request.contextPath }">All Categories: </a>
	<c:forEach items="${clist}" var="citem">
		<a href="${pageContext.request.contextPath }/servlet/client/ClientServlet?op=showOneCategory&categoryId=${citem.id}">${citem.name}</a>&nbsp;
	</c:forEach>
	<br/>
	<br/>
   	<table width="700" align="center">
   		<tr>
   			<c:forEach items="${page.records }" var="p">
   				<td align="center">
   					<img src="${pageContext.request.contextPath }/photos/${p.path}/${p.fileName}" height="180" width="150" alt="${p.fileName}"/><br/>
   					<nobr>${p.productName}</nobr><br/>
   					<nobr>Price: ${p.price}</nobr><br/>
   					<a href="${pageContext.request.contextPath }/servlet/client/ClientServlet?op=details&id=${p.id}">Details</a>
   				</td>
   			</c:forEach>
   		</tr>
   		<tr>
   			<td align="center" colspan="5">
   			<br/>
   			<br/>
   				Page: ${page.pageNum }/${page.totalPageNum } &nbsp;&nbsp;
					<a href="${pageContext.request.contextPath }${page.url}&num=${page.prePageNum}">Previous</a>&nbsp;
					<a href="${pageContext.request.contextPath }${page.url}&num=${page.nextPageNum}">Next</a>
   			</td>
   		</tr>
   	</table>
  </body>
</html>

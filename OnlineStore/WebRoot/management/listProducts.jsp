<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
 	<%@include file="/management/head.jsp" %>
	<br/>
	<br/>
	<body>
		<table border="1" width="800" align="center">
			<tr>
				<th>NAME</th>
				<th>SELLER</th>
				<th>PRICE</th>
				<th>DESCRIPTION</th>
				<th>CATEGORY</th>
				<th>PHOTO</th>
				<th>OPERATION</th>
			</tr>
			<c:forEach items="${page.records}" var="list_item">
				<tr>
					<td>${list_item.productName }</td>
					<td>${list_item.seller }</td>
					<td>${list_item.price }</td>
					<td>${list_item.description }</td>
					<td>${list_item.category.name }</td>
					<td>
						<img src="${pageContext.request.contextPath }/photos/${list_item.path}/${list_item.fileName}" height="125" width="75" alt="${list_item.fileName}"/>
					</td>
					<td>
						<a href="">revise</a>
						<a href="">delete</a>
					</td>
				</tr>
			</c:forEach>
			
			<tr>
				<td align="center" colspan="7">
					Page: ${page.pageNum }/${page.totalPageNum } &nbsp;&nbsp;
					<a href="${pageContext.request.contextPath }${page.url}&num=${page.prePageNum}">Previous</a>&nbsp;
					<a href="${pageContext.request.contextPath }${page.url}&num=${page.nextPageNum}">Next</a>
				</td>
			</tr>
		</table>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
		<br/>
	</body>
  
</html>

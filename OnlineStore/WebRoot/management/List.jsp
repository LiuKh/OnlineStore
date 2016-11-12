<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
 	<%@include file="/management/head.jsp" %>
	<br/>
	<br/>
	<body>
		<table border="1" width="450" align="center">
			<tr>
				<th>ID</th>
				<th>name</th>
				<th>description</th>
				<th>operation</th>
			</tr>
			<c:forEach items="${list}" var="list_item" varStatus="vs">
				<tr>
					<td>${vs.count}</td>
					<td>${list_item.name }</td>
					<td>${list_item.description }</td>
					<td>
						<a href="${pageContext.request.contextPath }">revise</a>
						<a href="">delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</body>
  
</html>

<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@include file="/welcomePage.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<br/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>Shopping cart</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<script type="text/javascript">
		function changeNum(inputObj, oldValue, id){
			var sure = window.confirm("Are u sure to change the number of this product?")
			if(sure){
				var num = inputObj.value;
				
				if(!/^[1-9][0-9]*$/.test(num)){
					alert("Plz input right number");
					return;
				}else{
					window.location.href="${pageContext.request.contextPath}/servlet/client/ClientServlet?op=changeNum&num=" + num + "&productId=" + id; 
				}
				
				
			}else{
				inputObj.value = oldValue;
			}
		}
		
		function deleteProduct(productId){
			var sure = window.confirm("Are u sure to delete this item or these items?");
			if(sure){
				window.location.href="${pageContext.request.contextPath}/servlet/client/ClientServlet?op=deleteProduct&productId=" + productId;
			}
		}
		
		function removeAll(){
			var sure = window.confirm("Are u sure to clear shopping cart?");
			if(sure){
				window.location.href="${pageContext.request.contextPath}/servlet/client/ClientServlet?op=removeAll"
			}
		}
	</script>
  </head>
  
  <body>
	<c:if test="${empty sessionScope.cart.map}">
		<br/>
		<br/>
		<h3>U didn't buy anything</h3>
	</c:if>
	
	<c:if test="${!empty sessionScope.cart.map }">
		<table align="center" border="1" width="800">
			<tr>
				<th>Product Name</th>
				<th>Number of Products</th>
				<th>Unit Price</th>
				<th>Amount Money</th>
				<th>Operation</th>
			</tr>
			<c:forEach items="${sessionScope.cart.map}" var="me">
				<tr>
					<td>${me.value.product.productName }</td>
					<td><input type="text" id="number" value="${me.value.numberOfProducts }" size="1" onchange="changeNum(this, ${me.value.numberOfProducts }, '${me.value.product.id }')" /></td>
					<td>$ ${me.value.product.price }</td>
					<td>$ ${me.value.item_money }</td>
					<td>
						<a href="javascript:deleteProduct('${me.key}')">Delete Product</a>
					</td>
				</tr>
			</c:forEach>
				<tr>
					<td colspan="5" align="right">
						<a href="javascript:removeAll()">Remove All</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<B>Number of Products: </B>
						${sessionScope.cart.totalNumberOfProducts } &nbsp;&nbsp;
						<B>Total Amount of Money: </B>
						$ ${sessionScope.cart.money }&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath }/servlet/client/ClientServlet?op=addOrder">Proceed to Check</a>
					</td>
				</tr>
			
		</table>
	</c:if>
   </body>
</html>

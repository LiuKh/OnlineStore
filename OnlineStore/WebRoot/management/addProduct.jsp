<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@include file="/management/head.jsp" %>
<%@ taglib uri="token.tag.com" prefix="t" %>

  <br/>
  <br/>
  <body>
    <form enctype="multipart/form-data" action="${pageContext.request.contextPath }/servlet/management/ManagementServlet?op=addProductIntoDB" method="post">
    	<table border="1" width="450" align="center">
    		<tr>
    			<td>Product name:</td>
    			<td>
    				<input type="text" name="productName"/>
    			</td>
    		</tr>
    		
    		<tr>
    			<td>Seller:</td>
    			<td>
    				<input type="text" name="seller"/>
    			</td>
    		</tr>
    		
    		<tr>
    			<td>Price:</td>
    			<td>
    				<input type="text" name="price"/>
    			</td>
    		</tr>
    		
    		<tr>
    			<td>Photo:</td>
    			<td>
    				<input type="file" name="file"/>
    			</td>
    		</tr>
    		
    		<tr>
    			<td>Description:</td>
    			<td>
    				<textarea rows="2" cols="40" name="description"></textarea>
    			</td>
    		</tr>
    		
    		<tr>
    			<td>Category:</td>
    			<td>
    				<select name="categoryId">
    					<c:forEach items="${list}" var="itm">
    						<option value="${itm.id}">${itm.name}</option>
    					</c:forEach>
    				</select>
    			</td>
    		</tr>
    		
    		<tr>
    			<td colspan="2">
    				<input type="submit" value="save" name="save"/>
    			</td>
    		</tr>
    	</table>
    </form>
  </body>
</html>

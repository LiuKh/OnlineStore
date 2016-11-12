<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
 	<%@include file="/management/head.jsp" %>
 	<%@ taglib uri="token.tag.com" prefix="t" %>
 	
 	<script type="text/javascript">
		function toSubmit(){
			var nameValue = document.getElementById("name").value;
			if(nameValue.trim()==""){
				alert("The name can't be empty");
				return;
			}
			document.forms[0].submit();
		}
		
		window.onload=function(){
			document.getElementById("name").onblur=function(){
				if(this.value == ""){
					document.getElementById("s1").innerHTML = "<font color='red' >The name can't be empty</font>";
					this.focus();
					return;
				}else{
					document.getElementById("s1").innerHTML = "";
				}
			}
		}
	</script>
	<br/>
	<br/>
	<body>
	    <form action="${pageContext.request.contextPath }/servlet/management/ManagementServlet?op=addCategory" method="post">
		    	<table border="1" width="450" align="center">
		    		<tr>
		    			<td>category name: </td>
		    			<td>
		    				<input type="text" name="name" id="name"/><span id="s1"></span>
		    			</td>
		    		</tr>
		    		
					<tr>
		    			<td>description: </td>
		    			<td>
		    				<textarea rows="2" cols="40" name="description" id="description"></textarea>
		    			</td>
		    		</tr>   
		    		 		
		    		<tr>
		    			<td colspan="2">
		    				<input type="button" value="save" onclick="toSubmit()"/>
		    			</td>
		    		</tr>
		    		
		    	</table>
	    </form>
	</body> 
</html>

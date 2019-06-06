<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 
 <%
   String jQueryPath =request.getContextPath()+"/BuildJQuery/development-bundle/";
    
 %>
  
      
  
  <script type="text/javascript" src="<%=jQueryPath%>jquery-1.8.0.js"></script>
  <script type="text/javascript" src="<%=jQueryPath%>jquery.jcryption-1.1.js"></script>
   
   <script type="text/javascript" src="<%=request.getContextPath()%>/js/utils/stringCryption.js"></script>
  
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/utils/passwordCryption.js"> </script>
 
   
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Password Encryption</title>
</head>
<body>
  
   <form name="login" id="login" action="/decryptPassword" method="post">
    
      <table border="0">
       <tr><td>
   		Username <input type="text" name="username" >
   		</td></tr><tr><td>   		
   		Password <input type="password" id="password" name="password" >
   		</td></tr><tr><td>   		 
   		   
   		 	 <input type="button" id="viewPass" value="viewPass"  onClick="javascript:viewEncPass();">
   		  	 
   		</td></tr><tr><td colspan="2"> 
   		<input type="button" name="login" value="Login" onClick="javascript:submitPsswordForm();">
   	  </td></tr>
   		</table>
   </form>
</body>
</html>
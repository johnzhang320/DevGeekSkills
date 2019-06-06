<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.loan.agent.services.Constant" %> 
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 <style>
  strong {
      color:blue;
  }
 p {
	text-indent:0px;
	/*text-shadow: #9FBEB9 1px 1px 1px;*/
	margin-left:10px;
	margin-right:10px;
}
.tabTitle {
	font: 10pt arial;
}
h2 {
font: bold 12pt arial;
}
 
  </style> 
 
 <%
      String currentJSPCode=(String) request.getSession().getAttribute(Constant.HELP_CURRENT_JSP_CODE);
       
  %>
 
<div class="externalcontent">
 
   <logic:present name="currentJSPCode"> 	    
   		<jsp:include page="<%=currentJSPCode%>" flush="true"/>
	</logic:present> 

 </div>
 
  
 
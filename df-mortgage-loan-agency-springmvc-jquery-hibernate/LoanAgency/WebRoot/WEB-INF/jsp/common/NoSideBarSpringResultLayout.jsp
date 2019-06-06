<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" pageEncoding="ISO-8859-1"%>
 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
 

<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr">

  <head>
   <meta http-equiv="X-UA-Compatible" content="IE=9"/>
   <meta http-equiv="X-UA-Compatible" content="IE=8"/>
   <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <title>Loans-agents.com</title>
        <jsp:include page="./Scripts.jsp" flush="true" />   
   
   
  </head>
 

  <body class="body">
   <div class=" container "> 
    <div class="leftExDiv">
       <div class="layout">   
	<tiles:insertAttribute name="header"/>
  	<tiles:insertAttribute name="menu"/>
  	 <div class="externalcontent">
  	 <div>
  		<tiles:insertAttribute name="body"/>
  	  
  	 </div>
  	   <tiles:insertAttribute name="ResultBottom"/>   
  	 </div>
  	   
 
  	</div>
  	</div>
  	 <logic:present name="agentId" > 
  	  <div class="rightExDiv">	
    	<div class="leftcontent">
  			<tiles:insertAttribute name="adbox"/>  	   	  
  		</div>
  	</div>
 </logic:present>	 
  	</div>
  	 	<tiles:insertAttribute name="footer"/>
  </body>
</html>

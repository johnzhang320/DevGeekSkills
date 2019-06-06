<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@ page language="java" import ="com.loan.agent.services.Constant" %>
 
 
<html>
 <meta http-equiv="X-UA-Compatible" content="IE=9"/>
   <meta http-equiv="X-UA-Compatible" content="IE=8"/>
      <!-- Google Business Account Domain Verify -->
   <meta name="google-site-verification" content="RTjM7tIZuLfKL7-3B3VOYCKBLBoiG1S4H_1Yj1yAfqc" />
    <title>Loans-agents.com  </title>
    
    
  <head>
     <!-- Google Analytics Track Code -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common/layout.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common/utilities.css" />
 	 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common/module.css" />  
     <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/menufiles/dropDownMenu.css" /> 
     <script type="text/javascript" src="<%=request.getContextPath()%>/js/utils/dialog.js"> </script>
  </head>
 

  <body class="unlimitedContainer">
  
  	 <tiles:insertAttribute name="body"/> 
    
  </body>
</html>

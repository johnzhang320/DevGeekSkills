<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 
 
 
<html>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <!-- Google Business Account Domain Verify -->
    <meta name="google-site-verification" content="RTjM7tIZuLfKL7-3B3VOYCKBLBoiG1S4H_1Yj1yAfqc" />
    <title>Loans-agents.com  </title>
	<meta http-equiv="cache-control" content="max-age=0" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
	<meta http-equiv="pragma" content="no-cache" />    
    
  <head>
     <!-- Google Analytics Track Code -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common/layout.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common/utilities.css" />
 	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common/module.css" />  
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/menufiles/dropDownMenu.css" /> 
 	<script type="text/javascript" src="<%=request.getContextPath()%>/js/utils/dialog.js"> </script>
  	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/angular/module/app.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/angular/service/dmarc_service.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/angular/controller/dmarc_controller.js"></script>
  </head>
 

  <body class="body">
  <div class=" container "> 
    <div class="leftExDiv">
       <div class="layout">    
	       <tiles:insertAttribute name="header"/>
  	       
  	       <div class="externalcontent">
  		      <tiles:insertAttribute name="body"/>  	   	  
  	        </div>
   	 
  	   </div>
   </div>
  	
  
 
  </div>
    <tiles:insertAttribute name="footer"/>	 
  </body>
</html>

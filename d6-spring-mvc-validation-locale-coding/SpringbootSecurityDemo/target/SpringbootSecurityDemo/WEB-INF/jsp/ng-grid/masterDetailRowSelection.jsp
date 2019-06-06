<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

  
   
     
	<%
	 
	 String contextPath =request.getContextPath();
	 
	 
	%>
	   <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.0/angular.js"></script>
	   <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.0/angular-touch.js"></script>
	   <script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.5.0/angular-animate.js"></script>
	   <script src="http://ui-grid.info/docs/grunt-scripts/csv.js"></script>
	   <script src="http://ui-grid.info/docs/grunt-scripts/pdfmake.js"></script>
	   <script src="http://ui-grid.info/docs/grunt-scripts/vfs_fonts.js"></script>
	  
	   <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-grid/4.0.4/ui-grid.js"></script>
	   <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-grid/4.0.4/ui-grid.css" />
	  
	   <script src="<%=contextPath%>/js/ng-grid/masterGridRowSelection.js"></script> 
	   <style type="text/css">
	   	.gird {
	   		width:900px;
	   		height:400px
	   	}
	   </style>
	   <div class="module-title" >
			<spring:message code="title.dmarc.reports"/>  
			<input type="button" value="Help" class="helpbutton" onclick="javascript:popUpFixedName('/Spring4AngularHibernateTile3RestCassandra/helpDmarcReport.html','1300','400');">            
		</div>
	   <div ng-app="ngGridMasterDetailRowSelection">
	       <div ng-controller="SecondCtrl">
		     	 <br/>		   	  
		         <div ui-grid="gridOptions" ui-grid-selection class="grid"></div>
	       </div>
  	   </div>
 
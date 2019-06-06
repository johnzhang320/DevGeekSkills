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
   
  
    Integer helpTabPointer=0;
  
 %>
  <script>
  	$(function() {    	  	 
  		$("#tabs").tabs({selected: <%=helpTabPointer%>});
  		
  	});
   
  </script>
  
        
<div class="externalcontent">
  <div class="bigbox">
    <div id="tabs">
    	 <ul>
	    <li><a href="#tabs-1"><span class="tabTitle"> Pull Agent Profile</span></a></li>	 
	    <li><a href="#tabs-2"><span class="tabTitle"> Pull Quote Data</span></a></li>		    
	    <li><a href="#tabs-3"><span class="tabTitle"> Pull Application Checklist</span></a></li>	   
	    <li><a href="#tabs-4"><span class="tabTitle"> Edit, Save, Attach or Send Email</span></a></li>		
	</ul> 	 
	 
	<div id="tabs-1">
		<jsp:include page="HelpPullAgentProfileData.jsp" flush="true"></jsp:include>
	</div>
	<div id="tabs-2">
		<jsp:include page="HelpPullBorrowerQuoteData.jsp" flush="true"></jsp:include>
	</div>	
	<div id="tabs-3">
		<jsp:include page="HelpPullApplicationCheckList.jsp" flush="true"></jsp:include>
	</div>
	<div id="tabs-4">
		<jsp:include page="HelpEditSaveAttachSendEmail.jsp" flush="true"></jsp:include>
	</div> 
	
  </div>
 </div>
 </div>
 
  
 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java"  import="java.util.List" %>  
<%@ page language="java"  import="java.util.ArrayList" %> 
<%@ page language="java"  import="com.loan.agent.services.SelectedValueVo"%>
<%@ page language="java"  import="com.loan.agent.services.SingletonData"%> 
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

  
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<% 
    String localeUrl = request.getContextPath()+"/localeChange.html"; 
    List<SelectedValueVo> list =(ArrayList<SelectedValueVo>) request.getSession().getAttribute("LanguageList"); 
    if (null==list) {
    	list = SingletonData.getInstance().getLanguageInitList();
    	  request.getSession().setAttribute("LanguageList",list);
    }
     
    if (null==list) {
    	System.out.println("Language List=null");
    }  
  
 %>
 	<script type="text/javascript" src="<%=request.getContextPath()%>/ui/static/js/lib/jquery-1.8.0.js"></script>
 
      
<img src="<%=request.getContextPath()%>/ui/static/images/agents/loans-agent-logo.png">
<br> 
<div style="margin-left:672px;margin-bottom:8px;">
        
	 <select name="localeChange" id="localeChange" style="width:120px;border:1px solid;"  onchange="javascript:languageSwitch(event);">
	    
	   
	     <c:if test="${not empty LanguageList}"> 
	       <c:forEach var="lang" items="${LanguageList}">
			   <c:if test="${lang.selected=='false'}">
			   		 <option value=${lang.value}>${lang.label}
			   </c:if>	 
			    <c:if test="${lang.selected=='true'}">
			   		 <option value=${lang.value} selected="selected" >${lang.label}
			   </c:if>	 
			</c:forEach>
		 </c:if> 
	   
    
    </select>
    <br>
</div>
     
     

 <script type="text/javascript" src="<%=request.getContextPath()%>/ui/static/js/form/header.js"> </script>  
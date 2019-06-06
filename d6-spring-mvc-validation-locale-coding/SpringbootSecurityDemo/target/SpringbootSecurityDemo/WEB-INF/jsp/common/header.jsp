<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java"  import="java.util.List" %>  
<%@ page language="java"  import="java.util.ArrayList" %> 
<%@ page language="java"  import="com.loan.agent.services.SelectedValueVo"%>
<%@ page language="java"  import="com.loan.agent.services.SingletonData"%> 
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
  
 
	
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
 	<script type="text/javascript" src="<%=request.getContextPath()%>/js/lib/jquery-1.8.0.js"></script>
 
      
<img src="<%=request.getContextPath()%>/images/agents/loans-agent-logo.png">
<br> 
<div style="margin-left:672px;margin-bottom:8px;">
        
	 <select name="localeChange" id="localeChange" style="width:120px;border:1px solid;"  onchange="javascript:languageSwitch(event);">
	    
	    <logic:present name="LanguageList" > 
	     
	      <logic:iterate id="ptr" name="LanguageList" type="com.loan.agent.services.SelectedValueVo" >    
 	            <logic:equal name="ptr" property="selected"  value="false"> 
 	      		   <option value='<bean:write name="ptr" property="value"/>'><bean:write name="ptr" property="label"/></option>
 	      		</logic:equal>
 	           <logic:equal name="ptr" property="selected"  value="true"> 
    		   		<option value='<bean:write name="ptr" property="value"/>' selected="selected" ><bean:write name="ptr" property="label"/></option>
    	        </logic:equal>
       </logic:iterate>
      </logic:present> 
    </select>
    <br>
</div>
     
     

 <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/header.js"> </script>  
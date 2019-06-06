<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 
<%@ page language="java"  import="java.util.List" %>  
<%@ page language="java"  import="java.util.ArrayList" %> 
<%@ page language="java"  import="com.loan.agent.services.SelectedValueVo"%>
<%@ page language="java"  import="com.loan.agent.services.SingletonData"%> 
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
  
 <style>
 .headerDiv {
 	float:right;
 	margin-top:2px; 
 	margin-right:10px;
 }
 .headerDiv table tr td {
     border:0px;
   }
 </style> 
 
<% 
    
    String imageFile =request.getContextPath()+"/images/layoutImages/"+(String)request.getParameter("headerImage");
    String localeUrl = request.getContextPath()+"/localeChange.html"; 
    List<SelectedValueVo> list =(ArrayList<SelectedValueVo>) request.getSession().getAttribute("LanguageList"); 
    if (null==list) {
    	list = SingletonData.getInstance().getLanguageInitList();
    	  request.getSession().setAttribute("LanguageList",list);
    }
    
  
 %>
 <script type="text/javascript" src="<%=request.getContextPath()%>/js/lib/jquery-1.8.0.js"></script>
  
<img src="<%=request.getContextPath()%>/images/agents/loans-agent-logo.png">
 <div class="headerDiv">   
   <table>
     <tr><td>
         <!-- <span style="color:blue;"> Contact us: support.staff@loans-agent.com</span> -->
        <span style="float:right;" id="siteseal"><script type="text/javascript" src="https://seal.godaddy.com/getSeal?sealID=2SyYFy3E8UgbPIdALxzYHyDfSyTuanTWq8oTCN1JeATEg8Kn0dZXWbPrdDEf"></script></span> 
     </td></tr>
   	  <tr><td>
   	    <table>
   	       <tr><td>
    		<!-- <a href="<%=localeUrl%>?locale=en"><spring:message code="label.en.US"/></a>  -->
    		<spring:message code="label.language"/>
    	   </td><td>
  			<!-- | &nbsp;<a href="<%=localeUrl%>?locale=zh_CN"><spring:message code="label.zh.CN"/></a> -->
  			 <select name="localeChange" id="localeChange" style="width:120px;"  onchange="javascript:languageSwitch(event);">
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
 		   </td></tr>
  		</table>
    
        </td></tr> 
    
       </table>       
     </div>         
     

 <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/header.js"> </script>  
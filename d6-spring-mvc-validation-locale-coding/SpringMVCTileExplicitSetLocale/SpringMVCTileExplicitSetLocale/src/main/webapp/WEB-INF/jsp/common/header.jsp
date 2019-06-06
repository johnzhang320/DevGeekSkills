 <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
   String localeUrl = request.getContextPath()+"/localeChange.html"; 
  %>
<img src="<%=request.getContextPath()%>/images/agents/loans-agent-logo.png">
 <div class="headerDiv">   
   <table>
     <tr><td>
         <!-- goDaddy Security Verified Stamp -->
        <span style="float:right;" id="siteseal"><script type="text/javascript" src="https://seal.godaddy.com/getSeal?sealID=2SyYFy3E8UgbPIdALxzYHyDfSyTuanTWq8oTCN1JeATEg8Kn0dZXWbPrdDEf"></script></span> 
     </td></tr>
      <tr><td>
   	    <table>
   	       <tr><td>
    		<a href="<%=localeUrl%>?language=en"><spring:message code="label.en.US"/></a>  
    	   </td><td>
  			| &nbsp;<a href="<%=localeUrl%>?language=zh_CN"><spring:message code="label.zh.CN"/></a> 
  			</td></tr>
  		</table>    
    </td></tr> 
     </table>   
    
  </div>    
 
 
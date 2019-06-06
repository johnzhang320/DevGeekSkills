<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
  <%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<div class="header_logo">

<logic:present name="LoginUserId" scope="session">
     <logic:notEmpty name = "UserName" >
     <div style="margin-left:570px;margin-top:40px;"> 
      <bean:write name="UserName"/>
       <a href="<%=request.getContextPath()%>/userLogout.html"> Logout</a>
       </div>
      </logic:notEmpty>
   </logic:present>
   <logic:empty name = "UserName" >      
   
   </logic:empty>   
   </div>
 
<% 
    
    String imageFile =request.getContextPath()+"/images/layoutImages/"+(String)request.getParameter("headerImage");
      
   
 %>
<div class="header">  
 
<img src="<%=request.getContextPath()%>/images/agents/loans-agent-logo.png">
 <div style="float:right;margin-top:2px; margin-right:10px;">   
   <table>
     <tr><td>
         <!-- <span style="color:blue;"> Contact us: support.staff@loans-agent.com</span> -->
        <span style="float:right;" id="siteseal"><script type="text/javascript" src="https://seal.godaddy.com/getSeal?sealID=2SyYFy3E8UgbPIdALxzYHyDfSyTuanTWq8oTCN1JeATEg8Kn0dZXWbPrdDEf"></script></span> 
     </td></tr>
      <tr><td>
          <logic:notPresent name="loginAgentId">
             <table style="margin-bottom:-10px;margin-top:-5px;">
                <tr><td>
          		     <a style="text-decoration: none;font-weight:bold;color:blue;font-size:12px;" href="<%=request.getContextPath()%>/agentLogin.html?agentLoginDirect=yes"><span>Agent&nbsp;Login</span> </a>&nbsp; 
                </td><td>		   
          		     <a style="text-decoration: none;font-weight:bold;color:blue;font-size:12px;" href="<%=request.getContextPath()%>/agentSignup.html?agentLoginDirect=yes"><span>Agent&nbsp;Signup</span> </a>&nbsp;
                </td></tr>		   
             </table>             
          </logic:notPresent>
    
          <logic:present name="loginAgentId">
           		<a style="text-decoration: none;font-weight:bold;color:blue;font-size:12px;margin-bottom:-10px;margin-top:-5px;"" href="<%=request.getContextPath()%>/agentLogout.html?agentLoginDirect=yes"><span>Agent&nbsp;Logout</span> </a>&nbsp;
          </logic:present>     
        </td></tr>
       </table>       
     </div>         
     <img src="<%=imageFile%>" />
   
    
 </div>

<script type="text/javascript" >
	function userLogout() {
	      var baseUrl ="userLogout.html";
		  $.ajax({
			type: 'POST',			 
			dataType: "json",
			url: baseUrl,			
			success: function(data, textStatus, jqXHR){
		 		location.reload(true);
			},
			error: function(jqXHR, textStatus, errorThrown){
			 
			}
		});
	}
</script>
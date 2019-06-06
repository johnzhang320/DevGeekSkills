<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" import ="com.loan.agent.services.Constant" %>

<%@ page language="java" import ="java.util.List" %>

   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common/tablescroll.css" />
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/utils/tablescroll.js"> </script> 
 <%
   int line=0;
    String imagePath =request.getContextPath()+"/images/layoutImages/";	  
   
  %> 
  
 <script  type="text/javascript">
/*<![CDATA[*/

jQuery(document).ready(function($)
{
	$('#thetable5').tableScroll({height:500});
  
});

/*]]>*/

 
 
      
   function submitAgentData(selectedId) {
      document.forms[0].actionType.value="actionCurrentCheck";
      var checked = document.getElementById(selectedId).checked;
      
      if (checked) {
      	  document.forms[0].currentCheckStatus.value="checked";
      	  
      } else {
      	 document.forms[0].currentCheckStatus.value="unchecked";
      }
      document.forms[0].currentCheckStatusId.value=selectedId;
      document.forms[0].submit();
   }
       
    function SelectAllAgentData() {
       document.forms[0].actionType.value="actionSelectAllCheck";
       
      var selectAll = document.getElementById("selectAll").checked;
      
      var selectAllStatus = document.getElementById("selectAllStatus").checked
      
      if (selectAll) {
      	  document.forms[0].selectAllStatus.value="checked";
      	  
      } else {
      	 document.forms[0].selectAllStatus.value="unchecked";
      }
     
      document.forms[0].submit();
   }  
   function pullSelectedData() {
       document.forms[0].actionType.value="actionPullSelectedData";
       document.getElementById("cProgressSamll").style.display="block";
       document.forms[0].submit();
   }
   
  function refreshParentCloseSelf() {
  	  window.opener.location.reload(false);
  	  self.close();
  }
  </script>
 
             <div id="errorBox" class="errorblock"></div> 
             <div id="cProgressSamll" style="display:none;" style="algin:center;" class="progress_dialog">      
			 <img style="margin-left:30%;margin-top:20%;" src="<%=imagePath%>animateCircle.gif" />
			 </div> 
			       
             <logic:present name="successMessage" >
        	   <div id="saveSuccess" class="saveSuccess">
              	  <span style="font-weight:bold; color:GREEN; font-size:15px;"><bean:write name="successMessage"/></span>
               </div> 
             </logic:present> 
 

   <div class="tableScrollbox" style="width:640px;">   
   
   	  <label class="tstitle" >
   	      Select Agent Profile Data and Pull Selected Data to Create an Email for <bean:write name="loginAgentName"/>
   	  </label>   	
   	  
    <form:form  id="agentDataform" method="POST" name="AgentDataForm" commandName="agentForm" action="agentDataForm.html">
   
    <input type="hidden" name="currentCheckStatus"  >
    <input type="hidden" name="currentCheckStatusId"  >
    <input type="hidden" name="selectAllStatus" >
    <input type="hidden" name="actionType">
    
    <logic:present name="AgentDataList" scope="session" >             

       <table id="thetable5" width="100%" border="0" cellpadding="30"  >
     		<thead>
  			<tr>
    	
   		 	<td> 
   		 	  <input type="checkbox" id='selectAll' value='selectAll' onclick="javascript:submitAgentData('selectAll');">
 	
   			</td>     	  		 
   		 	<td ><font class="tstitle">Select Agent Profile Data</font>
   		 	</td>
   		 	
   		 
      	</tr>   
 	   </thead>
 	   	<tbody>
 	   	
 	    
 	 	<logic:iterate id="agentData" name="AgentDataList" type="com.loan.agent.calculators.vo.AgentDataVo" scope="session" indexId="rowIndex"> 
   			    
      	    <%if (line%2==0) {%>
      			<tr class="rowwhite">    
        	 <%} else { %>
        	    <tr class="rowshade"> 
        	   <%}
        	    line++;        	    
        	    %> 
      		  <td>  
      		     <logic:equal name="agentData" property="selected" value="checked">
  				      <input type="checkbox" id='<bean:write name="agentData" property="agentDataId"/>' value='<bean:write name="agentData" property="value" />' checked onclick="javascript:submitAgentData('<bean:write name="agentData" property="agentDataId"/>');">
			      </logic:equal>  	
 			      <logic:equal name="agentData" property="selected" value="unchecked">    		    	  
  				      <input type="checkbox" id='<bean:write name="agentData" property="agentDataId"/>' value='<bean:write name="agentData" property="value" />' onclick="javascript:submitAgentData('<bean:write name="agentData" property="agentDataId"/>');">
 			     </logic:equal>  
 			 </td>
 		     <td>
    	   	 
    	  	  <font style="font-size:13px;color:RGB(50,50,50);font-weight:bold;">  
    	        <bean:write name="agentData" property="value" /> 
    	     
    	     </font> 
    	    
    	    </td>                   
   			</tr>
    		 
          </logic:iterate>
         
        </tbody>
 </table>
    
 </logic:present>
 </form:form> 
    <table style="border:0px;">
    <tr><td>
 	 <input type="button" name="close" value="Pull Selected Data" class="buttonImage" onclick="javascript:pullSelectedData();">
     </td><td>
 	 <input type="button" name="close" value="Close" class="buttonLeft" onclick="javascript:refreshParentCloseSelf();">
     </td></tr>
     </table>
  </div>
    
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/formUtils.js"> </script>  
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/selectAgentDataView.js"> </script>   
        




 

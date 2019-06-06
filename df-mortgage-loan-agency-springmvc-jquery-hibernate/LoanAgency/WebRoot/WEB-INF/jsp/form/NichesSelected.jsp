<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ page language="java" import ="com.loan.agent.mvc.formbean.NicheForm" %>
<%@ page language="java" import ="com.loan.agent.services.Constant" %>

<%@ page language="java" import ="java.util.List" %>

   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common/tablescroll.css" />
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/utils/tablescroll.js"> </script> 
  <%
  
	   int line=0;
   %>
 <script  type="text/javascript">
/*<![CDATA[*/

jQuery(document).ready(function($)
{
	$('#thetable').tableScroll({height:700});
  
});

/*]]>*/

 
 
      
   function nicheEditDelete(actionType,nicheIdStr) {
     
   	    
       javascript:popUpFixedName('/nicheForm.html?actionType='+actionType+'&paramNicheId='+nicheIdStr,'600','520');
   }
       
      
  </script>
 
  
 

   <div class="tableScrollbox">   
   
   	  <label class="captain">
   	     Please Select <bean:write name="loginAgentName" /> Offered Niche Loan Program   
   	  </label>   	
     
 <table style="border:0px;" >
  <tr><td>
  <input type="button" name="add" value="Add" class="buttonImage" onclick="javascript:popUpFixedName('/nicheForm.html?actionType=nicheAdd','600','520');">
   </td>
  
  </tr>
  </table> 
    <logic:present name="NichesList" scope="session" >  
           

       <table  id="thetable" width="100%" border="1" cellpadding="0" cellspacing="0" >
     		<thead>
  			<tr>
    	
   		 	 <td><label class="title">
   		 	   <font class="tstitle">&nbsp;</font>
   			</label> 		 
   		 	<td width="90%"><font class="tstitle">Niche Program</font></td>
   		   
      	</tr>   
 	   </thead>
 	   	<tbody>
 	   	
 	    
 	 	<logic:iterate id="review" name="NichesList" type="NicheForm" scope="session" indexId="rowIndex"> 
   	 	 
  		      <%if (line%2==0) {%>
      			<tr class="rowwhite">    
        	 <%} else { %>
        	    <tr class="rowshade"> 
        	   <%}
        	    line++;        	    
        	    %>
    	 	  <div style="margin-bottom:10px;">  
    	      	    <input type="checkbox" id='<bean:write name="review" property="nicheId"/>' value='<bean:write name="review" property="nicheId"/>' onClick="javascript:saveSelectNiche('<bean:write name="review" property="nicheId"/>');">
    			</div>    
    	    <td width="95%">
    	    <font style="font-size:13px;color:RGB(50,50,50);font-weight:bold;">  
    	        <bean:write name="review" property="nicheTitle"/> 
    	     
    	     </font> 
    	     <font style="font-size:12px;color:RGB(50,50,50);">  
    	   		 <bean:write name="review" property="nicheNote"/> 
    	   	 </font>
    	    </td>                   
     		
  		   
  			
  			</tr>
    		 
          </logic:iterate>
         
        </tbody>
 </table>
    
 </logic:present>
 <br>
   <div id="saveNicheIntroSuccess" style="display:none;">
   	<div class="saveSuccess">   	
      	  <span id="nicheIntroSpan" style="font-weight:bold; color:GREEN; font-size:15px;"></span>
     </div> 
   </div>  
   <label class="captain">
   	      <bean:write name="loginAgentName" /> Niche Loan Program Description  
   	  </label>  
	 <textarea id="nicheIntro" name="nicheIntro" class="noteWidthClass"></textarea>	 
	 <br>
	 <input type="button" value="Save" class="buttonImage" onClick="javascript:submitNichesSelected();">
  			    
 </div>  
 
    
  	 	 <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/formUtils.js"> </script>  
		  <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/nichesSelected.js"> </script>   
        




 

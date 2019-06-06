<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ page language="java" import ="com.loan.agent.mvc.formbean.AppCheckListForm" %> 
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
	$('#thetable3').tableScroll({height:700});
  
});

/*]]>*/

        
   function appCheckListEditDelete(actionType,appCheckIdStr) {     
   	    
       javascript:popUpFixedName('/appCheckListForm.html?actionType='+actionType+'&paramCheckId='+appCheckIdStr,'600','520');
   }
   
  </script>
  
   <div class="tableScrollbox">      
   	  <label class="captain">
   	      Loan Application Check List     
   	  </label>   	
     
    <table style="border:0px;" >
     <tr><td>
        <input type="button" name="add" value="Add" class="buttonImage" onclick="javascript:popUpFixedName('/appCheckListForm.html?actionType=appCheckListAdd','600','520');">
      </td>
  
     </tr>
     </table> 
    <logic:present name="appCheckListsList" scope="session" >  
           

       <table  id="thetable3" width="100%" border="1" cellpadding="0" cellspacing="0" >
     		<thead>
  			<tr>
    	
   		 	  		 
   		 	<td width="90%"><font class="tstitle">Application Check List</font></td>
   		 	<td><label class="title">
   		 	   <font class="tstitle">Edit</font>
   			</label>
   			</td>     	
   			<td><label class="title">
   		 	   <font class="tstitle">Delete</font>
   			</label>
   			</td>     
      	</tr>   
 	   </thead>
 	   	<tbody>
 	   	
 	    
 	 	<logic:iterate id="review" name="appCheckListsList" type="AppCheckListForm" scope="session" indexId="rowIndex"> 
   	 	 
  		      <%if (line%2==0) {%>
      			<tr class="rowwhite">    
        	 <%} else { %>
        	    <tr class="rowshade"> 
        	   <%}
        	    line++;        	    
        	    %>
    	 	  
    	    <td width="95%">
    	    <font style="font-size:13px;color:RGB(50,50,50);font-weight:bold;">  
    	        <bean:write name="review" property="title"/> 
    	     
    	     </font> 
    	     <font style="font-size:12px;color:RGB(50,50,50);">  
    	   		 <bean:write name="review" property="note"/> 
    	   	 </font>
    	    </td>                   
     		
  		    <td>  		  
  			   <input type="button" name="edit" value="Edit" class="tinybutton" onclick="javascript:appCheckListEditDelete('appCheckListEdit','<bean:write name="review" property="checkId"/>');">
 			</td><td> 
 			    <input type="button" name="delete" value="Delete" class="tinybutton" onclick="javascript:appCheckListEditDelete('appCheckListDelete','<bean:write name="review" property="checkId"/>');">
 			</td>  
  			
  			</tr>
    		 
          </logic:iterate>
         
        </tbody>
 </table>
    
 </logic:present>
  </div>  
 <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/formUtils.js"> </script>  
 <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/appCheckListListView.js"> </script>   
        


	<div id = "basicInfo" class="tableboxNone" >
	
	<IFRAME id="quoteFrame" style="background-color:white;" WIDTH=820 HEIGHT=800 frameborder="no" scrolling="yes">
	 
	</IFRAME>
</div>

 

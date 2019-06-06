<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ page import="com.loan.agent.services.Constant" %>	
 
 
	<%
	     String connectionStatus = (String) request.getSession().getAttribute(Constant.EMAIL_SERVER_CONNECTION_STATUS);
	     String imagePath =request.getContextPath()+"/images/layoutImages/";
	%>          
    
          <div id="errorBox" class="errorblock"></div> 
          <div id="cProgressSamll" style="display:none;" class="progress_dialog">      
			 <img style="margin-left:20%;margin-top:20%;" src="<%=imagePath%>animateCircle.gif" />
			</div>              
            
       	   <form:form   method="POST" name="emailServerForm" commandName="emailServerForm" enctype="multipart/form-data" target="_self">
       	                    
               <form:hidden id="actionType" path="actionType" />
                 <fieldset > 
                   <legend><span class="AccountCreateTableHeader">Please Upload Attachment Files </span> </legend>
                   
    
      		        	  <table>
      		        	  
        				    <logic:present name="emailListErrorMessage" >
        				      <tr><td>
                          		  <span style="font-weight:bold; color:red; font-size:15px;"><bean:write name="emailListErrorMessage"/></span>
                          	   </td></tr>
                     		 </logic:present>
                     		 
		      			 <tr><td>
		      			 	<label id="pictureContentLabel">Attachment 1:</label>
		      			 	<form:input path="attachment1"  size="80"  maxlength="200" /><br/> 
		      			 	<label id="pictureContentLabel">Upload File :</label>
                            <input id="fileContent" type="file" name="fileContent1"  style="width:340px;" onChange="javascript:uploadAttachment('attachment1');" />
                            <a  href="javascript:deleteAttachment('attachment1')"><img style="border:0px;padding-top:7px;" 
    			    		src="<%=request.getContextPath()%>/images/formImages/b_delete.png" 
    			    		title="Delete current attachment" width="80" height="30" /> </a>    
 		      			 </td></tr>
		      			  
		      		  <tr><td>
		      			 	<label id="pictureContentLabel">Attachment 2:</label>
		      			 	<form:input path="attachment2"  size="80"  maxlength="200" /><br/> 
		      			 	<label id="pictureContentLabel">Upload File :</label>
                            <input id="fileContent" type="file" name="fileContent2"  style="width:340px;" onChange="javascript:uploadAttachment('attachment2');" />
                            <a  href="javascript:deleteAttachment('attachment2')"><img style="border:0px;padding-top:7px;" 
    			    		src="<%=request.getContextPath()%>/images/formImages/b_delete.png" 
    			    		title="Delete current attachment" width="80" height="30" /> </a>     
 		      			 </td></tr>
 		      			 
 		      			  <tr><td>
		      			 	<label id="pictureContentLabel">Attachment3:</label>
		      			 	<form:input path="attachment3"  size="80"  maxlength="200" /><br/> 
		      			 	<label id="pictureContentLabel">Upload File :</label>
                            <input id="fileContent" type="file" name="fileContent3"  style="width:340px;" onChange="javascript:uploadAttachment('attachment3');" />
                              <a  href="javascript:deleteAttachment('attachment3')"><img style="border:0px;padding-top:7px;" 
    			    		src="<%=request.getContextPath()%>/images/formImages/b_delete.png" 
    			    		title="Delete current attachment" width="80" height="30" /> </a>     
 		      			 </td></tr>
 		      			 
 		      			  <tr><td>
		      			 	<label id="pictureContentLabel">Attachment4:</label>
		      			 	<form:input path="attachment4"  size="80"  maxlength="200" /><br/> 
		      			 	<label id="pictureContentLabel">Upload File :</label>
                            <input id="fileContent" type="file" name="fileContent4"  style="width:340px;" onChange="javascript:uploadAttachment('attachment4');" />
                              <a  href="javascript:deleteAttachment('attachment4')"><img style="border:0px;padding-top:7px;" 
    			    		src="<%=request.getContextPath()%>/images/formImages/b_delete.png" 
    			    		title="Delete current attachment" width="80" height="30" /> </a>     
 		      			 </td></tr>
 		      			 
 		      			  <tr><td>
		      			 	<label id="pictureContentLabel">Attachment5:</label>
		      			 	<form:input path="attachment5"  size="80"  maxlength="200" /><br/> 
		      			 	<label id="pictureContentLabel">Upload File :</label>
                            <input id="fileContent" type="file" name="fileContent5"  style="width:340px;" onChange="javascript:uploadAttachment('attachment5');" />
                              <a  href="javascript:deleteAttachment('attachment5')"><img style="border:0px;padding-top:7px;" 
    			    		src="<%=request.getContextPath()%>/images/formImages/b_delete.png" 
    			    		title="Delete current attachment" width="80" height="30" /> </a>     
 		      			 </td></tr>
		      			 
		      			</table> 	
		      			   
		      			 	<a  href="javascript:reloadParentCloseSelf();"><img style="border:0px;padding-left:160px;padding-top:20px;padding-bottom:20px;" 
    			    		src="<%=request.getContextPath()%>/images/formImages/b_attach.png" 
    			    		title="Close Current Window" width="80" height="30" />    			    		
    			    	   </a>   	
    			    	 	<a  href="javascript:self.close();"><img style="border:0px;padding-left:30px;padding-top:20px;padding-bottom:20px;" 
    			    		src="<%=request.getContextPath()%>/images/formImages/b_close.png" 
    			    		title="Close Current Window" width="80" height="30" />    			    		
    			    	   </a>   
    			    	   <br>
    			    	 <br>
    			 </fieldset>  	
    			  </form:form> 
           	 	 <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/formUtils.js"> </script>  
			  	 <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/uploadAttachment.js"> </script>  
		 	 
   					
            
        
   
   
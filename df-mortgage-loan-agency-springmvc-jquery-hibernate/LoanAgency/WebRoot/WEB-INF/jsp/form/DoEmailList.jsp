<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
	 
<%
	   String imagePath =request.getContextPath()+"/images/layoutImages/";
	   
 %>
  
    <div id="cProgressSamll" style="display:none;">
	       <img style="margin-left:170px;margin-top:140px;" src="<%=imagePath%>animateCircle.gif" />
	</div>
   <form:form id="doEmailList" action="doEmailList.html" method="POST" name="emailListForm" commandName="emailListForm" enctype="multipart/form-data">
              
              <form:hidden path="actionType" />
              
              
	    	   <div id="errorBox" class="errorblock"></div>
	    	    <fieldset >  
       			     <legend><span class="AccountCreateTableHeader">Load an email list file from your local disk (txt, csv and xls file)</span> </legend>
      				 <label id="pictureContentLabel"><font color="red">*</font>Load Your Email List File:</label>
      				  <input id="fileContent" type="file" name="fileContent" size="70" maxlength="280"  /> 
 		             <a  href="javascript:uploadEmailListFile();"><img style="border:0px;" 
    			    		src="<%=request.getContextPath()%>/images/formImages/b_add.png" 
    			    		title="Next Pag" width="80" height="30" />
    			    	</a>
    			    <input type="submit" name="upload" value="upload">
		  		 </fieldset>	 
      			  <fieldset >  
       			     <legend><span class="AccountCreateTableHeader">Copy-paste email list content from opened files but keep each email address in one line </span> </legend>
      		
     	 		     <br><span style="font-weight:blod;color:blue;">format is email receiver name , email address per line or just email address per line </span>
 		      		 <br><span style="font-weight:blod;color:blue;">Good Example(Greeting Person Name plus colon plus email address): </span>
 		      		 
 		      		 <br><span style="color:blue;">Steve Johnson , steveJs@gmail.com </span>
 		             <br><span style="color:blue;">Larry Edison , LarryEd1332@uspsitcenter.org</span>
 		             <br><br><span style="font-weight:blod;color:blue;">Less Good Example(email address only): </span> 		      		 
 		      		 <br><span style="color:blue;">steveJs@gmail.com </span>
 		             <br><span style="color:blue;">LarryEd1332@uspsitcenter.org</span>
 		             
 		      	    <label id="expertise">Email List</label>
           			 
           			<form:textarea path="emailList" id="emailList"  cssClass="EmailList"/><br/> 	
           			
           				 
		        </fieldset>		 
                   
           
          
              		  <div style="padding-left:80px;margin-bottom:20px">
               		    
    			    	<a  href="javascript:doSubmitForm();"><img style="border:0px;" 
    			    		src="<%=request.getContextPath()%>/images/formImages/b_save.png" 
    			    		title="Next Pag" width="80" height="30" />
    			    	</a>
    			    	 
    			        	<a  href="javascript:self.close();"><img style="border:0px;padding-left:20px;padding-top:20px;padding-bottom:20px;" 
    			    		src="<%=request.getContextPath()%>/images/formImages/b_close.png" 
    			    		title="Close Current Window" width="80" height="30" />
    			    		
    			    	</a>   	      	   			     
					</div>   
					<br>
			 </form:form>		
					 <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/formUtils.js"> </script>  
					
					<script type="text/javascript" src="<%=request.getContextPath()%>/js/form/doEmailList.js"> </script>  

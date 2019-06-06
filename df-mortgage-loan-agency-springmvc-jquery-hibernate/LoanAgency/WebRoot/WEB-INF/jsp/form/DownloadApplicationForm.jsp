 <%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
 <%
	   String imagePath =request.getContextPath()+"/images/layoutImages/";
	  
	   String leftCapture =imagePath+ "mortgageLoanAgency_DownloadApplicationForm.png";
	  
 %>
   <script  type="text/javascript">

  function downloadApplicationForm() {
      document.getElementById("cProgressSamll").style.display="black";
  	  document.forms[0].actionType.value="downloadAppForm";
  	  document.forms[0].action="downloadApplicationForm.html";
  	  document.forms[0].submit(); 
  	  
  }
      
  
       
      
  </script>    
<div class="externalcontent">

<div class="bigbox">

<div class="module-title">
   Download Loan Application Form             
 </div>	 
<fieldset>
 <legend><span class="style13">Download Loan Application Form</span> </legend>

      
          <div id="errorBox" class="errorblock"></div> 
           <div id="cProgressSamll" style="display:none;" style="algin:left;" class="progress_dialog">      
			 <img style="margin-left:30%;margin-top:20%;" src="<%=imagePath%>animateCircle.gif" />
			</div> 
			       
             <logic:present name="successMessage" >
        	   <div id="saveSuccess" class="saveSuccess">
              	  <span style="font-weight:bold; color:GREEN; font-size:15px;"><bean:write name="successMessage"/></span>
               </div> 
             </logic:present>
        	   <form:form  id="downloadAppForm" method="POST" name="agentForm" commandName="agentForm" action="downloadApplicationForm.html">
       	       
       	   	     <input type="hidden" name="actionType" />       
               <table width="70%" border="0">
                 <tr>
                    <td>
                		 To view Application Form immediately , ensure install PDF Reader
                	</td>
                 </tr>
                 <tr>
                    <td>
                    <table>
                       <tr><td>
                		 <strong>Or Click on : &nbsp; </strong>	
                	  </td><td>	 
                	     <a href="downLoadAppForm.html">Download Application Form</a>
                   	   </td></tr>	 
   				    </table>
   				         
   				    </td>
   				  </tr>    
                </table>  
    		  </form:form>   
 		          
  			 
 </fieldset> 
 
<IFRAME id="amortFrame" src="/getApplicationFormByte.html" style="background-color:lightblue;" WIDTH=750 HEIGHT=700 frameBorder="0" >
	 
</IFRAME>
 
</div>
</div>
  	 <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/formUtils.js"> </script>  

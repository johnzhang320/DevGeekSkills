 
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ page language="java" import ="com.loan.agent.dao.RateSheet" %>
<%@ page language="java" import ="com.loan.agent.dao.Agents" %>
<%@ page language="java" import ="com.loan.agent.mvc.formbean.NicheForm" %>
<%@ page language="java" import ="com.loan.agent.services.Constant" %>
<%@ page language="java" import ="java.util.List" %>
<%@ page language="java" import ="com.loan.agent.mvc.utils.ui" %>
<%@ page language="java" import ="com.loan.agent.mvc.utils.Utility"%>

   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common/tablescroll.css" />
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/utils/tablescroll.js"> </script> 
  <%
  
	   int line=0;
  		String url = request.getRequestURL().toString();
  		String uri = request.getRequestURI().toString();
 		 
 		
   %>
 <script  type="text/javascript">
/*<![CDATA[*/

jQuery(document).ready(function($)
{
	$('#thetable2').tableScroll({height:400});
  
});

/*]]>*/
 
</script>
 <script  type="text/javascript">
 	function saveSelectNiche(nicheId) {
 		var value=document.getElementById(nicheId).value;
 		var checked = document.getElementById(nicheId).checked;
 		if (checked) {
 			checked =false;
 		} else {
 			checked =true;
 		}
 			
 		
 		
 		alert("Current NicheId="+nicheId+",previous checked="+checked);
 	}
 </script> 

<div class="module-title">
    <logic:present name="agentProfile"> 
       <bean:define id="agents" name="agentProfile" type="com.loan.agent.dao.Agents" />  
 		   
      	<bean:write name="agents"  property="firstName" />&nbsp;&nbsp;<bean:write name="agents"  property="lastName" />
      	</logic:present>'s Profile and Loan Service    	                 
 </div>	
  
 <fieldset >  
       <legend><span class="AccountCreateTableHeader">Agent Profile </span> </legend>
                <logic:present name="agentProfile"> 
                    <bean:define id="agents" name="agentProfile" type="com.loan.agent.dao.Agents" />  
 		             <table style="text-align: left; width: 310px; " border="0" cellpadding="0" cellspacing="0">
                        <tbody>
                         <tr>
                            <td>&nbsp;</td>
                             <td>
                             	 <img style="margin-center:0px; width:180px;" src="<%=request.getContextPath()%>/getLoginAgentPicture.html"> 
                             </td>
                         </tr>
                       
                           
                            <td>Email:</td>
                            <td ><label class="left"> <bean:write name="agents"  property="emailAddress" /> </label> </td>
                          </tr>
                          <tr>
                            <td >Agent:</td>
                            <td ><label class="left"> <bean:write name="agents"  property="firstName" />&nbsp;&nbsp;<bean:write name="agents"  property="lastName" /></label> </td>
                          </tr>
                          <tr>
                            <td >Company:</td>
                            <td ><label class="left"> <bean:write name="agents"  property="companyName" /></label></td>
                          </tr>
                          <tr>
                            <td >State Sales RE Lic No:</td>
                            <td ><label class="left"> <bean:write name="agents"  property="dreNo" /></label></td>
                          </tr>
                           <tr>
                            <td >NMLS No:</td>
                            <td ><label class="left"> <bean:write name="agents"  property="nmlsNo" /></label></td>
                          </tr>
                          <tr>
                            <td >License for:</td>
                            <td ><label class="left"> <bean:write name="agents"  property="licenseEligibleState" /></label></td>
                          </tr>
                          <tr>
                            <td >Work Phone:</td>
                            <td ><label class="left"> <bean:write name="agents"  property="workPhone" /></label> </td>
                          </tr>
                           <tr>
                            <td >Cell Phone:</td>
                            <td ><label class="left"> <bean:write name="agents"  property="cellPhone" /></label> </td>
                          </tr>
                          <tr>
                            <td >Address</td>
                            <td ><label class="left"> <bean:write name="agents"  property="address" />,  <bean:write name="agents"  property="city"  /> , <bean:write name="agents"  property="state"  />&nbsp;&nbsp;<bean:write name="agents"  property="zipCode"/> </label> </td>
                          </tr>
                          <logic:present name="agents" property="description">
                           <tr>                           
                            <td colspan="3">Experience and Expertise:<br>
                              <textarea Class="adExpClass" ><bean:write name="agents"  property="description" /></textarea>	 
                              </td>
                          </tr>
                          </logic:present>
                          <tr>
                        </tbody>
                        </table>        		 
                  	  
 			 </logic:present>
 			 
</fieldset>		

 
  <logic:present name ="conformingList" scope="session">
      <fieldset style="margin: 10px 10px 10px 10px;border:solid 2px lightblue">  
       <legend><span class="AccountCreateTableHeader">Conforming Rate (<=$417,000)</span> </legend>
 
   <table style="text-align: left; width: 100%; height: 46px;" border="1" cellpadding="0" cellspacing="0">
  
   <tbody>
    <tr>
      <td style="background-color: rgb(131, 208, 224);" text-align: center;>Term</td>
      <td style="background-color: rgb(131, 208, 224);" text-align: center;>Rate </td>
      <td size="15" style="text-align: center; background-color: rgb(131, 208, 224);">APR&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</td>
      <td style="background-color: rgb(131, 208, 224);" text-align: center;>Credit</td>
      <td style="background-color: rgb(131, 208, 224);" text-align: center;>LTV</td>
    </tr>
 
   <logic:iterate id="rateSheet" name ="conformingList" type="com.loan.agent.dao.RateSheet"  >
         <logic:present name="rateSheet">
    	<tr>
     	 <td ><bean:write name="rateSheet" property="term"/></td>
      	<td ><bean:write name="rateSheet" property="conformRate"/></td>
     	 <td ><bean:write name="rateSheet" property="conformApr"/></td>
     	 <td ><bean:write name="rateSheet" property="creditScore"/></td>
     	 <td ><bean:write name="rateSheet" property="loanToValue"/></td>
   	    </tr>
        </logic:present>
    </logic:iterate>
   </tbody>
 </table>
 <font color="blue">Refinance No Fee & No Point.<br>Interest rate subject to change without notice.</font>
</fieldset>
</logic:present>
 
 
<logic:present name="superConformingList" scope="session">  
 <fieldset style="margin: 10px 10px 10px 10px;border:solid 2px lightblue">  
       <legend><span class="AccountCreateTableHeader">Super Conforming Rate ($417,000~$625,000)</span> </legend>
<table style="text-align: left; width: 100%; height: 46px;" border="1" cellpadding="0" cellspacing="0">
  
  <tbody>
     <tr>
       <td style="background-color: rgb(131, 208, 224);" text-align: center;>Term</td>
       <td style="background-color: rgb(131, 208, 224);" text-align: center;>Rate </td>
       <td size="15" style="text-align: center; background-color: rgb(131, 208, 224);">APR&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</td>
       <td style="background-color: rgb(131, 208, 224);" text-align: center;>Credit</td>
       <td style="background-color: rgb(131, 208, 224);" text-align: center;>LTV</td>
     </tr>
   <logic:iterate id="rateSheet" name ="superConformingList" type="com.loan.agent.dao.RateSheet"  >
      <logic:present name="rateSheet">
    	<tr>
     	 <td ><bean:write name="rateSheet" property="term"/></td>
      	 <td ><bean:write name="rateSheet" property="superConformRate"/></td>
     	 <td ><bean:write name="rateSheet" property="superConformApr"/></td>
     	 <td ><bean:write name="rateSheet" property="creditScore"/></td>
     	 <td ><bean:write name="rateSheet" property="loanToValue"/></td>
   	    </tr>
      </logic:present>
    </logic:iterate>

 </tbody>
</table>
<font color="blue">Refinance No Fee & No Point.<br>Interest rate subject to change without notice.</font>
</fieldset>
</logic:present>
  
 <logic:present name="jumboList" scope="session">  
  <fieldset style="margin: 10px 10px 10px 10px;border:solid 2px lightblue">  
       <legend><span class="AccountCreateTableHeader">Jumbo Loan($625,500~$2M)</span> </legend>
 
<table style="text-align: left; width: 100%; height: 46px;" border="1" cellpadding="0" cellspacing="0">
  
  <tbody>
  <tr>
       <td style="background-color: rgb(131, 208, 224);" text-align: center;>Term</td>
       <td style="background-color: rgb(131, 208, 224);" text-align: center;>Rate </td>
       <td size="15" style="text-align: center; background-color: rgb(131, 208, 224);">APR&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</td>
       <td style="background-color: rgb(131, 208, 224);" text-align: center;>Credit</td>
       <td style="background-color: rgb(131, 208, 224);" text-align: center;>LTV</td>
     </tr>
    <logic:iterate id="rateSheet" name ="jumboList" type="com.loan.agent.dao.RateSheet"  >
      <logic:present name="rateSheet">
    	<tr>
     	 <td ><bean:write name="rateSheet" property="term"/></td>
      	<td ><bean:write name="rateSheet" property="jumboRate"/></td>
     	 <td ><bean:write name="rateSheet" property="jumboApr"/></td>
     	 <td ><bean:write name="rateSheet" property="creditScore"/></td>
     	 <td ><bean:write name="rateSheet" property="loanToValue"/></td>
   	    </tr>
      </logic:present>
    </logic:iterate>

  </tbody>
</table>
<font color="blue">Jumbo Rate at Par Pricing.<br>Interest rate subject to change without notice.</font>
</fieldset>
</logic:present>
 

<logic:present name="NichesList" scope="session"> 
   <fieldset style="margin: 5px 5px 5px 5px;border:solid 2px lightblue">  
       <legend><span class="AccountCreateTableHeader">Competitive or Distinctive Niche Program</span> </legend>
          <logic:present name="NichesList" scope="session" >  
            <table  id="thetable2" width="95%" border="1" cellpadding="0" cellspacing="0" >
  	    	<tbody>
  	 	       <logic:iterate id="review" name="NichesList" type="NicheForm" scope="session" indexId="rowIndex"> 
  		         <%if (line%2==0) {%>
      			    <tr class="rowwhite">    
        	     <%} else { %>
        	       <tr class="rowshade"> 
        	     <%}
        	       line++;        	    
        	      %>
     	       <td width="95%" >
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
 </fieldset>
 </logic:present> 

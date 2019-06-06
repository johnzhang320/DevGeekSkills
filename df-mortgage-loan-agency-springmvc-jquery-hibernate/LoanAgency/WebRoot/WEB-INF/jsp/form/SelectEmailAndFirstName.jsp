<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
	 
<style>
.chooseDiv { 
}	 
.chooseDiv table,th,td		   
{	 
	width:100%;
	background-color:rgb(250,254,254);		 
	border:1px solid black;	   
	border-collapse:collapse   
}	 
.chooseDiv th,td		   
{	 
	padding:6px;		   
}	 
</style> 

<logic:present name="EmailDynamicList" >
 <div class="chooseDiv">	   
	 
 <table   border="1" > 
   <caption><strong>Please Choose First Name and Email Address</strong> </caption>	  
    <thead> 
       	<tr>		   
       	  <th style="border:0px;">FirstName</th>	 
     	 
     	    <logic:iterate id="ptr" name="firstNamePtrList" type="com.loan.agent.calculators.vo.SelectedValueVo" >    
     	      <logic:equal name="ptr" property="selected"  value="false"> 
     	      		<th><input type="radio" name="firstName" value='<bean:write name="ptr" property="value" />' onclick="javascript:SelectFirstName('<bean:write name="ptr" property="value"/>')"></th>
     	      </logic:equal>
     	      <logic:equal name="ptr" property="selected"  value="true"> 
     	      		<th><input type="radio" name="firstName" checked value='<bean:write name="ptr" property="value" />' onclick="javascript:SelectFirstName('<bean:write name="ptr" property="value"/>')"></th>
     	      </logic:equal>
 		   </logic:iterate>
          </tr>
          <tr>		   
       	  <th style="border:0px;">EmailAddress</th>	 
     	      <logic:iterate id="ptr" name="emailPtrList" type="com.loan.agent.calculators.vo.SelectedValueVo" >    
     	      	<logic:equal name="ptr" property="selected"  value="false">  	     
     	      	<th><input type="radio" name="email" value='<bean:write name="ptr" property="value" />' onclick="javascript:SelectEmail('<bean:write name="ptr" property="value"/>')"></th>
     	      </logic:equal>
     	          
     	      	<logic:equal name="ptr" property="selected"  value="true">  	     
     	      	<th><input type="radio" name="email" checked value='<bean:write name="ptr" property="value" />' onclick="javascript:SelectEmail('<bean:write name="ptr" property="value" />')"></th>
     	      </logic:equal>
 		   </logic:iterate>
          </tr>
          </thead> 
          <tbody>
   		 <logic:iterate id="dyVo" name="EmailDynamicList" type="com.email.list.reader.EmailDynamicVO" scope="session" indexId="rowIndex">
              <tr>
              <td style="border:0px;">&nbsp;</td>
             	 <logic:iterate id="cList" name="dyVo" type="java.lang.String" property="colList"  >
                     
                     <td><bean:write name="cList"/> </td>
                 </logic:iterate>
            </tr>
          </logic:iterate>      
          
       </tbody>
     </table>
   </div>
 </logic:present>	 


 
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>


 
   <fieldset>
			 
	<legend>
		<span class="AccountCreateTableHeader">Reset Password	</span>
    </legend>
    <br> <font size="+1" color="blue" >Reset Email has been sent to your email address</font><br>
    
     <br>
     <logic:present name="emailAddress">
         <span style="font-color:blue;font-size:12px;">
         <bean:write name="emailAddress"/>
        </span><br>
     </logic:present>
     
 
<br>
<br>
<div style="margin-left:160px;">
	<input type="button" value="Close" class="buttonImage" onClick="javascript:self.close();">
</div>
<br><br>
</fieldset>
 
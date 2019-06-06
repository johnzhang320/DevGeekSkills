<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<style>
  
fieldset table tr td {
     border:1px;
   }
 </style> 

<div >        
	<spring:message code="title.agent.login"/>  
	<input type="button" value="Help" class="helpbutton" onclick="javascript:popUpFixedName('/SpringbootAdvancedDemo/helpAgentLogin.html','920','600');">            
</div>

<fieldset >     
	<legend><span class="AccountCreateTableHeader"> <spring:message code="title.login.success"/></span> </legend>
	<br>
	<table>   
		 
		<tr><td><spring:message code="label.public.key"/>
		</td><td>
		<textArea id="showEncryptedString" rows="8" style="width:500px;" > ${agentTable.publicKey}</textArea>                                                         
		</td></tr>
	</table>
	<br>
	<table>   
	 
		<tr><td><spring:message code="label.encrypted.password"/>
		</td><td>
		<textArea id="showEncryptedString" rows="5" style="width:500px;" > ${agentTable.encryptedPassword} </textArea>                                                         
		</td></tr>
		<tr><td><spring:message code="label.decrypted.password"/>
	    </td><td>
		<textArea id="showEncryptedString" rows="2" style="width:500px;" > ${agentTable.decryptedPassword}</textArea>    
		</td></tr>
		<tr><td><spring:message code="label.hashed.password"/></td><td>	<textArea id="showEncryptedString" rows="2" style="width:500px;"> ${agentTable.hashedPassword}</textArea>
	    </td></tr>
	</table>	
	<br>
	<table>   
 		<tr><td>Encrypted Credit Card Number
		</td><td>
		<textArea id="showEncryptedString" rows="5" style="width:500px;" > ${agentTable.encryptedCredit} </textArea>                                                         
		</td></tr>
		<tr><td>Decrypted Credit Card Number
	    </td><td>
		<textArea id="showEncryptedString" rows="2" style="width:500px;" > ${agentTable.decryptedCredit}</textArea>    
	 	</td></tr>
	 	
	</table>	
	<br>
	<table>   
		
		<tr><td>Encrypted Secial Security Number
		</td><td>
		<textArea id="showEncryptedString" rows="5" style="width:500px;" > ${agentTable.encryptedSSO} </textArea>                                                         
		</td></tr>
		<tr><td>Decrypted Secial Security Number
	    </td><td>
		<textArea id="showEncryptedString" rows="2" style="width:500px;" > ${agentTable.decryptedSSO}</textArea>    
	    </td></tr>
	</table>	
	
</fieldset>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<html>
<header>

 
<script type="text/javascript" >

	function createonebulk() {
		var form = document.getElementById("certGenerateForm");
 	 	form.method = "POST";
	 	document.getElementById("promptDiv").style.display = "block";
 			 
	 	form.action = "createonebulk.html";
	 		 
		form.submit();	
 }
 
</script>
 
</header>

<body>
  
    <table>
    	  <tr>
    	     <td> 
		    <a href="<%=request.getContextPath()%>/cert_mgr_renew_all.html" title="cert.mgr.renew.all" style="height:15px;line-height:15px;">
		    <span class="textStyle"><spring:message code="cert.mgr.renew.all"/></span></a>
		</td>  
        <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
    	    <td>
		    <a href="<%=request.getContextPath()%>/renewone.html" title="cert.mgr.renew.one" style="height:15px;line-height:15px;">
		    <span class="textStyle"><spring:message code="cert.mgr.renew.one"/></span></a>
        </td>
    
	   </tr>
    </table>   
    <br>    
 <form:form method="POST" name="certGenerateForm" id="certGenerateForm" action="/createonebulk.html" modelAttribute="certGenerateForm">
  
   
 <fieldset>
	<legend>
		<span class="title"><spring:message code="bulk.api.create.one.title"/></span>			 
	</legend>
 

    <div>
        <br><form:label path="env_path_file" ></b><span class="title"><spring:message code="cert.env.path.file"/>:</span></form:label><br>
        <form:input path="env_path_file" styleId="env_path_file"  size="103" maxlength="110" cssClass="box" placeholder="/applepay_qa_environment/keystores/applepay_qa1/yourkeystore.jks"/>
        <form:errors path="env_path_file" cssClass="error"/>
        <br><span class="grey"><spring:message code="cert.env.keystore.path"/></span>
    </div>
    <div>
    	  <table>
    	  <tr>
    	     <td>
	        <form:label path="storePass" ><span class="title"><spring:message code="cert.generate.ipaddress"/>:</span></form:label>
	     </td>  
	     <td>
	       <form:input path="ipAddress" styleId="ipAddress"  size="60" maxlength="70"  cssClass="box" />
	       <form:errors path="ipAddress" cssClass="error"/>
	     </td>
	   </tr>  
   	  <tr>
    	     <td>
 	       <form:label path="storePass" ><span class="title"><spring:message code="cert.generate.store.password"/>:</form:label>
	      </td>  
	     <td>
	       <form:password path="storePass" styleId="storePass"  size="40" maxlength="70" cssClass="box"/>
	       <form:errors path="storePass" cssClass="error"/>
	     </td>
	   </tr>  
	     <tr>
    	     <td>
	       <form:label path="alias" ><span class="title"><spring:message code="cert.generate.owner.alias"/>:</span></form:label>
	      </td>  
	     <td>
	       <form:input path="alias" styleId="alias"  size="40" maxlength="70" cssClass="box"/>
	         <form:errors path="alias" cssClass="error"/>
         </td>
         </tr>
        </table>
    </div>
     <div>
      <form:label path="authToken"><span class="title"><spring:message code="cert.generate.auth.token"/></span></form:label><br>
	  <form:textarea path="authToken" styleId="authToken" rows="5" cols="110" cssClass="textArea" />	
	   <form:errors path="authToken" cssClass="error"/>
    </div>
     <div id="promptDiv" style="display:none">
        <br><span id="promptDiv" class="errorblock">Please Waiting......</span>
    </div>
   <div>
	  <br><form:label path="cert_result"><span class="title"><spring:message code="cert.result"/></span></form:label><br>
	  <form:textarea path="cert_result" styleId="cert_result" rows="12" cols="110" cssClass="textArea" />	
	  
	</div>	   
 
    <div>
     <input type="button" value="Generate" class="buttonImage" onclick="javascript:createonebulk();"> 
    <!--  <input type="submit" value="Generate" class="buttonImage" > -->
   </div>
	
</fieldset>
</form:form>
  
</body>
</html>



 


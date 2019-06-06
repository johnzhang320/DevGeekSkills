<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic"%>
<%
 	 String contextPath =request.getContextPath();
%>
<style>
  
	 table tr td th {
	       border:0px;color:white;
	  	 }
	  	 
	}
</style> 

<script src="<%=contextPath%>/js/oauth2/myOauth2App.js"></script>
	
<div class="module-title" >
	<spring:message code="title.oauth2.demo"/>  
	<input type="button" value="Help" class="helpbutton" onclick="javascript:popUpFixedName('/Spring4AngularHibernateTile3RestCassandra/helpAgentSingup.html','750','600');">            
</div>

<div ng-app="MyOauth2Application">
 <div ng-controller="Oauth2AppController">
     <fieldset>
        <legend><span>The OAuth 2.0 Authorization Demo</span></legend>
        <table>
			<tr><td><label style="margin-left: -100px;">Authorization Server URI:</label></td><td>{{autho_server_uri}} </td></tr>
			<tr><td><label style="margin-left: -100px;">Trusted Client Username:</label></td><td>{{trustedClientId}}</td></tr> 
			<tr><td><label style="margin-left: -100px;">Trusted Client password:</label></td><td>{{trustedClientSecret}}</td></tr> 
			<tr><td><button ng-click="verifyRequest()">Verify then Request</button></td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>  
   		</table>
    </fieldset>  
     <fieldset>
        <legend><span>Getting Tokens from Authorization Server</span></legend>
        <table>
	        <tr><td><tr><td><label style="margin-left: -100px;">Authorization Server URL:</label></td><td>{{autho_server_url}}</td></tr>
	        <tr><td><tr><td><label style="margin-left: -100px;">Authorization:</label></td><td>Basic={{credential_display}}</td></tr>        
	        <tr><td><tr><td><label style="margin-left: -100px;">Content-type:</label></td><td>{{content_type_display}}</td></tr> 
	       	<tr><td><button ng-click="sendRequest()">Getting Tokens</button></td><td><button ng-click="clearRequest()">Clear this Request</button></td></tr>        		
    	</table>
     </fieldset>	
     <fieldset>
        <legend><span>The Tokens from Authorization Server</span></legend>
       <table>
	  		<tr><td><label style="margin-left: -100px;">Resource Server URL:</label></td><td>{{resource_server_url}}</td></tr>
	        <tr><td><label style="margin-left: -100px;">Access Token:</label></td><td>{{access_token}}</td></tr>
	        <tr><td><label style="margin-left: -100px;">Refresh Token:</label></td><td>{{refresh_token}}</td></tr>        
	        <tr><td><label style="margin-left: -100px;">Token Type:</label></td><td>{{token_type}}</td></tr>         
	        <tr><td><label style="margin-left: -100px;">Expires In:</label></td><td>{{expires_in}}(ms)</td></tr> 
	       	<tr><td><button ng-click="fetchAllUsers()">Getting Data from Resource Server</button></td><td><button ng-click="clearTokens()">Clear Tokens</button></td></tr> </td></tr>       		
        </table>
     </fieldset>	
     
     <fieldset>
        <legend><span>The User Data from Resource Server</span></legend>
        <table>
	  		<tr><td><label style="margin-left: -100px;">Resource Server URL:</label></td><td>{{resource_server_url}}</td></tr>
	        <tr><td><label style="margin-left: -100px;">Retrived Result:</label></td><td>{{result_display}}</td></tr>
	        
	      	 <tr><td><button ng-click="clearResult()">Clear this Result</button></td><td> &nbsp;&nbsp;&nbsp;&nbsp;</td></tr>    
       	 </table>   		
     </fieldset>
     <fieldset>
        <legend><span>Getting New Tokens by Current Refresh Token</span></legend>
        <table>
	        <tr><td><label style="margin-left: -100px;">Authorization Server URL:</label></td><td>{{refreshUrl}}</td></tr>
	            
	        <tr><td><label style="margin-left: -100px;">New Access Token:</label></td><td>{{access_token}}</td></tr>
	        <tr><td><label style="margin-left: -100px;">New Refresh Token:</label></td><td>{{refresh_token}}</td></tr>   
	        <tr><td><label style="margin-left: -100px;">Authorization:</label></td><td>Basic={{credential_display}}</td></tr>        
	        <tr><td><label style="margin-left: -100px;">Content-type:</label></td><td>{{content_type_display}}</td></tr> 
	       	<tr><td><button ng-click="getRefreshedAccessToken()">Getting New Tokens</button></td><td><button ng-click="clearRequest()">Clear this Request</button></td></tr> </td></tr>       		
	    </table>
     </fieldset>		
  </div>  
</div> 
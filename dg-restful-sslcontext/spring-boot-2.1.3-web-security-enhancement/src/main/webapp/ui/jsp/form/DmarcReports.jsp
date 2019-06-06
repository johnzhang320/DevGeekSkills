<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
 
 <style>
  
	 fieldset table tr td th {
	       border:1px;
	  	 }
	  	 .custom-width {
	    	width: 80px !important;
	     }
	}
</style> 
 
<div> 
	<spring:message code="title.agent.login"/>  
	<input type="button" value="Help" class="helpbutton" onclick="javascript:popUpFixedName('/SpringbootAdvancedDemo/helpAgentLogin.html','920','600');">            
</div>
<div ng-app="dmarcModule">
	<fieldset>  
  
	<legend><span class="AccountCreateTableHeader"> <spring:message code="title.login.success"/></span> </legend>
	<br>
	  <table ng-controller="DmarcController as ctrl">   
	    <thead>
		   	<th> master_id           </th>  
			<th> org_name            </th> 
			<th> emailAddress        </th> 
			<th> report_id           </th> 
			<th> begin_time          </th> 
			<th> end_time            </th> 
			<th> domain              </th> 
			<th> transaction_done    </th> 
			<th> host_name           </th> 
			<th> policy_string	 	 </th> 
		 </thead>
		 <tbody>
      	    <tr ng-repeat="m in ctrl.masters">
		        <td><span ng-bind="m.master_id"/></span></td> 
				<td><span ng-bind="m.org_name"/></span></td>
				<td><span ng-bind="m.emailAddress"/></span></td>
				<td><span ng-bind="m.report_id"/> </span></td>
				<td><span ng-bind="m.begin_time"/></span></td>
				<td><span ng-bind="m.end_time" /> </span></td>
				<td><span ng-bind="m.domain"/></span></td>
				<td><span ng-bind="m.transaction_done"/></span></td>
				<td><span ng-bind="m.host_name"/></span></td>
				<td><span ng-bind="m.policy_string"/></span></td> 
            </tr>
      	</tbody>
  	   </table>	
    </fieldset>
</div>
<script type="text/javascript" src="js/angular/controller/dmarc_controller.js"></script>
  
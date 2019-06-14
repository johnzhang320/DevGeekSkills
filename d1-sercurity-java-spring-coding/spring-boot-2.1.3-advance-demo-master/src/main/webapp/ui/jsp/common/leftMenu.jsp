<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<script type="text/javascript">
 function popoutWindow(url,title,w,h) {
	window.open(url, title, "toolbar=yes,scrollbars=yes,resizable=yes,top=10,left=100"+",width=" + w + ",height="+ h);
 }
 
</script>
 
    <div class="module-title" >Menu</div>
 <fieldset>
  <!-- 
    
		title.agent.login = Frontend Encryption(FE)
		
		title.spring.security.demo=Spring Security DataSource/PasswordFE
		
		title.oauth2.demo = Spring Oauth2 Code/Password/SSO/FE
		
		title.oauth2.password.flow =Spring/Angular Oauth2/Password/Flow 
		
		title.agent.signup = Spring MVC Locale Validation
		
		title.oauth2.sso=Okta SSO Login
		
		title.oauth2.google.facebook=Google Facebook SSO
		
		title.gather.truststore=Gather Truststore
		
		title.dmarc.reports = AngularJS Grid Hibernate 
		
		title.dmarc.Cassandra.reports = AngularJS Grid Cassandra 
		
		title.dmarc.Mongodb.reports = AngularJS Grid Mongodb 
     -->
    
    <p>	 
	    <a href="<%=request.getContextPath()%>" title="Loan Agent login" style="height:15px;line-height:15px;">
	    <spring:message code="title.agent.login"></spring:message></a>
   </p>
   <p> 
	   <a href="javascript:popoutWindow('http://localhost:8091/home','Spring Security Demo',1050,650);" title="title.spring.security.demo" style="height:15px;line-height:15px;">
	    <spring:message code="title.spring.security.demo"></spring:message></a>
   </p>        
    <p>
	    <a href="http://localhost:8081/oauth_master/home" title="title.oauth2.demo" style="height:15px;line-height:15px;">
	    <span><spring:message code="title.oauth2.demo"></spring:message></span></a>
    </p>
    
   <p>
	    <a href="#" title="title.oauth2.password.flow" style="height:15px;line-height:15px;">
	    <span><spring:message code="title.oauth2.password.flow"></spring:message></span></a>
    </p>
    
    <p>
	    <a href="http://localhost:8092/SpringbootOAuth2OktaSSO/bankaccount" title="Okta SSO Login" style="height:15px;line-height:15px;">
	    <span><spring:message code="title.oauth2.sso"></spring:message></span></a>
    </p>  
    
  
     <p>
	    <a href="#" title="Google Facebook sso" style="height:15px;line-height:15px;">
	    <span><spring:message code="title.oauth2.google.facebook"></spring:message></span></a>
    </p>  
    
     <p>
	    <a href="#" title="Gather SSL Server Certificate" style="height:15px;line-height:15px;">
	    <span><spring:message code="title.gather.truststore"></spring:message></span></a>
    </p>   
    <p>
	    <a href="http://localhost:8080/SpringbootAdvancedDemo/agentSignup.html" title="Spring MVC Validation" style="height:15px;line-height:15px;">
	    <span><spring:message code="title.agent.signup"></spring:message></span></a>
    </p>
     <p>  
	    <a href="http://localhost:8080/SpringbootAdvancedDemo/masterDetailRow.html" title="AngularJS Grid Hibernate" style="height:15px;line-height:15px;">
	    <span><spring:message code="title.dmarc.reports"></spring:message></span></a>
    </p>
    
      <p>  
	    <a href="#" title="AngularJS Grid Cassandra" style="height:15px;line-height:15px;">
	    <span><spring:message code="title.dmarc.Cassandra.reports"></spring:message></span></a>
    </p>
    
     <p>  
	    <a href="#" title="AngularJS Grid Mongodb" style="height:15px;line-height:15px;">
	    <span><spring:message code="title.dmarc.Mongodb.reports"></spring:message></span></a>
    </p>
       
 
   
</fieldset>

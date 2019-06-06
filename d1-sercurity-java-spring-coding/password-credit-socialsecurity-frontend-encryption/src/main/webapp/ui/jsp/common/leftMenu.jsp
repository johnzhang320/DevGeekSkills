<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
<script type="text/javascript">
 function popoutWindow(url,title,w,h) {
	window.open(url, title, "toolbar=yes,scrollbars=yes,resizable=yes,top=10,left=100"+",width=" + w + ",height="+ h);
 }
 
</script>
 
    <div class="module-title" >Menu</div>
 <fieldset>

    <p>	 
	    <a href="<%=request.getContextPath()%>" title="Loan Agent login" style="height:15px;line-height:15px;">
	    <spring:message code="title.agent.login"></spring:message></a>
   </p>
   <p> 
	   <a href="javascript:popoutWindow('http://localhost:8091/home','Spring Security Demo',1050,650);" title="title.spring.security.demo" style="height:15px;line-height:15px;">
	    <spring:message code="title.spring.security.demo"></spring:message></a>
   </p>        
    <p>
	    <a href="http://localhost:8080/oauth_master/home" title="title.oauth2.demo" style="height:15px;line-height:15px;">
	    <span><spring:message code="title.oauth2.demo"></spring:message></span></a>
    </p>
<!--     <p>
     
	    <a href="javascript:popoutWindow('http://localhost:8080/SpringbootAdvancedDemo/helpOktaSSO.html','Spring Security Demo',1050,950);" title="title.oauth2.sso" style="height:15px;line-height:15px;">
	    <span><spring:message code="title.oauth2.sso"></spring:message></span></a>
    </p>
    -->
     <p>  
	    <a href="http://localhost:8080/oauth_master/homeWebAdvancedApp" title="Loan Agent sign up" style="height:15px;line-height:15px;">
	    <span><spring:message code="title.agent.signup"></spring:message></span></a>
    </p>
       
    <p>
     
	    <a href="" title="title.spark.application" style="height:15px;line-height:15px;">
	    <span><spring:message code="title.spark.application"></spring:message></span></a>
    </p>
 
       

 <!--      
    <p>
	    <a href="<%=request.getContextPath()%>/masterDetailRow.html" title="title.dmarc.reports" style="height:15px;line-height:15px;">
	    <span><spring:message code="title.dmarc.reports"></spring:message></span></a>
    </p> -->
</fieldset>

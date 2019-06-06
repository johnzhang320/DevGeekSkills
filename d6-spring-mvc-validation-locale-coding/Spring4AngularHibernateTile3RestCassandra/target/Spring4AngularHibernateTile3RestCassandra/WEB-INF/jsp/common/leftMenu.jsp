<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
    <div class="module-title" >Left Menu</div>
	<p>
	    <a href="<%=request.getContextPath()%>" title="Loan Agent login" style="height:15px;line-height:15px;">
	    <span><spring:message code="title.agent.login"></spring:message></span></a>
    </p>
         
    <p>
	    <a href="<%=request.getContextPath()%>/oauth2.html" title="title.oauth2.demo" style="height:15px;line-height:15px;">
	    <span><spring:message code="title.oauth2.demo"></spring:message></span></a>
    </p>
   
    <p>  
	    <a href="<%=request.getContextPath()%>/agentSignup.html" title="Loan Agent sign up" style="height:15px;line-height:15px;">
	    <span><spring:message code="title.agent.signup"></spring:message></span></a>
    </p>
       
    <p>
	    <a href="<%=request.getContextPath()%>/masterDetailRow.html" title="title.dmarc.reports" style="height:15px;line-height:15px;">
	    <span><spring:message code="title.dmarc.reports"></spring:message></span></a>
    </p>
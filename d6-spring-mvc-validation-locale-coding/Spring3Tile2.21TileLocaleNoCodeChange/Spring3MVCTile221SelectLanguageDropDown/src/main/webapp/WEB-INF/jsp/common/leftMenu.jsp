<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> 
        <div class="module-title" >Left Menu</div>
  		<p>
		    <a href="" title="Loan Agent login" style="height:15px;line-height:15px;">
		    <span><spring:message code="title.agent.login"></spring:message></span></a>
        </p>
        <p>  
  		    <a href="<%=request.getContextPath()%>/agentSignup.html" title="Loan Agent sign up" style="height:15px;line-height:15px;">
  		    <span><spring:message code="title.agent.signup"></spring:message></span></a>
        </p>
      
  
 
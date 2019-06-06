<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
  <head>
      <meta charset="utf-8">
      <title>Get Authorization Code</title>

      <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
      <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
  </head>

  <body>
	
		
    <div class="d-flex justify-content-center align-items-center container">

        <form:form method="POST" action="${authorize_uri}" modelAttribute="authCodeForm">
           <div class="form-group row">
           		<h2 class="form-signin-heading">Demo Authorization Code Flow</h2>
           		
           </div>
           <div class="form-group row">
	            <Label for="response_type"  class="text-info col-sm-2 col-form-label">Response Type</Label> 
	            <spring:bind path="response_type">
	                <div class="form-group ${status.error ? 'has-error' : ''} col-sm-4">
	                    <form:input type="text" path="response_type" class="form-control" autofocus="true"></form:input>
	                    <form:errors path="response_type"></form:errors>
	                </div>
	            </spring:bind>
		   </div>
		  <div class="form-group row">
			  <Label for="client_id"  class="text-info col-sm-2 col-form-label">Client Id</Label> 
	            <spring:bind path="client_id">
	                <div class="form-group ${status.error ? 'has-error' : ''} col-sm-4">
	                       <form:input type="client_id" path="client_id" class="form-control" placeholder="client_id"></form:input>
	                    <form:errors path="client_id"></form:errors>
	                </div>
	            </spring:bind>
          </div>
           <div class="form-group row">
			  <Label for="scope"  class="text-info col-sm-2 col-form-label">Scope</Label> 
				<spring:bind path="scope">
	                <div class="form-group ${status.error ? 'has-error' : ''} col-sm-4">
	                	 
	                    <form:input type="text" path="scope" class="form-control"
	                                placeholder="scope"></form:input>
	                    <form:errors path="scope"></form:errors>
	                </div>
	            </spring:bind>
            </div>
             <div class="form-group row">
			  <Label for="redirect_uri"  class="text-info col-sm-2 col-form-label">Redirect Url</Label> 
	            <spring:bind path="redirect_uri">
	                <div class="form-group ${status.error ? 'has-error' : ''} col-sm-6">
	                 
 	                    <form:input type="text" path="redirect_uri" class="form-control"
	                                placeholder="redirect_uri"></form:input>
	                    <form:errors path="redirect_uri"></form:errors>
	                </div>
	            </spring:bind>
  			</div>
  			
  			 <div class="form-group row">
			  <Label for="resource_uri"  class="text-info col-sm-2 col-form-label">Resource Url</Label> 
	            <spring:bind path="resource_uri">
	                <div class="form-group ${status.error ? 'has-error' : ''} col-sm-6">
	                 
 	                    <form:input type="text" path="resource_uri" class="form-control"
	                                placeholder="resource_uri"></form:input>
	                    <form:errors path="resource_uri"></form:errors>
	                </div>
	            </spring:bind>
  			</div>
  			
  			 <div class="form-group row">
			  <Label for="authorize_uri"  class="text-info col-sm-2 col-form-label">Authorize Url</Label> 
	            <spring:bind path="authorize_uri">
	                <div class="form-group ${status.error ? 'has-error' : ''} col-sm-6">
	                 
 	                    <form:input type="text" path="authorize_uri" class="form-control"
	                                placeholder="authorize_uri"></form:input>
	                    <form:errors path="authorize_uri"></form:errors>
	                </div>
	            </spring:bind>
  			</div>
  			
  			 <div class="form-group ${status.error ? 'has-error' : ''} col-sm-4">
           		 <button class="btn btn-primary btn-block btn-left col-md-4" type="submit">Submit</button>
	 	    </div>	
	 	   
        </form:form>
	    	   
	 </div>
     <div class="container">
            <H4 class="text-center text-info text-lg"> Authorization Code Flow</H4>
           
            <img   class="center-block" src="${contextPath}/resources/images/Demo_OAuth2_Authorization_Code_Flow.jpg" width="90%" height="90%">
     </div>
	    
  
  </body>
</html>

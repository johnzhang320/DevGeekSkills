
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

 <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
   <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
   <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
<br/> 
  <div class="container">
      <h2 class="form-heading text-Left text-info">Okta Single Sign On (SSO) Demo</h2>
       <div class="form-group"> 
         <h4 class="text-left text-info">Access 'Dashboard' page via sign on a login page redirected by Okta</h4>
	    <a href="http://localhost:8092/SpringbootOAuth2OktaSSO/dashboard" title="title.oauth2.sso" style="height:15px;line-height:15px;">
	    <h4 class="text-left text-primary text-lg">http://localhost:8092/SpringbootOAuth2OktaSSO/dashboard</h4></a>
     </div>
    <div class="form-group"> 
         <h4 class="text-left text-info">If already sign on 'Dashboard', directly access below page</h4>
	    <a href="http://localhost:8092/SpringbootOAuth2OktaSSO/bankaccount" title="title.oauth2.sso" style="height:15px;line-height:15px;">
	     <h4 class="text-left text-primary text-lg">http://localhost:8092/SpringbootOAuth2OktaSSO/bankaccount</h4></a>
     </div>
    <div class="form-group"> 
     <h4 class="text-left text-info">Single Sign On (SSO) is nothing but sign on one time and access more without signing again.</h4>
   
    </div>
    </div>
    <div class="container">
       <H4 class="text-center text-primary text-lg">Okta Single Sign On (SSO) Flow</H4>
      
       <img   class="Left-block" src="${contextPath}/ui/static/images/OktaSSOSequence.jpg" width="80%" height="80%">
    </div>

	 
     
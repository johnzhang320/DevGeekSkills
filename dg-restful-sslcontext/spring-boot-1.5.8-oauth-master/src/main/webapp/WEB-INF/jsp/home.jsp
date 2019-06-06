
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
  <head>
      <meta charset="utf-8">
      <title>Security Demo </title>

      <link href="css/bootstrap.min.css" rel="stylesheet">
      <link href="css/common.css" rel="stylesheet">
  </head>

  <body>


<div class="container">

    <div class="starter-template center-block">
         <h2 class="text-info text-center">Spring Oauth2 Demo</h2>
         <h3 class="text-info">1.  Spring boot, Oauth2 Authorization Code Flow , Server's Login Page Encrypting Password</h3>
        <h4>  .  <a href="http://localhost:8080/oauth_client/getDashboard">http://localhost:8080/oauth_client/getDashboard</a></h4>
	    <h4>  .  <a href="http://localhost:8080/oauth_client/getBankAccount">http://localhost:8080/oauth_client/getBankAccount</a></h4>
         
        <h3  class="text-info">2.  Spring4 Oauth2 Password Flow </h3>
         <h4>  .  <a href="javascript:popoutWindow('http://localhost:8080/SpringbootAdvancedDemo/oauth2.html','Spring Oauth2 Passowrd Flow Demo',1050,950);"  style="height:15px;line-height:15px;">
	    http://localhost:8080/SpringbootAdvancedDemo/oauth2.html</a></h4>
     
 	   
   <!--       <h3 class="text-info">3. Single Sign On(SSO) Application Authorized by Spring boot OAuth2 Server</h3>-->
        <h3 class="text-info">3. Single Sign On(SSO) Application Authorized by Okta Service</h3>
        <h4>  .  <a href="http://localhost:8092/SpringbootOAuth2OktaSSO/dashboard">http://localhost:8092/SpringbootOAuth2OktaSSO/dashboard</a></h4>
	    <h4>  .  <a href="http://localhost:8092/SpringbootOAuth2OktaSSO/bankaccount">http://localhost:8092/SpringbootOAuth2OktaSSO/bankaccount</a></h4>
	
        
        <h3 class="text-info">4. Single Sign On(SSO) Application Authorized by Social Media Accounts(Google / Facebook)</h3>
        
    </div>

</div>
    <div class="container">
         <a href="http://localhost:8080/SpringbootAdvancedDemo" class="btn btn-primary">Home Page</a>
    
        
    </div>
</body>
</html>
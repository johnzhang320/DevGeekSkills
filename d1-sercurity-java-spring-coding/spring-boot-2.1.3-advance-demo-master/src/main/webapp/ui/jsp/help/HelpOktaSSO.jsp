
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
  <head>
      <meta charset="utf-8">
      <title>Security Demo </title>

      <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
      <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
      
      <style>
      /* unvisited link */
		a:link {
		  color: blue;
		}
		
		/* visited link */
		a:visited {
		  color: green;
		}
		
		/* mouse over link */
		a:hover {
		  color: hotpink;
		}
		
		/* selected link */
		a:active {
		  color: blue;
		  text-decoration:underline;
		}
      </style>
  </head>

  <body>


<div class="container">

 
    <fieldset>
       <legend><h3> Okta Single Sign On (SSO) Demo</h3></legend> 
     
       
          <h4 class="text-left text-info">Single Sign On (SSO) is nothing but sign on one time and access more without signing again.</h4>
          <h4 class="text-left text-info">Before play this demo, please clean Cookie of your browser</h4><br/>
          <h4 class="text-left text-info">Access 'Dashboard' page via sign on a login page which is redirected by Okta, click below link:</h4>
 	      <a  href="http://localhost:8092/SpringbootOAuth2OktaSSO/dashboard" title="title.oauth2.sso" style="height:15px;line-height:15px;">
	      <h4 class="text-left text-primary text-lg">http://localhost:8092/SpringbootOAuth2OktaSSO/dashboard</h4></a>
    	 <br/>
          <h4 class="text-left text-info">If you already sign on 'Dashboard', you can directly access below page, click below link:</h4>
	      <a  href="http://localhost:8092/SpringbootOAuth2OktaSSO/bankaccount" title="title.oauth2.sso" style="height:15px;line-height:15px;">
	      <h4 class="text-left text-primary text-lg">http://localhost:8092/SpringbootOAuth2OktaSSO/bankaccount</h4></a>
	      <br/>
     
      
       <H4 class="text-center text-primary text-lg" style="color:black">Okta Single Sign On (SSO) Flow</H4>
     
       <img src="${contextPath}/ui/static/images/OktaSSOSequence.jpg" style="max-width:90%;max-height:90%">   
    
       <br/><br/>
         <H4 class="text-center text-primary text-lg" style="color:black">Register Application of http://localhost:8092/SpringbootOAuth2OktaSSO/dashboard</H4>
    
        <img src="${contextPath}/ui/static/images/OktaRegisteredApp.jpg" style="max-width:90%;max-height:90%">   
  </fieldset>   

</div>
</body>
</html>
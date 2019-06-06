
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<html lang="en">
  <head>
      <meta charset="utf-8">
      <title>Log in with your account</title>

      <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
      <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
     <!-- Front End Encryption library -->
		<script type="text/javascript" src="${contextPath}/resources/js/lib/jquery-1.8.0.js"></script>
		<script type="text/javascript" src="${contextPath}/resources/js/lib/jquery.jcryption-1.1.js"></script> 
		
	<!-- Front End Encryption Customized Code -->
		<script type="text/javascript"	src="${contextPath}/resources/js/utils/stringCryption.js">		
		</script>
		<script type="text/javascript"> 
			$(document).ready(function(){
			
				stringCryption.getPublicKey("/getKeyPair.html")
			 	stringCryption.initialize("password"); 		  
			
			}); 
		</script>  
 
  </head>

  <body>
 

    <div class="container">
      <form method="POST" action="${contextPath}/login" class="form-signin">
        <h2 class="form-heading">Log In SSO Server</h2>

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>
            <input name="username" type="text" class="form-control" placeholder="Username"
                   autofocus="true"/>
            <input name="password" id="password" type="password" class="form-control" placeholder="Password"/>
            <span>${error}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			 <!--<input type="button" value="Log In" class="btn btn-lg btn-primary btn-block" onClick="javascript:loginSubmitForm();"> -->
            <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button> 
           
        </div>
            <div class="form-group">
            	<label class="text-center text-info ">This login page is provided by Oauth2 Server</label>
          	</div>	
          	 <div class="form-group">
            	<label class="text-center text-info ">Encrypting your password once cursor leaves password field</label>
          	</div>	
        
      </form>
    </div>
  
 
  </body>
</html>



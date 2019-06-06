
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
  <head>
      <meta charset="utf-8">
      <title>Log in with your account</title>

      <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
      <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
      <!-- Front End Encryption library -->
	<script type="text/javascript" src="${contextPath}/ui/static/js/lib/jquery-1.8.0.js"></script>
	<script type="text/javascript" src="${contextPath}/ui/static/js/lib/jquery.jcryption-1.1.js"></script> 
	
	<!-- Front End Encryption Customized Code -->
	<script type="text/javascript"	src="${contextPath}/ui/static/js/utils/stringCryption.js">		
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
        <h2 class="form-heading">Log In</h2>

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span>${message}</span>
            <input name="username" type="text" class="form-control" placeholder="Username"
                   autofocus="true"/>
            <input name="password" id="password" type="password" class="form-control" placeholder="Password"/>
            <span>${error}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			 <!--<input type="button" value="Log In" class="btn btn-lg btn-primary btn-block" onClick="javascript:loginSubmitForm();"> -->
            <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button> 
            <h4 class="text-center"><a href="${contextPath}/registration">Create an account</a></h4>
        </div>
        <br/>
         <div class="form-group">
            <label class="text-center text-info "> Encrypt your password before page submitted !</label>
          </div>
         
         <br/>
        
      </form>
    </div>
     <div class="container">
            <H4 class="text-center text-info text-lg"> Front End Public Key Encryption (FEPKE)</H4>
           
            <img   class="center-block" src="${contextPath}/ui/static/images/FrontEndPublicKeyEncryption.jpg" width="90%" height="90%">
     </div>

 
  </body>
</html>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
  <head>
      <meta charset="utf-8">
      <title>Security Demo </title>

      <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
      <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
  </head>

  <body>


<div class="container">

    <div class="starter-template center-block">
         <h3 class="text-info">Spring Boot Security Enhancement and Frontend Encryption Demo</h3>
        <h3>1. <a href="${contextPath}/safebox/dashboard.html">Dashboard page (Need USER Role)</a></h3>
        <h3>2. <a href="${contextPath}/safebox/getEmployeesList">Employee List JSON Data  (Need USER Role)</a></h3>
        <h3>3. <a href="${contextPath}/safebox/bankaccount.html">User Bank Account page  (Need SUPERUSER Role)</a></h3>
        <h3>4. <a href="${contextPath}/safebox/assign_roles.html">Assign Roles to User  (Need ADMIN Role)</a></h3>
        <h3>5. <a href="${contextPath}/registration">User Registration page  (NO Need Authentication)</a></h3>
        <h3>6. <a href="${contextPath}/login">Log In  (NO Need Authentication)</a></h3>    
    </div>

</div>
   <div class="container">

        <footer>
        <!-- this is footer -->
        &copy; Spring Security Demo
          <sec:authorize access="isAuthenticated()">
                | Logged user: <b><sec:authentication property="principal" /></b> |
                Roles: <b><sec:authentication property="authorities"/></b> |
                <a href="${contextPath}/logout">Sign Out</a> <!--  |
                <a href="http://localhost:8080/SpringbootAdvancedDemo">Main Page </a> -->
          </sec:authorize>.

        <script type="text/javascript"
                src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        </footer>
    </div>
    <br/>

    <div class="container">
         <a href="http://localhost:8080/SpringbootAdvancedDemo" class="btn btn-primary">Home Page</a>
    
        
    </div>
 <div class="container">
            <h3 class="text-center text-info text-lg">Spring Boot Security Enhancement Flow</h3>
           
            <img   class="center-block" src="${contextPath}/ui/static/images/SpringbootSecurityEnhancement.jpg" width="100%" height="100%">
     </div>
</body>
</html>
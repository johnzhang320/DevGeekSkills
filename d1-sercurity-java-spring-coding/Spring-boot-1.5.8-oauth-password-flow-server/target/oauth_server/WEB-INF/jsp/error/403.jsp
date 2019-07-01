
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
  </head>

  <body>




<div class="container">

    <div class="starter-template">
        <h1>403 - Access is denied</h1>
        <div >Hello  <b><sec:authentication property="principal" /></b>, you do not have permission to access this page.</div>
    </div>

</div>
 <br/>
<div class="container">
 <a href="${contextPath}/home"><b>Home Page</b> </a>
 </div>


</body>
</html>
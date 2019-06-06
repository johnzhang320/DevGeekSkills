
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
    .textline {
  		content: "\A";
  		white-space: pre;
  		color:black;
  		font-size:16px;
    }
   
</style>
  
  </head>

  <body>


<div class="container">

 
    <fieldset>
        <legend><span style="color:white;"> Front End Public Key Encryption (FEPKE)</span></legend> 
        <span class="textline"><b>FEPKE make sense in below cases of hacker's attack or virus attacks:</b></span><br/>
       
        <span class="textline"><b>Attacks</b> at non-encrypted wireless hotSpot and using same WIFI key</span>
        <span class="textline"><b>Attacks</b> at vulnerable time during TLS/SSL established such as HTTP hand shake or impostor's<br/>certificate for key exchange </span><br/>
        <span class="textline"><b>Attacks</b> when user try to connect server but it's certificate is expired </span>
        <span class="textline"><b>Attacks</b> user account because, instead of Gmail, some account operations are not 100% on HTTPS<br/>such as Facebook etc</span>
        <span class="textline"><b>Attacks</b> by invaded virus like <b>Trojan</b> or <b>X-Agent</b> which sneaks into your devices or laptops to steal <br/>and reveal your sensitive data </span><br/>   
        <span class="textline"><b>To fight back those attacks</b></span><br/>
        <span class="textline">User can apply FEPKE to instantly encrypt any sensitive data in the web page once data<br> entry is done </span>
        <span class="textline">Encrypting sensitive data can be password, credit card number , social security , any personal<br/>information .</span> 
        <span class="textline">After user input the data and leaving the fields in the web page, FEPKE immediately encrypt<br/>those data</span>
       	<span class="textline">Those encryption action happens	even much early before user submits the web page.  </span>
        <span class="textline">Server dynamically generates Public Key Pair, front end code is doing this encryption </span>
        <br/><br/>
      </fieldset>   
 
</div>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.loan.agent.mvc.formbean.QuoteForm" %>
<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%
     	 QuoteForm q = new QuoteForm();
     	 q.setEmailAddress("WarrenSmith@apprion.com");
     	 q.setFirstName("Warren");
     	 q.setLastName("Smith");
     	 q.setLoanAmount("417,000");
         request.setAttribute("quoteForm", q);
         String formAction = request.getParameter("form_action");
         request.setAttribute("formAction", formAction);
         request.getSession().setAttribute("userAction", "signup");
    %>
    <style type="text/css">
      .contactForm .textbox {
    width: 564px;
    height: 46px;
    margin: 0;
    padding: 0 0 0 15px;
    font-size: 16px;
    color: #182A76;
    outline: 0;
    border: 1px solid;
    background: url('../images/form.png') left 97% no-repeat;
    opacity:0.8;
    filter:alpha(opacity=40)
    }
    
    </style>
  </head>
  
  <body>
  
     <div style="align:center">
     
     </div>
     <logic:present name="quoteForm" scope="request">
         
       <logic:present name="formAction" >
         <logic:equal name="formAction" value="purchase" >
         <bean:write name="formAction" /><br>
         </logic:equal>
        </logic:present>
         <bean:define id="qf" name="quoteForm" />
         <bean:write name="qf" property="loanAmount"/><br>
         <bean:write name="qf" property="emailAddress"/><br>
         <bean:write name="qf" property="firstName"/><br>
         <bean:write name="qf" property="lastName"/><br>
         <logic:equal name="quoteForm" property="loanAmount" value="417,000" >
            <h1>LoanAmount=417,000</h1>            
         </logic:equal>
     </logic:present>
     
     <logic:equal name="userAction" scope="session" value="signup">
        <h1>UserAction=<bean:write name="userAction"/> </h1>
    </logic:equal>
    
 <!--    <input id="fileId" type="file" name="fileContent" />
    <input id="buttonId" type="button" value="upload" />
   --> 
    
   <form id="ajax-contact-form" class="contactForm">
    <label>Simply type your email.</label>
        <input class="textbox" name="email" type="text" value="3.14">
        <input class="sendMessage" name="submit" value="Send Email" type="submit">
	</form>
    
  </body>
</html>
<script language="javascript">
$(document).ready(function() {
    $("#buttonId").click(function() {
       
       var filename = document.getElementById("fileId").value;
       
    });
});
$(document).ready(function(){    

if($("input.textbox").val() == "3.14") {
    $('.textbox').css({ opacity: 0.3});
} else {
    $('.textbox').css({ opacity: 1});

}

$('input.textbox').focus(function() {
        $(this).stop().fadeTo("slow", 1);
        $("input.textbox").val(""); 
    }).blur(function() {
        $(this).stop().fadeTo("slow", 0.9);
    });

});
</script>
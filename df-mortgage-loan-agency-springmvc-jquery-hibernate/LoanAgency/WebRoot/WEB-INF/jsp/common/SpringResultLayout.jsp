<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" pageEncoding="ISO-8859-1"%>
 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
 <%
   String jQueryPath =request.getContextPath()+"/BuildJQuery/development-bundle/";
 %>


<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr">

  <head>
   <meta http-equiv="X-UA-Compatible" content="IE=9"/>
   <meta http-equiv="X-UA-Compatible" content="IE=8"/>
   <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta name="description" content="Loan Agent Service, Loan Agent Email Sender, Loan Agent Email Marketing, Agent and Borrower Serivce, Free Calculators, Free Agent Sign Up now, 
    Free Loan Calculator, Loans, Calculator, Credit Card, Mortgage, Loans Online,Mortgage Rates, Home Loans, Interest Rate, Schedule, Loan Education, 
    Email Marketing, Agent Email Marketing, Bulk Email Sender, Interest, Calculate Home, Real Estate, Lender, Realtors, Refinance, Mortgage Brokers, 
    Loan Agent,  Get A Loan, Property, Loan Company, Free Email Marketing, Loan_Agent, loanAgent, Loan Agent Email Market, Borrower Inquire, 
    Loan Inquire, Interest Rate Inquire, APR, 30 years Fixed, 20 years fixed, 15 years fixed, 10/1 ARM, 5/1 ARM, Paymanet Calculator, Home Loans
    Refinance Calculator, Rate Calculator, Compare Mortgage, Compare Loans, Amortization Chart, Amortization Report, Amortization Download, Calculator Free,
    Mortgage Estimator, Security, SSL Connection, Secure SSL Encryption, Front End Encryption,  Amortization , Scehdule For Mortgage, Loan Compare Loan Knowledge ">
     
     	 
   <meta name="keywords" content="Loan Agent Service, Loan Agent Email Sender, Loan Agent Email Marketing, Agent and Borrower Serivce, Free Calculators, Free Agent Sign Up now, 
    Free Loan Calculator, Loans, Calculator, Credit Card, Mortgage, Loans Online,Mortgage Rates, Home Loans, Interest Rate, Schedule, Loan Education, 
    Email Marketing, Agent Email Marketing, Bulk Email Sender, Interest, Calculate Home, Real Estate, Lender, Realtors, Refinance, Mortgage Brokers, 
    Loan Agent,  Get A Loan, Property, Loan Company, Free Email Marketing, Loan_Agent, loanAgent, Loan Agent Email Market, Borrower Inquire, 
    Loan Inquire, Interest Rate Inquire, APR, 30 years Fixed, 20 years fixed, 15 years fixed, 10/1 ARM, 5/1 ARM, Paymanet Calculator, Home Loans
    Refinance Calculator, Rate Calculator, Compare Mortgage, Compare Loans, Amortization Chart, Amortization Report, Amortization Download, Calculator Free,
    Mortgage Estimator, Security, SSL Connection, Secure SSL Encryption, Front End Encryption,  Amortization , Scehdule For Mortgage, Loan Compare Loan Knowledge ">
    
   <title>Loans-agents.com | Loan Agent Service | Loan Agent Email Marketing |Home Loans| Mortgage Rates |  Interest Rate | Get A Loan | 30 years Fixed | Refinance | Bulk Email Sender </title>
   
    <!-- Google Analytics Track Code -->
   <script>
      (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
         (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
          m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
       })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

       ga('create', 'UA-51706961-1', 'loans-agent.com');
       ga('send', 'pageview');
    </script>
    
       <jsp:include page="./Scripts.jsp" flush="true" />   
  </head>
 

  <body class="body">
   <div class=" container "> 
    <div class="leftExDiv">
       <div class="layout">   
	<tiles:insertAttribute name="header"/>
  	<tiles:insertAttribute name="menu"/>
  	 <div class="externalcontent">
  	 <div>
  		<tiles:insertAttribute name="body"/>
  		
   	 </div>
  	   <tiles:insertAttribute name="ResultBottom"/>   
  	 </div>
  	   
  
  </div>
  </div>
 <logic:present name="agentId" > 
  	  <div class="rightExDiv">	
    	<div class="leftcontent">
  			<tiles:insertAttribute name="adbox"/>  	   	  
  		</div>
  	</div>
 </logic:present>	  	 
  	</div>
  		<tiles:insertAttribute name="footer"/>
  </body>
</html>

  <%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<style>
.content {
	width:100%;
}
.footer {
	padding-left: 20px;
	padding-right: 20px;
	padding-bottom: 20px;
	
}
 .footer a {
	color: white;
    text-decoration:none;
    margin-left:0px;
    margin-top:20px;
    margin-bottom:20px;
}
 .footer a:hover {
    color: black;
    background-color: #dfdfdf;
    text-decoration:underline;    
    margin-left:0px;
    margin-top:20px;
    margin-bottom:20px;
    
} 

</style>
<% String contextPath = request.getContextPath()+"/"; %> 
<div class="container">
 	
 	<div class="footer">
 	   
 	      <table align="center">
 	       <tr><td> 	
 	          <table>
 	            <tr><td>
 	          		<img src="<%=contextPath%>images/agents/CopyRight25pixel.png" width="20" height="20">
 	            </td><td>
 	           		<span >2013-2014, Software Developing Company: Devprobe.com, All Rights Reserved</span>
 	             </td></tr>		
 	           </table>        
 	       </td></tr>
 	       <tr><td>
  	           <span style="margin-left:100px; margin-bottom:20px;"> Contact us: support.staff@loans-agent.com</span>
   	       </td></tr> 
   	       <tr><td>
   	              <a style="margin-left:100px;" href="javascript:popUpFixedName('/termAndAgreement.html','780','700');"><span style="color:white;">Term and Agreement</span></a>	         		       
   			</td></tr> 
 	     </table>
   
 	  <br>
      <span style="color:yellow;">Favorite Links:</span> <a href="http://www.becomealoanbroker.net/loan-agent.html">Loan Agent-Become A Loan Broker | </a> 
 	   <a href="http://www.realestatelicense.com/safe-mortgage-loan-originator/mlo-lp.aspx?utm_source=Google&utm_medium=ppc&utm_campaign=SAFE-S&utm_content=LandingPage&gclid=CjkKEQjwqsCcBRDt7_Gts5a91YYBEiQAm-wYESCOz_HDEp0TbANu3ngJG8gTaFsDS9iFWxwGqp5ANi3w_wcB">Loan Officer License- SAFE Compliant? NMLS Approved | </a> 
 	   <a href="https://answers.yahoo.com/question/index?qid=20070519093104AAyXAyO">Loan agent? Mortgage broker? Loan processor? What's the difference? | </a> 
 	   <a href="http://www.calhfa.ca.gov/homebuyer/lenders.htm">Find a Loan Officer | </a>  
 	   <a href="http://www.loanagents.com/">Welcome LoanAgent.com | </a>   
 	   <a href="http://www.yelp.com/search?find_desc=Loan+Agent&find_loc=San+Jose%2C+CA">Loan agent San Jose, CA - Yelp |</a> 
 	   <a href="http://ranlife.com/">Ran Life Home Loans </a> 
 
 	   
	  <br>
		 <span style="color:yellow;">Internal Links:</span>
		 <a href="<%=contextPath%>purchaseQuote.html"> Purchase Rate Quote|</a> 
		<a href="<%=contextPath%>refinanceQuote.html" title="Rate Quote ">Refinance Rate Quote|</a>   	  
 	    <logic:notPresent name="loginAgentId">
        	<a href="<%=contextPath%>agentNicheProgram.html" title="Loan Agents login then enter Niche Loan Program">Agent Niche Loan Program |</a> 
 			<a href="<%=contextPath%>agentEnterRate.html" title="Loan Agents login then enter their own rate sheet">Agent Enter Interest Rate |</a> 
 			<a href="<%=contextPath%>agentRelpyQuoteLogin.html" title="Loan Agents login then Reply Borrower Quote ">Agent Reply Quote |</a> 
	 		<a href="<%=contextPath%>agentEmailMarketing.html" title="Configure Email Server, Create Recipicient's Email List , Preview HTML format Email including interesting rate, attached your picture and calculator links, edit it , copy -paste MS DOC / Excel table into it,  and send to your customer with your email list">Agent Email Marketing |</a></u>
	 	</logic:notPresent>
	 	 <logic:present name="loginAgentId">
 		    <a href="<%=contextPath%>nicheListView.html" title="Loan Agents login then enter Niche Loan Program">Agent Niche Loan Program |</a> 
			<a href="<%=contextPath%>agentEnterRate.html" title="Loan Agents login then enter their own rate sheet">Agent Enter Interest Rate |</a> 
		    <a href="<%=contextPath%>agentReplyQuote.html" title="Loan Agents login then Reply Borrower Quote ">Agent Reply Quote |</a> 
		  	<a href="<%=contextPath%>emailMarketing.html" title="Configure Email Server, Create Recipicient's Email List , Preview HTML format Email including interesting rate, attached your picture and calculator links, edit it , copy -paste MS DOC / Excel table into it,  and send to your customer with your email list">Agent Email Marketing |</a></u>
	 	</logic:present>
       <a href="<%=contextPath%>appCheckListView.html" title="Loan Agents login then enter Application Check List">Enter Application Check List |</a> 
       <a href="<%=contextPath%>purchaseQuote.html">Free Bulk Email Sender |</a> 	   
 	   
 	    
 	   	<a href="<%=contextPath%>compareRefinanceLoans.html" title="Compare Refinance Loans Rates and Terms">Refinance Loan Comparison |</a>  
   		<a href="<%=contextPath%>comparePurchaseLoans.html" title="Compare Purchase Loans Rates and Terms">Purchase Loan Comparison |</a>  
		<a href="<%=contextPath%>monthlyAmortization.html" title="Monthly Amertization">Monthly Amertization |</a>  
		<a href="<%=contextPath%>yearlyAmortization.html" title="Yearly Amertization">Yearly Amertization |</a>   
	  
   		<a href="<%=contextPath%>education.html" title="Education">Mortgage Loan Knowledge |</a>  
		<a href="<%=contextPath%>affordableHomePrice.html" title="Estimate Affortable Home Price">Estimate Affortable Home Price |</a> 
		<a href="<%=contextPath%>calculateMonthlyExpense.html" title="Estimate Total Monthly Expense for your Mortgage">Estimate Total Monthly Expense |</a>
		
	</div>
	 
	<!--  2012 All Copyrights Reserved 
	<table width="100%" style="padding: 00px 20px 20px 00px;  color: #1040A3; border-bottom:1px;"><tr><td align="center"><span style="align:center;color:blue;" >&nbsp;</span></td></tr></table> 
	--> 
	
</div>
 
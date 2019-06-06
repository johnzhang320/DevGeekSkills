<%@ page language="java" pageEncoding="ISO-8859-1"%>

<% String contextPath = request.getContextPath()+"/"; %> 

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 
 <div class="mainmenu">
   <ul id="css3menu" >
	<li class="topfirst"><a href="<%=contextPath%>purchaseQuote.html" target="_parent" title="4 Quotes and 4 Loan Comparision either for refinance loan or purchase loan ">Mortgage Rate Quote</a>
	<ul>
	    <li><a href="<%=contextPath%>purchaseQuote.html" title="4 Quotes and 4 Loan Comparision either for Purchase loan ">Purchase Loan Rate Quote</a></li>

		<li><a href="<%=contextPath%>refinanceQuote.html" title="4 Quotes and 4 Loan Comparision either for Purchase loan ">Refinance Loan Rate Quote</a></li>

		<li><a href="<%=contextPath%>userReviewQuote.html" title="4 Quotes and 4 Loan Comparision either for Purchase loan ">View Agent Quote Response</a></li>
	 		 
	</ul>
	</li>
	  <li><a href="<%=contextPath%>affordableHomePrice.html" title="Calculators"><span>Loan Calculators</span></a>
	<ul>
	    <li><a href="<%=contextPath%>calculateMonthlyExpense.html" title="Calculate total monthly mortgage expense including, interest payment, Property Tax and Insurence">Calculate Monthly Expense </a></li>
	
		<li><a href="<%=contextPath%>affordableHomePrice.html" title="Consider non rental income and rental income, estimate what home price you are able to afford ">What purchase price I can afford ?</a></li>
	
		<li><a href="<%=contextPath%>pageOnConstruction.html" title="Consider Loan To Value, Income , How can you be qualified a loan ">How can I qualify a loan ?</a></li>

	    <li><a href="<%=contextPath%>pageOnConstruction.html" title="Refinance Qualify and How much Interest Saving">Refinance Now ?</a></li>
 
			 
	</ul>
	</li>
	<li><a href="<%=contextPath%>downloadApplicationForm.html" title="Estimate Loan Amount, Loan Cmparision, Credit Check, Download Application Form"><span>PreConditions</span></a>
		
	  <ul>
	 	 
	 	<li><a href="<%=contextPath%>creditReportWebsite.html" title="Professional Credit Report "> Free Credit Report</a></li>

		<li><a href="<%=contextPath%>downloadApplicationForm.html" title="Customer can online download a loan application form by Acrobat Reader from loan company">Download Application Form</a></li>
	  </ul>
	</li>
	
  

	<li><a href="<%=contextPath%>compareRefinanceLoans.html" title="Loan Comparison"><span>Loan Comparison</span></a>
	<ul>
		<li><a href="<%=contextPath%>compareRefinanceLoans.html" title="Before customers decide which loan amount , term, and interest rate is better for an refinance loan, they can compare mixmium 3 supposed new loans to last loan amount , term and rate. Saying one loan is same loan amount and term but lower rate with some closing fee or point, another loan is   same loan amount but different term (15 years) and lower rate, no fee and no point, third loan is different loan amount customer want , then system calculate and give you total interest and balance you need pay , monthly saving or pay more, break even points for closing fee or points for each loan solution, customer can pick an ideal loan solution to fill request form to a loan officer ">Compare Refinance Loans</a></li>
	 
	    <li><a href="<%=contextPath%>comparePurchaseLoans.html" title="Compare Loans and Rates">Compare Purchase Loans</a></li>
			 
	</ul>
	</li>
    <li><a href="<%=contextPath%>monthlyAmortization.html" title="Amortization Graphs"><span>Amortization Report / Graphs </span></a>
	<ul>
       <li><a href="<%=contextPath%>monthlyAmortization.html" title="Calculate monthly amertization report that includes those monthly changes of principal, remaining balance,interests month by month based on loan amount , interest rate, first payment date, even if pay extra amount monthly, yearly or once ">Monthly Amertization Report / Chart</a></li>

		<li><a href="<%=contextPath%>yearlyAmortization.html" title="Calculate yearly amertization report that includes those yearly changes of principal, remaining balance,interests month by month based on loan amount , interest rate, first payment date, even if pay extra amount monthly, yearly or once">Yearly Amertization Report / Chart</a></li>

		
	</ul>
	</li>

     <li><a href="<%=contextPath%>education.html" title="Loan Process Guide"><span>Education</span></a>
	   <ul>
		   <li><a href="<%=contextPath%>education.html" title="Mortgage loan Qualification ">Mortgage Loan Qualify</a></li>
		   <li><a href="<%=contextPath%>applyLoanDocument.html" title="Documents Customer should to prepare for applying a loan">The documents to apply a loan</a></li>
	  	   <li><a href="<%=contextPath%>californiaLoan.html" title="California Mortgage Loan Procedures">California Mortgage Loan Procedures</a></li>
	   	   
	   
		   <li><a href="<%=contextPath%>loanOptions.html" title="Mortgage Loan Options">Mortgage Loan Options</a></li>
	   </ul>
	 </li>

     <li><a href="<%=contextPath%>userLogin.html" title="Send a Message to dennie"><span>Login</span></a>
		<ul>
			<li><a href="<%=contextPath%>userLogin.html" title="After login, user can see their response of the quote">User Login</a></li>

			<li><a href="<%=contextPath%>agentLogin.html" title="After login, Agent can see user quote inquire and reply them">Agent Login</a></li>

			<li><a href="<%=contextPath%>userSignup.html" title="After login, user can see their response of the quote">User Signup</a></li>

			<li><a href="<%=contextPath%>agentSignup.html" title="After login, Agent can see user quote inquire and reply them">Agent Signup</a></li>
		
			<li><a href="<%=contextPath%>agentReviewQuote.html" title="Agent can access quote review page at any time after login ">Agent Review Quotes</a></li>
	
		</ul>
	  </li>
	 
    <!--  <li><a><span style="width:59px; height:15px;"></span></a></li>-->
    </ul>
</div>

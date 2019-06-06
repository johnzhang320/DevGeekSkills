  <%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
 
<% String contextPath = request.getContextPath()+"/"; %> 
	
 
 <div class="mainmenu">
  
<ul id="css3menu1" class="topmenu">
	<li class="topfirst"><a href="<%=contextPath%>index.html" title="" style="height:15px;line-height:15px;"><span>Home</span></a>
    <ul>
	 <li><a href="<%=contextPath%>index.html"  style="height:15px;line-height:15px;"><span>Home</span></a> 
	</ul>
	</li>
 	<li class="topfirst"><a href="<%=contextPath%>websiteFeatures.html" title="" style="height:15px;line-height:15px;"><span>Features</span></a>
 	 <ul>
	 <li><a href="<%=contextPath%>websiteFeatures.html" title="" style="height:15px;line-height:15px;"><span>Highlights and Features</span></a> 
	</ul>
	</li>
 	  
 
	<li class="topmenu"><a href="<%=contextPath%>purchaseQuote.html?turnOffNiche=Yes" title="Calculate yearly amertization report that includes those yearly changes of principal, remaining balance,interests month by month based on loan amount , interest rate, first payment date, even if pay extra amount monthly, yearly or once" style="height:15px;line-height:15px;"><span>For Borrowers</span></a>
	<ul>
		<li><a href="<%=contextPath%>purchaseQuote.html?turnOffNiche=Yes" title="Purchase Loan borrower(customer) send a quote to current agent (shown as picture box)  about purchase loan, the agent will response you loan interet rate , loan comparing and professional information">Purchase Loan Rate Quote</a></li>
		<li><a href="<%=contextPath%>refinanceQuote.html?turnOffNiche=Yes" title="Refinance Loan borrower(customer) send a quote to current agent (shown as picture box)  about refinance  loan, the agent will response you loan interet rate , loan comparing and professional information">Refinance Loan Rate Quote</a></li>
		  <logic:present name="agentId">
			<li><a href="<%=contextPath%>downloadApplicationForm.html">Download Application Form</a></li>
		 </logic:present>
		<li><a href="<%=contextPath%>creditReportWebsite.html">Free Credit Report</a></li>
		<li><a href="<%=contextPath%>calculateMonthlyExpense.html" title="Calculate total monthly mortgage expense including, interest payment, Property Tax and Insurence">Calculate Monthly Expense </a></li>
		<li><a href="<%=contextPath%>affordableHomePrice.html" title="Consider non rental income and rental income, estimate what home price you are able to afford ">What purchase price I can afford ?</a></li>
		
	
 	</ul></li>
 
	<li class="toplast"><a href="<%=contextPath%>agentLogin.html?agentLoginDirect=yes" title="Loan Agent login and the account sign up" style="height:15px;line-height:15px;"><span>For Loan Agents</span></a>
	<ul>
		<!-- <li><a href="<%=contextPath%>userLogin.html?userLoginDirect=yes" title="Loan Customer Login or Sign up, if you want to send a quote to current agent, login or sign up your account">Borrower Login</a></li>
		<li><a href="<%=contextPath%>userSignup.html" title="Loan Customer Login or Sign up, if you want to send a quote to current agent, login or sign up your account">Borrower Signup</a></li>
		-->
		<li><a href="<%=contextPath%>agentLogin.html?agentLoginDirect=yes" title="Login as Loan Agents login or sign up their account , then they can view/ response their customer quote, send bulk email to their customers,  create their profile to be shown as contact, put their rate sheet to their customers">Agent Login</a></li>
		<li><a href="<%=contextPath%>agentSignup.html" title="Sign Up a Loan Agents Account to be able to enter interest rate, enter niche loan program, upload agent picture and company loan application form, enter agent expertise, license information, then send bulk emails to the customers by pulling all agent profile data, interest data, borrower quote data and check list data to generate email content quickly. Sign up agent to be able to do Email Marketing by sending secured email to customer, login agent can load email list in format: Txt file, CSV file Excel File(xls,xlsx), then send email with customer first name as greeting , email is HTML format which embeded your picuture.">Agent Signup</a></li>
  	
        <logic:notPresent name="loginAgentId">
        	<li><a href="<%=contextPath%>agentNicheProgram.html" title="Loan Agents login then enter Niche Loan Program">Agent Niche Loan Program</a></li>
 			<li><a href="<%=contextPath%>agentEnterRate.html" title="Loan Agents login then enter their own rate sheet">Agent Enter Interest Rate</a></li>
 			<li><a href="<%=contextPath%>agentRelpyQuoteLogin.html" title="Loan Agents login then Reply Borrower Quote ">Agent Reply Quote</a></li>
	 		<li><a href="<%=contextPath%>agentEmailMarketing.html" title="Configure Email Server, Create Recipicient's Email List , Preview HTML format Email including interesting rate, attached your picture and calculator links, edit it , copy -paste MS DOC / Excel table into it,  and send to your customer with your email list">Agent Email Marketing</a></li>
	 	</logic:notPresent>
	 	 <logic:present name="loginAgentId">
 		    <li><a href="<%=contextPath%>nicheListView.html" title="Loan Agents login then enter Niche Loan Program">Agent Niche Loan Program</a></li>
			<li><a href="<%=contextPath%>agentEnterRate.html" title="Loan Agents login then enter their own rate sheet">Agent Enter Interest Rate</a></li>
		    <li><a href="<%=contextPath%>agentReplyQuote.html" title="Loan Agents login then Reply Borrower Quote ">Agent Reply Quote</a></li>
		  	<li><a href="<%=contextPath%>emailMarketing.html" title="Configure Email Server, Create Recipicient's Email List , Preview HTML format Email including interesting rate, attached your picture and calculator links, edit it , copy -paste MS DOC / Excel table into it,  and send to your customer with your email list">Agent Email Marketing</a></li>
	 	</logic:present>
	    <li><a href="<%=contextPath%>appCheckListView.html" title="Loan Agents login then enter Application Check List">Enter Application Check List</a></li>

	 
	</ul></li>
 
	 

	<li class="topmenu"><a href="#" title="Email Marketing" style="height:15px;line-height:15px;"><span>Email Marketing</span></a>
	<ul>
		<li><a href="<%=contextPath%>bulkEmailSender.html" title="Send 100 emails as limit">Free Bulk Email Sender</a></li>  
	
	 	<li><a href="<%=contextPath%>agentEmailMarketing.html" >Agent Email Marketing</a></li>
		<li><a href="javascript:popUpFullScreen('/helpAgentEmailMarket.html','800','600');">Agent Email Marketing Guide</a> </li> 
		<li><a href="<%=contextPath%>agentEmailMarketing.html" >Agent Reply Quote via Email Marketing Tool</a></li>
		<li><a href="javascript:popUpFullScreen('/helpAgentReplyQuote.html','800','600');">Agent Reply Quote Guide</a> </li> 	 
		 
	 	 
	</ul></li>
	 
	<li class="topmenu"><a href="#" title="Loan Process Guide" style="height:15px;line-height:15px;"><span>Loan Compare & Amortization</span></a>
	<ul>
	    <li><a href="<%=contextPath%>compareRefinanceLoans.html" title="Before customers decide which loan amount , term, and interest rate is better for an refinance loan, they can compare mixmium 3 supposed new loans to last loan amount , term and rate. Saying one loan is same loan amount and term but lower rate with some closing fee or point, another loan is   same loan amount but different term (15 years) and lower rate, no fee and no point, third loan is different loan amount customer want , then system calculate and give you total interest and balance you need pay , monthly saving or pay more, break even points for closing fee or points for each loan solution, customer can pick an ideal loan solution to fill request form to a loan officer ">Compare Refinance Loans</a></li>
		<li><a href="<%=contextPath%>comparePurchaseLoans.html" title="Compare Loans and Rates">Compare Purchase Loans</a></li>
		<li><a href="<%=contextPath%>monthlyAmortization.html" title="Calculate monthly amertization report that includes those monthly changes of principal, remaining balance,interests month by month based on loan amount , interest rate, first payment date, even if pay extra amount monthly, yearly or once ">Monthly Amertization Report / Chart</a></li>
		<li><a href="<%=contextPath%>yearlyAmortization.html" title="Calculate yearly amertization report that includes those yearly changes of principal, remaining balance,interests month by month based on loan amount , interest rate, first payment date, even if pay extra amount monthly, yearly or once">Yearly Amertization Report / Chart</a></li>
	 </ul>
	</li>
	<li class="topmenu"><a href="<%=contextPath%>education.html" title="Loan Process Guide" style="height:15px;line-height:15px;"><span>Loan Knowledge</span></a>
	<ul>
		<li><a href="<%=contextPath%>education.html" title="Mortgage loan Qualification ">Mortgage Loan Qualify</a></li>
		<li><a href="<%=contextPath%>applyLoanDocument.html" title="Documents Customer should to prepare for applying a loan">The documents to apply a loan</a></li>
		<li><a href="<%=contextPath%>californiaLoan.html" title="California Mortgage Loan Procedures">Mortgage Loan Procedures</a></li>
		<li><a href="<%=contextPath%>loanOptions.html">Mortgage Loan Options</a></li>
		
	</ul></li> 
	<li class="topmenu"><a href="<%=contextPath%>websiteHelp.html" title="Loans-agent.com feature description and function procedures" style="height:15px;line-height:15px;"><span>Helps</span></a>
    <ul>
	 <li><a href="<%=contextPath%>websiteHelp.html" title="Loans-agent.com feature description and function procedures" style="height:15px;line-height:15px;"><span>Help Summary & Detail User Guides</span></a> 
	</ul>
	</li>
	
	 
	<!-- 	<li class="topmenu"><a href="#" style="height:15px;line-height:15px;"><span></span></a>-->
	 
	
</ul> 
</div>
 


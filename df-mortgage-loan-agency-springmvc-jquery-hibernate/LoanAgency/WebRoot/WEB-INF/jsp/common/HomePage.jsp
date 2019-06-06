 
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
 <%
    String contextPath = request.getContextPath();
   %>
   <div class="externalcontent">
       <div class="bigbox">
 <fieldset style="margin: 10px 10px 10px 10px;border:solid 2px lightblue">  
       <legend><span class="AccountCreateTableHeader">For Agents</span> </legend>
 		<p><a href="<%=contextPath%>agentLogin.html?agentLoginDirect=yes" title="Loan Agents login or sign up their account , then they can view/ response their customer quote, send bulk email to their customers,  create their profile to be shown as contact, put their rate sheet to their customers">Agent Login</a></p>
		<p><a href="<%=contextPath%>agentSignup.html" title="Loan Agents login or sign up their account , then they can view/ response their customer quote, send bulk email to their customers,  create their profile to be shown as contact, put their rate sheet to their customers">Agent Signup</a></p>
       <logic:notPresent name="loginAgentId">
        	<p><a href="<%=contextPath%>agentNicheProgram.html" title="Loan Agents login then enter Niche Loan Program">Agent Niche Loan Program</a></p>
			<p><a href="<%=contextPath%>agentEnterRate.html" title="Loan Agents login then enter their own rate sheet">Agent Enter Interest Rate</a></p>
	 		<p><a href="<%=contextPath%>agentEmailMarketing.html" title="Configure Email Server, Create Recipicient's Email List , Preview HTML format Email including interesting rate, attached your picture and calculator pnks, edit it , copy -paste MS DOC / Excel table into it,  and send to your customer with your email list">Agent Email Marketing</a></p>
	 	</logic:notPresent>
 		  <logic:present name="loginAgentId">
 		    <p><a href="<%=contextPath%>nicheListView.html" title="Loan Agents login then enter Niche Loan Program">Agent Niche Loan Program</a></p>
			<p><a href="<%=contextPath%>agentEnterRate.html" title="Loan Agents login then enter their own rate sheet">Agent Enter Interest Rate</a></p>
		 	<p><a href="<%=contextPath%>emailMarketing.html" title="Configure Email Server, Create Recipicient's Email List , Preview HTML format Email including interesting rate, attached your picture and calculator pnks, edit it , copy -paste MS DOC / Excel table into it,  and send to your customer with your email list">Agent Email Marketing</a></p>
		 	<p><a href="<%=contextPath%>compareRefinanceLoans.html" style="height:15px;line-height:15px;"><span>Loan Comparison</span></a></p>
			<p><a href="<%=contextPath%>compareRefinanceLoans.html" title="Before customers decide which loan amount , term, and interest rate is better for an refinance loan, they can compare mixmium 3 supposed new loans to last loan amount , term and rate. Saying one loan is same loan amount and term but lower rate with some closing fee or point, another loan is   same loan amount but different term (15 years) and lower rate, no fee and no point, third loan is different loan amount customer want , then system calculate and give you total interest and balance you need pay , monthly saving or pay more, break even points for closing fee or points for each loan solution, customer can pick an ideal loan solution to fill request form to a loan officer ">Compare Refinance Loans</a></p>
			<p><a href="<%=contextPath%>comparePurchaseLoans.html" title="Compare Loans and Rates">Compare Purchase Loans</a></p>
			<p><a href="<%=contextPath%>monthlyAmortization.html" style="height:15px;line-height:15px;"><span>Amortization Report / Graphs</span></a></p>
		    <p><a href="<%=contextPath%>monthlyAmortization.html" title="Calculate monthly amertization report that includes those monthly changes of principal, remaining balance,interests month by month based on loan amount , interest rate, first payment date, even if pay extra amount monthly, yearly or once ">Monthly Amertization Report / Chart</a></p>
		    <p><a href="<%=contextPath%>yearlyAmortization.html" title="Calculate yearly amertization report that includes those yearly changes of principal, remaining balance,interests month by month based on loan amount , interest rate, first payment date, even if pay extra amount monthly, yearly or once">Yearly Amertization Report / Chart</a></p>
		 	
	 	</logic:present>       
 </fieldset>	
 <fieldset style="margin: 10px 10px 10px 10px;border:solid 2px lightblue">  
       <legend><span class="AccountCreateTableHeader">For Borrowers</span> </legend>
     
    
       	<p><a href="<%=contextPath%>purchaseQuote.html" title="Purchase Loan borrower(customer) send a quote to current agent (shown as picture box)  about purchase loan, the agent will response you loan interet rate , loan comparing and professional information">Purchase Loan Rate Quote</a></p>
		<p><a href="<%=contextPath%>refinanceQuote.html" title="Refinance Loan borrower(customer) send a quote to current agent (shown as picture box)  about refinance  loan, the agent will response you loan interet rate , loan comparing and professional information">Refinance Loan Rate Quote</a></p>
	 
	 
		<p><a href="<%=contextPath%>calculateMonthlyExpense.html" title="Calculate total monthly mortgage expense including, interest payment, Property Tax and Insurence">Calculate Monthly Expense </a></p>
		<p><a href="<%=contextPath%>affordableHomePrice.html" title="Consider non rental income and rental income, estimate what home price you are able to afford ">What purchase price I can afford ?</a></p>
	 
		<p><a href="<%=contextPath%>downloadApplicationForm.html">Download Application Form</a></p>
		<p><a href="<%=contextPath%>creditReportWebsite.html">Free Credit Report</a></p>
	
	<p><a href="<%=contextPath%>education.html" title="Loan Process Guide" style="height:15px;line-height:15px;"><span>Loan Education</span></a>
	</p>
		<p><a href="<%=contextPath%>education.html" title="Mortgage loan Qualification ">Mortgage Loan Qualify</a></p>
		<p><a href="<%=contextPath%>applyLoanDocument.html" title="Documents Customer should to prepare for applying a loan">The documents to apply a loan</a></p>
		<p><a href="<%=contextPath%>californiaLoan.html" title="California Mortgage Loan Procedures">Mortgage Loan Procedures</a></p>
		<p><a href="<%=contextPath%>loanOptions.html">Mortgage Loan Options</a></p>
	
 </fieldset>
 </div>
 </div>
 
 
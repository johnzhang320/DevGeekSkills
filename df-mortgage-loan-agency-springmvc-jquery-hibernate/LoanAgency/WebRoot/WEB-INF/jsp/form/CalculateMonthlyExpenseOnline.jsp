 <%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
 
 <!-- 
 
    mode.addObject("monthlyInterestPayment", Utility.renderDollar(month_payment));		
		    mode.addObject("propertyTax", Utility.renderDollar(property_tax));		
		    mode.addObject("propertyInsurance", Utility.renderDollar(property_ins));		
		    mode.addObject("totalMortgageExpense", Utility.renderDollar(estimate_expense));		
  -->
  <div class="externalcontent">
  <div class="leftArea">
 <fieldset>
 <legend><span class="style13">Total Monthly Mortgage Expense</span> </legend>

 <label   > Total Monthly Mortgage Expense: </label>
<span style="blue"><bean:write name="totalMortgageExpense"/> </span><br>
  
 

 <label  > Monthly Mortgage Payment: </label>
 
 <span style="blue"><bean:write name="monthlyInterestPayment"/></span> <br>
 
  

 <label > Monthly Property Tax: </label>
 <span style="blue"> <bean:write name="propertyTax"/> </span><br>
  

  <label  > Monthly Property Insurance: </label>
  <span style="blue"><bean:write name="propertyInsurance"/></span> <br>
    
 
</fieldset>

<fieldset>
 <legend><span class="style13">Regular Lender Loan Approval Information</span> </legend>
 
 <label> LTV: Loan to Home Value (%) , DTI: Debt To Income (%). </label><br>
 
<label>Your LTV is : </label>
 
 <span style="blue"><b></b><bean:write name="LTV"/>%</b></span> <br> 
  
<label  > Confirming Loan:</label> 
 
 <span style="blue">Up to $417,000.00, Approval DTI Up to 50%, LTV:80% </span> <br> 
 
 <label  > High Balance Loan: </label><br>
 
 <span style="blue"> $418,000.00 to $625,000.00, Approval DTI Up to 45%,LTV: Fixed:80% ,ARM:70% </span> <br>
 

  
</fieldset>
</div>
 </div>     

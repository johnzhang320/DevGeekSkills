<%--looking for all database javabean instances which login.jsp created --%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ page language="java" import ="com.loan.agent.calculators.vo.UserReviewQuoteVo" %>
<%@ page language="java" import ="com.loan.agent.calculators.vo.CompareLoanVo" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" import="java.util.*" %>
 
 <%    
    int page_no=1;  
    
 %>
 

   
  
  <div class="tablebox2">    
    <br>
    
   <logic:present name="QuoteVo" >
   <fieldset style="width:720px;margin-left:5px;">    
   <legend>Customer Quote Detail</legend>  
   <table style="margin-left:1px;"  border="1" cellpadding="0" cellspacing="0" width="97%" >
      
   
   	<logic:equal name="QuoteVo" property="loan_type" value="Purchase">      
   	 <tr>
     <th><label class="reviewQuoteThSmall">Loan Type</label></th>
     <th><label class="reviewQuoteThSmall">Loan Amount</label></th>
     <th><label class="reviewQuoteThSmall">Purchase Price</label></th>
     <th><label class="reviewQuoteThSmall">Purchase Stage</label></th> 
     <th><label class="reviewQuoteThSmall">Purchase Date</label></th>
     <th><label class="reviewQuoteThSmall">Property Type</label></th>
     <th><label class="reviewQuoteThSmall">Occupied Type</label></th>
     <th><label class="reviewQuoteThSmall">Property State</label></th>
     <th><label class="reviewQuoteThSmall">Property County</label></th>
     <th><label class="reviewQuoteThSmall">Property Zip</label></th>     
     </tr>      
   <tr class="rowwhite">   
     <td><font size=-1><b><bean:write name="QuoteVo" property="loan_type"/></b></font></td>
     <td><font size=-1><b><bean:write name="QuoteVo" property="loan_amount"/></b></font></td>
     <td><font size=-1><b><bean:write name="QuoteVo" property="purchase_price"/></b></font></td>    
      <td><font size=-1><bean:write name="QuoteVo" property="purchase_stage"/></font></td>  
      <td><font size=-1><bean:write name="QuoteVo" property="purchase_date"/></font></td>  
      <td><font size=-1><bean:write name="QuoteVo" property="property_type"/></font></td>        
      <td><font size=-1><bean:write name="QuoteVo" property="occupancy_status"/></font></td>                   
      <td><font size=-1><bean:write name="QuoteVo" property="property_state"/></font></td>   
  	  <td><font size=-1><bean:write name="QuoteVo" property="property_county"/></font></td> 
     <td><font size=-1><bean:write name="QuoteVo" property="property_zip_code"/></font></td>
     </tr>
     </table>
   </logic:equal> 
   <logic:equal name="QuoteVo" property="loan_type" value="Refinance">      
   	 <tr>
     <th><label class="reviewQuoteThSmall">Loan Type</label></th>
     <th><label class="reviewQuoteThSmall">Loan Amount</label></th>
     <th><label class="reviewQuoteThSmall">Home Price</label></th>
     <th><label class="reviewQuoteThSmall">cash_out</label></th> 
      <th><label class="reviewQuoteThSmall">Property Type</label></th>
     <th><label class="reviewQuoteThSmall">Occupy Type</label></th>
      <th><label  class="reviewQuoteThSmall">Property State</label></th>
     <th><label  class="reviewQuoteThSmall">Property County</label></th>  
     </tr> 
          
   <tr class="rowwhite">   
     <td><font size=-1><b><bean:write name="QuoteVo" property="loan_type"/></b></font></td>
     <td><font size=-1><b><bean:write name="QuoteVo" property="loan_amount"/></b></font></td>
     <td><font size=-1><b><bean:write name="QuoteVo" property="estimate_home_value"/></b></font></td>    
      <td><font size=-1><b><bean:write name="QuoteVo" property="refinance_cash_out"/></b></font></td>  
      <td><font size=-1><b><bean:write name="QuoteVo" property="property_type"/></b></font></td>        
      <td><font size=-1><b><bean:write name="QuoteVo" property="occupancy_status"/></b></font></td>       
           <td><font size=-1><b><bean:write name="QuoteVo" property="property_state"/></b></font></td>   
  	  <td><font size=-1><b><bean:write name="QuoteVo" property="property_county"/></b></font></td> 
                  
     	</tr>  
  	</table>
  	<br>
  	 <table style="margin-left:1px;"  border="1" cellpadding="0" cellspacing="0" width="70%" >
   
  	<tr >  
  	  <th><label style="text-align:center;width:110px;">Current Initial Loan</label></th>
     <th><label style="text-align:center;width:100px;">Current loan Rate</label></th>
     <th><label style="text-align:center;width:110px;">Current Loan Term</label></th>
     <th><label style="text-align:center;width:125x;">Current Loan Pay Start</label></th>
    
     
    </tr>
    
     
    <tr class="rowwhite">	  
       <td><font size=-1><b><bean:write name="QuoteVo" property="first_loan_balance"/></b></font></td>
         <td><font size=-1><b><bean:write name="QuoteVo" property="first_loan_rate"/></b></font></td>
         <td><font size=-1><b><bean:write name="QuoteVo" property="first_loan_term"/></b></font></td>
      <td><font size=-1><b><bean:write name="QuoteVo" property="first_date"/></b></font></td>
         
     </tr>
     </table>
     
   </logic:equal>  
   
   
    </fieldset>
    <fieldset style="width:720px;margin-left:5px;">    
   <legend>Customer Finance Information</legend>   
    
   
     <table style="margin-left:1px;"  border="1" cellpadding="0" cellspacing="0" width="97%" >      
       <tr>
     <th><label class="reviewQuoteThSmall">Full Name</label></th>
     <th><label class="reviewQuoteThSmall">Email</label></th>
     <th><label class="reviewQuoteThSmall">Phone</label></th>
     <th><label class="reviewQuoteThSmall">Month Income</label></th>
     <th><label class="reviewQuoteThSmall">Rental Income</label></th>
     <th><label class="reviewQuoteThSmall">Month Debt</label></th>
     <th><label class="reviewQuoteThSmall">Credit Score</label></th>
 	 <th><label class="reviewQuoteThSmall">Live State</label></th>
 	 
 	 
     </tr>
     <tr class="rowwhite">   
    
        <td><font size=-1><b><bean:write name="QuoteVo" property="first_name"/>&nbsp<bean:write name="QuoteVo" property="last_name"/></b></font></td>
        <td><font size=-1><b><bean:write name="QuoteVo" property="email_address"/></b></font></td>
        <td><font size=-1><b><bean:write name="QuoteVo" property="phone_no"/></b></font></td>        
        <td><font size=-1><b><bean:write name="QuoteVo" property="none_rental_income"/></b></font></td>
        <td><font size=-1><b><bean:write name="QuoteVo" property="rental_income"/></b></font></td>
        <td><font size=-1><b><bean:write name="QuoteVo" property="monthly_debt"/></b></font></td>
        <td><font size=-1><b><bean:write name="QuoteVo" property="credt_score"/></b></font></td>
     	 <td><font size=-1><b><bean:write name="QuoteVo" property="state"/></b></font></td> 
 
    </tr>
    
   </table>
 <!--   <table style="margin-left:10px;"  border="0" cellpadding="0" cellspacing="1" width="97%" bgcolor=lightblue>      
    
     	<CAPTION ALIGN=TOP><span class="style27">Customer Note</span> </CAPTION> <br> 
    <tr> <td>       
      <textarea style="width:600px;align:center;margin-left:60px;" rows="3"  cols="60" readonly >
        <bean:write name='QuoteVo' property='note'/>       
        
      </textarea>
     </td>
     </tr>
     </table> 
     <br>  
      -->    
    </fieldset>
   </logic:present>
   
       
     
   
    
   
  <div id="errorBox" class="errorblock"></div>
      
     
<fieldset style="width:720px;margin-left:5px;">
 <legend>The Data For Customer Verify Loan Approval Possibility</legend>
   <table style="margin-left:1px;"  border="1" cellpadding="0" cellspacing="0" width="60%" >      
   <tr>
    
   <th><label class="reviewQuoteThLarge">Loan To Value(LTV%)</label></th>
    <th><label class="reviewQuoteThLarge">Debt To Income(DTI%)</label></th>
   <th><label class="reviewQuoteThLarge">Process Status</label></th>
   <th><label class="reviewQuoteThLarge">Quote Date </label></th>
    </tr>
   <tr>
      
       <td><font size=-1><b><bean:write name="LTV" /></b></font></td>
       <td><font size=-1><b><bean:write name="DTI" /></b></font></td>
       <td><font size=-1><b><bean:write name="QuoteVo" property="process_status"/></b></font></td>
       <td><font size=-1><b><bean:write name="QuoteVo" property="modified_date"/></b></font></td>
  
     
   </tr>
   
   </table>
  </fieldset>
  
 </div>  
<div style="display:none">
 <logic:equal name="QuoteVo" property="process_status" value="replied" >
  
 
<div class="tablebox2">          	
  <fieldset style="width:720px;margin-left:5px;">    
  <legend>Compare New Primary Loan1 with New Other Loans</legend>  
 
<table class="amert" width="720px" border="1" cellpadding="0" cellspacing="0" >
  
    <logic:equal name="SessionQuoteVo" property="loan_type" value="Purchase">     
        <tr> 
    <th ><label class="reviewQuoteThSmall">Comparing Items</label> </th>
    <th ><label class="reviewQuoteThSmall">Dummy Item</label></th>     
    <th ><label class="reviewQuoteThSmall">Primary Loan1</label></th>
    <th><label class="reviewQuoteThSmall">New Loan2</label></th>
    <th><label class="reviewQuoteThSmall">New Loan3</label></th>
    <th><label class="reviewQuoteThSmall">New Loan4</label></th>
    
  </tr> 
</logic:equal>
<logic:equal name="SessionQuoteVo" property="loan_type" value="Refinance">     
        <tr> 
    <th ><label class="reviewQuoteThLarge">Comparing Items</label></th>
    <th ><label class="reviewQuoteThSmall">Current Loan</label></th>     
    <th ><label class="reviewQuoteThSmall">New Loan1</label></th>
    <th><label class="reviewQuoteThSmall">New Loan2</label></th>
    <th><label class="reviewQuoteThSmall">New Loan3</label></th>
    <th><label class="reviewQuoteThSmall">New Loan4</label></th>
    
  </tr> 
</logic:equal>
 
   
  <tr> 
    <td ><label class="reviewQuoteThLarge">Term(Months)</label></td>
   <logic:equal name="SessionQuoteVo" property="loan_type" value="Purchase">     
    
     <td><font size=-1><b></b><bean:write name="replyLoan" property="term"/></b></td>
    </logic:equal>
    <logic:equal name="SessionQuoteVo" property="loan_type" value="Refinance">    
      <td><bean:write name="currentLoan" property="term" /></td> 
    </logic:equal>
 
  <td><font size=-1><b><bean:write name="replyLoan1" property="term"/></b></font></td>
  <td><font size=-1><b><bean:write name="replyLoan2" property="term"/></b></font></td>
  <td><font size=-1><b><bean:write name="replyLoan3" property="term"/></b></font></td>
   <td><font size=-1><b><bean:write name="replyLoan4" property="term"/></b></font></td>
  </tr> 
<tr> 
    <td><label class="reviewQuoteThLarge">Remain Loan Amount($)</label></td>
    
    <td><font size=-1><b><bean:write name="currentLoan" property="remainBalance"/></b></font></td>
    <td><font size=-1><b><bean:write name="replyLoan1" property="remainBalance"/></b></font></td>
    <td><font size=-1><b><bean:write name="replyLoan2" property="remainBalance"/></b></font></td>
    <td><font size=-1><b><bean:write name="replyLoan3" property="remainBalance"/></b></font></td>
  <td><font size=-1><b><bean:write name="replyLoan4" property="remainBalance"/></b></font></td>
  </tr>
 
  <tr> 
    <td ><label class="reviewQuoteThLarge">Interest Rate(%)</label></td>
    <td><font size=-1><b><bean:write name="currentLoan" property="int_rate"/></b></font></td>
    <td><font size=-1><b><bean:write name="replyLoan1" property="int_rate"/></b></font></td>
    <td><font size=-1><b><bean:write name="replyLoan2" property="int_rate"/></b></font></td>
    <td><font size=-1><b><bean:write name="replyLoan3" property="int_rate"/></b></font></td>
    <td><font size=-1><b><bean:write name="replyLoan4" property="int_rate"/></b></font></td>

  </tr>
  <tr> 
    <td ><label class="reviewQuoteThLarge">Closing Costs($)</label> </td>
      <td>&nbsp</td>
      <td><font size=-1><b><bean:write name="replyLoan1" property="closing_fee"/></b></font></td>
      <td><font size=-1><b><bean:write name="replyLoan2" property="closing_fee"/></b></font></td>
      <td><font size=-1><b><bean:write name="replyLoan3" property="closing_fee"/></b></font></td>
      <td><font size=-1><b><bean:write name="replyLoan4" property="closing_fee"/></b></font></td>
  </tr>
  <tr> 
   <td ><label class="reviewQuoteThLarge">Points(%)</label> </td>
     <td>&nbsp</td>
     <td><font size=-1><b><bean:write name="replyLoan1" property="point"/></b></font></td>
     <td><font size=-1><b><bean:write name="replyLoan2" property="point"/></b></font></td>
     <td><font size=-1><b><bean:write name="replyLoan3" property="point"/></b></font></td> 
     <td><font size=-1><b><bean:write name="replyLoan4" property="point"/></b></font></td> 
  </tr>
  <tr> 
    <td ><label class="reviewQuoteThLarge">Monthly Payment($)</label></td>
    <td><font size=-1><b><bean:write name="currentLoan" property="monthPayment"/></b></font></td>
    <td><font size=-1><b><bean:write name="replyLoan1" property="monthPayment"/></b></font></td>
    <td><font size=-1><b><bean:write name="replyLoan2" property="monthPayment"/></b></font></td>
    <td><font size=-1><b><bean:write name="replyLoan3" property="monthPayment"/></b></font></td>
    <td><font size=-1><b><bean:write name="replyLoan4" property="monthPayment"/></b></font></td>
</tr>
<tr> 
  <td  ><label class="reviewQuoteThLarge">Monthly Saving(+)/Pay More(-)</label></td>
   <td>&nbsp</td>
   <td><font size=-1><b><bean:write name="replyLoan1" property="monthSaving"/></b></font></td>
   <td><font size=-1><b><bean:write name="replyLoan2" property="monthSaving"/></b></font></td>
   <td><font size=-1><b><bean:write name="replyLoan3" property="monthSaving"/></b></font></td>
   <td><font size=-1><b><bean:write name="replyLoan4" property="monthSaving"/></b></font></td>
</tr>
<tr> 
  <td ><label class="reviewQuoteThLarge">Break Even Point(Yr & Mo)</label></td>
   <td>&nbsp</td>
   <td><font size=-1><b><bean:write name="replyLoan1" property="breakEventPoint"/></b></font></td>
   <td><font size=-1><b><bean:write name="replyLoan2" property="breakEventPoint"/></b></font></td>
   <td><font size=-1><b><bean:write name="replyLoan3" property="breakEventPoint"/></b></font></td>
   <td><font size=-1><b><bean:write name="replyLoan4" property="breakEventPoint"/></b></font></td>
</tr>
<tr> 
    <td ><label class="reviewQuoteThLarge">Times Already Paid</label></td>
   <td><font size=-1><b><bean:write name="cuurentLoan" property="timesAlreadyPaid"/></b></font></td>
   <td>0</td>
   <td>0</td>
   <td>0</td>
  <td>0</td>
</tr>
<tr> 
    <td ><label class="reviewQuoteThLarge">Remaining Times</label></td>
   <td><font size=-1><b><bean:write name="currentLoan" property="remainTimes"/></b></font></td>
   <td><font size=-1><b><bean:write name="replyLoan1" property="remainTimes"/></b></font></td>
   <td><font size=-1><b><bean:write name="replyLoan2" property="remainTimes"/></b></font></td>
   <td><font size=-1><b><bean:write name="replyLoan3" property="remainTimes"/></b></font></td>
   <td><font size=-1><b><bean:write name="replyLoan4" property="remainTimes"/></b></font></td>
</tr>
<tr> 
  <td ><label class="reviewQuoteThLarge">Already Paid Interest($)</label></td>
    <td><font size=-1><b><bean:write name="currentLoan" property="paidInterest"/></b></font></td>
    <td>0.00</td>
    <td>0.00</td>
    <td>0.00</td>
   <td>0.00</td>

</tr>
<tr> 
  <td ><label class="reviewQuoteThLarge">Remaining Unpaid Interest($)</label></td>
  <td><font size=-1><b><bean:write name="currentLoan" property="unpaidInterets"/></b></font></td>
  <td><font size=-1><b><bean:write name="replyLoan1" property="unpaidInterets"/></b></font></td> 
  <td><font size=-1><b><bean:write name="replyLoan2" property="unpaidInterets"/></b></font></td>
  <td><font size=-1><b><bean:write name="replyLoan3" property="unpaidInterets"/></b></font></td>
  <td><font size=-1><b><bean:write name="replyLoan4" property="unpaidInterets"/></b></font></td>
</tr>

 <tr> 
  <td><label class="reviewQuoteThLarge">Remaining Unpaid Loan Amount($)</label> </td>
  <td><font size=-1><b><bean:write name="currentLoan" property="unpainLoanAmt"/></b></font></td>
  <td><font size=-1><b><bean:write name="replyLoan1" property="unpainLoanAmt"/></b></font></td> 
  <td><font size=-1><b><bean:write name="replyLoan2" property="unpainLoanAmt"/></b></font></td>
  <td><font size=-1><b><bean:write name="replyLoan3" property="unpainLoanAmt"/></b></font></td>
  <td><font size=-1><b><bean:write name="replyLoan4" property="unpainLoanAmt"/></b></font></td>
</tr>

    

</table> 
 
 


 
 <fieldset style="width:720px;margin-left:5px;">
  <legend>Loan Agent Advice Note</legend> 
   <table style="margin-left:10px;"  border="0" cellpadding="0" cellspacing="1" width="97%" >      
    	 
    <tr> <td>       
      <textarea name="advice_note" style="width:700px;align:center;margin-left:0px;" rows="4"  cols="60"  >        
        
      </textarea>
     </td>
     </tr>
     </table> 
 
</fieldset>


</fieldset>

</div>
</logic:equal>
 </div>
 <br>
 <br>   
  
  
  
  
 <%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ page language="java" import ="com.loan.agent.calculators.vo.AgentReviewQuoteVo" %>
 
   
<logic:equal name="QuoteVo" property="process_status" value="replied" >
   <div style="display:block">
 
<div class="tablebox2">          	
  <fieldset style="width:720px;margin-left:5px;">    
  <legend>Loan Agent:<bean:write name="agentName" />'s Rates Quote and Loans Comparison</legend>  
 
<table class="amert" width="720px" border="1" cellpadding="0" cellspacing="0" >
  
    <logic:equal name="SessionQuoteVo" property="loan_type" value="Purchase">     
        <tr> 
    <th ><label class="reviewQuoteThExLarge">Comparing Items</label> </th>
    <th ><label class="reviewQuoteThSmall">Dummy Item</label></th>     
    <th ><label class="reviewQuoteThSmall">Primary Loan1</label></th>
    <th><label class="reviewQuoteThSmall">New Loan2</label></th>
    <th><label class="reviewQuoteThSmall">New Loan3</label></th>
    <th><label class="reviewQuoteThSmall">New Loan4</label></th>
    
  </tr> 
</logic:equal>
<logic:equal name="SessionQuoteVo" property="loan_type" value="Refinance">     
        <tr> 
    <th ><label class="reviewQuoteThExLarge">Comparing Items</label></th>
    <th ><label class="reviewQuoteThSmall">Current Loan</label></th>     
    <th ><label class="reviewQuoteThSmall">New Loan1</label></th>
    <th><label class="reviewQuoteThSmall">New Loan2</label></th>
    <th><label class="reviewQuoteThSmall">New Loan3</label></th>
    <th><label class="reviewQuoteThSmall">New Loan4</label></th>
    
  </tr> 
</logic:equal>
 
   
  <tr> 
    <td ><label class="reviewQuoteThExLarge">Term(Months)</label></td>
   <logic:equal name="SessionQuoteVo" property="loan_type" value="Purchase">     
    
      <td>&nbsp</td>
    </logic:equal>
    <logic:equal name="SessionQuoteVo" property="loan_type" value="Refinance">    
      <td><font size=-1><b><bean:write name="currentLoan" property="termVo" /></b></font></td> 
    </logic:equal>
 
  <td><font size=-1><b><bean:write name="replyLoan1" property="termVo"/></b></font></td>
  <td><font size=-1><b><bean:write name="replyLoan2" property="termVo"/></b></font></td>
  <td><font size=-1><b><bean:write name="replyLoan3" property="termVo"/></b></font></td>
   <td><font size=-1><b><bean:write name="replyLoan4" property="termVo"/></b></font></td>
  </tr> 

 
  <tr> 
    <td ><label class="reviewQuoteThExLarge">Interest Rate(%)</label></td>
    <td><font size=-1><b><bean:write name="currentLoan" property="intRateVo"/></b></font></td>
    <td><font size=-1><b><bean:write name="replyLoan1" property="intRateVo"/></b></font></td>
    <td><font size=-1><b><bean:write name="replyLoan2" property="intRateVo"/></b></font></td>
    <td><font size=-1><b><bean:write name="replyLoan3" property="intRateVo"/></b></font></td>
    <td><font size=-1><b><bean:write name="replyLoan4" property="intRateVo"/></b></font></td>

  </tr>
  <tr> 
    <td ><label class="reviewQuoteThExLarge">Closing Costs($)</label> </td>
      <td>&nbsp</td>
      <td><font size=-1><b><bean:write name="replyLoan1" property="closingFee"/></b></font></td>
      <td><font size=-1><b><bean:write name="replyLoan2" property="closingFee"/></b></font></td>
      <td><font size=-1><b><bean:write name="replyLoan3" property="closingFee"/></b></font></td>
      <td><font size=-1><b><bean:write name="replyLoan4" property="closingFee"/></b></font></td>
  </tr>
  <tr> 
   <td ><label class="reviewQuoteThExLarge">Points(%)</label> </td>
     <td>&nbsp</td>
     <td><font size=-1><b><bean:write name="replyLoan1" property="point"/></b></font></td>
     <td><font size=-1><b><bean:write name="replyLoan2" property="point"/></b></font></td>
     <td><font size=-1><b><bean:write name="replyLoan3" property="point"/></b></font></td> 
     <td><font size=-1><b><bean:write name="replyLoan4" property="point"/></b></font></td> 
  </tr>
  <tr> 
    <td><label class="reviewQuoteThExLarge">Remain Loan Amount($)</label></td>
    
    <td><font size=-1><b><bean:write name="currentLoan" property="remainBalance"/></b></font></td>
    <td><font size=-1><b><bean:write name="replyLoan1" property="remainBalance"/></b></font></td>
    <td><font size=-1><b><bean:write name="replyLoan2" property="remainBalance"/></b></font></td>
    <td><font size=-1><b><bean:write name="replyLoan3" property="remainBalance"/></b></font></td>
  <td><font size=-1><b><bean:write name="replyLoan4" property="remainBalance"/></b></font></td>
  </tr>
  <tr> 
    <td ><label class="reviewQuoteThExLarge">Monthly Payment($)</label></td>
    <td><font size=-1><b><bean:write name="currentLoan" property="monthPayment"/></b></font></td>
    <td><font size=-1><b><bean:write name="replyLoan1" property="monthPayment"/></b></font></td>
    <td><font size=-1><b><bean:write name="replyLoan2" property="monthPayment"/></b></font></td>
    <td><font size=-1><b><bean:write name="replyLoan3" property="monthPayment"/></b></font></td>
    <td><font size=-1><b><bean:write name="replyLoan4" property="monthPayment"/></b></font></td>
</tr>
<tr> 
  <td  ><label class="reviewQuoteThExLarge">Monthly Saving(+)/Pay More(-)</label></td>
   <td>&nbsp</td>
   <td><font size=-1><b><bean:write name="replyLoan1" property="monthSaving"/></b></font></td>
   <td><font size=-1><b><bean:write name="replyLoan2" property="monthSaving"/></b></font></td>
   <td><font size=-1><b><bean:write name="replyLoan3" property="monthSaving"/></b></font></td>
   <td><font size=-1><b><bean:write name="replyLoan4" property="monthSaving"/></b></font></td>
</tr>
<tr> 
  <td ><label class="reviewQuoteThExLarge">Break Even Point(Yr & Mo)</label></td>
   <td>&nbsp</td>
   <td><font size=-1><b><bean:write name="replyLoan1" property="breakEventPoint"/></b></font></td>
   <td><font size=-1><b><bean:write name="replyLoan2" property="breakEventPoint"/></b></font></td>
   <td><font size=-1><b><bean:write name="replyLoan3" property="breakEventPoint"/></b></font></td>
   <td><font size=-1><b><bean:write name="replyLoan4" property="breakEventPoint"/></b></font></td>
</tr>
<tr> 
    <td ><label class="reviewQuoteThExLarge">Times Already Paid</label></td>
   <td><font size=-1><b><bean:write name="currentLoan" property="timesAlreadyPaid"/></b></font></td>
   <td><font size=-1><b>0</b></font></td>
   <td><font size=-1><b>0</b></font></td>
   <td><font size=-1><b>0</b></font></td>
  <td><font size=-1><b>0</b></font></td>
</tr>
<tr> 
    <td ><label class="reviewQuoteThExLarge">Remaining Times</label></td>
   <td><font size=-1><b><bean:write name="currentLoan" property="remainTimes"/></b></font></td>
   <td><font size=-1><b><bean:write name="replyLoan1" property="remainTimes"/></b></font></td>
   <td><font size=-1><b><bean:write name="replyLoan2" property="remainTimes"/></b></font></td>
   <td><font size=-1><b><bean:write name="replyLoan3" property="remainTimes"/></b></font></td>
   <td><font size=-1><b><bean:write name="replyLoan4" property="remainTimes"/></b></font></td>
</tr>
<tr> 
  <td ><label class="reviewQuoteThExLarge">Already Paid Interest($)</label></td>
    <td><font size=-1><b><bean:write name="currentLoan" property="paidInterest"/></b></font></td>
    <td><font size=-1><b>0.00</b></font></td>
    <td><font size=-1><b>0.00</b></font></td>
    <td><font size=-1><b>0.00</b></font></td>
   <td><font size=-1><b>0.00</b></font></td>

</tr>
<tr> 
  <td ><label class="reviewQuoteThExLarge">Remaining Unpaid Interest($)</label></td>
  <td><font size=-1><b><bean:write name="currentLoan" property="unpaidInterets"/></b></font></td>
  <td><font size=-1><b><bean:write name="replyLoan1" property="unpaidInterets"/></b></font></td> 
  <td><font size=-1><b><bean:write name="replyLoan2" property="unpaidInterets"/></b></font></td>
  <td><font size=-1><b><bean:write name="replyLoan3" property="unpaidInterets"/></b></font></td>
  <td><font size=-1><b><bean:write name="replyLoan4" property="unpaidInterets"/></b></font></td>
</tr>

 <tr> 
  <td><label class="reviewQuoteThExLarge">Remaining Unpaid Loan Amount($)</label> </td>
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
        <bean:write name="currentLoan" property="adviceNote"/>
      </textarea>
     </td>
     </tr>
     </table> 
 
</fieldset>


</fieldset>

</div>
 </div>
</logic:equal>

 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/form/formUtils.js"> </script>  
        
 <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/amortOnlineReport.js"> </script> 
 
 
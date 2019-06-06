 
 
   
  <div id = "reportFrame" style="display:none; padding-left:20px;padding-right:20px;padding-top:10px" >
   
        	

 <table  class="amertize" width="750px" border="1" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
  
 
   <CAPTION ALIGN=TOP><Font color=blue ><b>Current Loan Information</b></Font> </CAPTION> 
 
  <tr> 
    <td width="19%" height="24"><div align="center"><font color=blue>Loan Amount($)</font></div></td>
    <td width="16%"><div align="center"><font color="#0000FF">Term (months)</font></div></td>
    
    <td width="19%"><div align="center"><font color="#0000FF">Interest Rate(%)</font></div></td>
    <td width="24%"><div align="center"><font color="#0000FF">First Payment Date</font></div></td>
    <td width="30%"><div align="center"><font color="#0000FF">Remaining Balance($)</font></div></td>
  </tr>
 	<td><label for="loan_amt"></label> </td>   
    <td><label for="term"></label></td> 
    <td><label for="int_rate"></label></td> 
    <td><label for="first_date"></label></td> 
    <td><label for="remain_balance"></label></td> 
  </tr>
  
</table>
<br> 
<table class="loancompare" width="750px" border="1" cellpadding="1" cellspacing="1" bgcolor="#FFFFFF">
    <CAPTION ALIGN=TOP><Font color=blue ><b>Compare Current Loan with New Loans </b></Font> </CAPTION> 

   <tr> 
    <td ><div align="center"><font color="#0000FF">Comparing Items</font></div></td>
    <td ><div align="center"><font color="#0000FF">Current Loan</font></div></td>
    <td ><div align="center"><font color="#0000FF">New Loan1</font></div></td>
    <td><div align="center"><font color="#0000FF">New Loan2</font></div></td>
    <td><div align="center"><font color="#0000FF">New Loan3</font></div></td>
    <td><div align="center"><font color="#0000FF">New Loan4</font></div></td>
  </tr>
  
  <tr> 
    <td style="padding-left:10px;text-align:left">Term(Months)</td>
  <td><label for="term"></label></td>
  <td><label for="term1"></label></td>
  <td><label for="term2"></label></td>
  <td><label for="term3"></label></td>
  <td><label for="term4"></label></td>
  </tr> 
 <tr> 
    <td style="padding-left:10px;text-align:left">Remain Loan Amount($)</td>
    
    <td><label for="remain_balance"></label></td>
    <td><label for="new_loan_amt1"></label></td>
    <td><label for="new_loan_amt2"></label></td>
    <td><label for="new_loan_amt3"></label></td>
    <td><label for="new_loan_amt4"></label></td>
  </tr>
 
  <tr> 
    <td style="padding-left:10px;text-align:left">Interest Rate(%)</td>
    <td><label for="int_rate"></label></td>
    <td><label for="int_rate1"></label></td>
    <td><label for="int_rate2"></label></td>
    <td><label for="int_rate3"></label></td>
    <td><label for="int_rate4"></label></td>
  </tr>
  <tr> 
    <td style="padding-left:10px;text-align:left">Closing Costs($) </td>
      <td>&nbsp</td>
      <td><label for="closing_cost1"></label></td>
      <td><label for="closing_cost2"></label></td>
      <td><label for="closing_cost3"></label></td>
      <td><label for="closing_cost4"></label></td>

  </tr>
  <tr> 
   <td style="padding-left:10px;text-align:left">Points(%) </td>
     <td>&nbsp</td>
     <td><label for="point1"></label></td>
     <td><label for="point2"></label></td>
     <td><label for="point3"></label></td> 
     <td><label for="point4"></label></td> 

  </tr>
  <tr> 
    <td style="padding-left:10px;text-align:left">Monthly Payment($)</td>
    <td><label for="PMT"></label></td>
    <td><label for="PMT1"></label></td>
    <td><label for="PMT2"></label></td>
    <td><label for="PMT3"></label></td>
    <td><label for="PMT4"></label></td>
  
</tr>
<tr> 
  <td style="padding-left:10px;text-align:left" >Monthly Saving(+)/Pay More(-)</td>
   <td>&nbsp</td>
   <td><label for="monthly_saving1"></label></td>
   <td><label for="monthly_saving2"></label></td>
   <td><label for="monthly_saving3"></label></td>
   <td><label for="monthly_saving4"></label></td>

</tr>
<tr> 
  <td style="padding-left:10px;text-align:left">Break Even Point(Yr & Mo)</td>
   <td>&nbsp</td>
   <td><label for="BEP1"></label></td>
   <td><label for="BEP2"></label></td>
   <td><label for="BEP3"></label></td>
   <td><label for="BEP4"></label></td>
   
</tr>
<tr> 
    <td style="padding-left:10px;text-align:left">Times Already Paid</td>
   <td><label for="paid_num"></label></td>
   <td>0</td>
   <td>0</td>
   <td>0</td>
   <td>0</td>
</tr>
<tr> 
    <td style="padding-left:10px;text-align:left">Remaining Times</td>
   <td><label for="remain_num"></label></td>
   <td><label for="term1"></label></td>
   <td><label for="term2"></label></td>
   <td><label for="term3"></label></td>
   <td><label for="term4"></label></td>
</tr>
<tr> 
  <td style="padding-left:10px;text-align:left" >Already Paid Interest($)</td>
    <td><label for="total_int_paid"></label></td>
    <td>0.00</td>
    <td>0.00</td>
    <td>0.00</td>
    <td>0.00</td>
</tr>
<tr> 
  <td style="padding-left:10px;text-align:left">Remaining Unpaid Interest($) </td>
  <td><label for="total_unint_paid"></label></td>
  <td><label for="total_unint_paid1"></label></td> 
  <td><label for="total_unint_paid2"></label></td>
  <td><label for="total_unint_paid3"></label></td>
   <td><label for="total_unint_paid4"></label></td>
</tr>

 <tr> 
  <td style="padding-left:10px;text-align:left">Remaining Unpaid Loan Amount($) </td>
  <td><label for="unpaid_remain_bal"></label></td>
  <td><label for="unpaid_remain_bal1"></label></td> 
  <td><label for="unpaid_remain_bal2"></label></td>
  <td><label for="unpaid_remain_bal3"></label></td>
   <td><label for="unpaid_remain_bal4"></label></td>
</tr>

    

</table>

 
 

</div>
 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/form/formUtils.js"> </script>  
        
 <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/amortOnlineReport.js"> </script> 
 
 
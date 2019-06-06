 <%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
   
 <!-- 
 
   mode.addObject("aggressiveAffordablePrice", Utility.renderDollar(aPrice));		
   mode.addObject("conservativeAffordablePrice", Utility.renderDollar(cPrice));		
  -->
  <div >
   
 <fieldset>
 <legend ><span class="style13">Affordable Home Price For Purchase</span> </legend>

 <label  > Aggressive Affordable Home Price: </label>
<span style="blue"><bean:write name="aggressiveAffordablePrice"/> </span><br>
  

 <label  > Conservative Affordable Home Price: </label>
 
 <span style="blue"><bean:write name="conservativeAffordablePrice"/></span> <br>
 
</fieldset>

<fieldset>
 <legend><span class="style13">Regular Lender Loan Approval Information</span> </legend>
 
 <label> LTV: Loan to Home Value (%) , DTI: Debt To Income (%). </label><br>
 
<label>Your DTI is : </label>
 
 <span style="blue"><b></b><bean:write name="DTI"/>%</b></span> <br> 
  
<label  > Confirming Loan Information: </label> <br>
 
 <span style="blue">Up to $417,000.00, Approval DTI Up to 50%, LTV:80% </span> <br> 
 
 <label  > High Balance Loan: </label><br>
 
 <span style="blue"> $418,000.00 to $625,000.00, Approval DTI Up to 45%,LTV: Fixed:80% ,ARM:70% </span> <br>
 
 <label  > Jumbor Loan: </label><br>
 
 <span style="blue">Over $625,000.00,  Approval DTI:35% or 40% ,LTV:70%</span> <br>
  
</fieldset>
<br><br>
 </div>     

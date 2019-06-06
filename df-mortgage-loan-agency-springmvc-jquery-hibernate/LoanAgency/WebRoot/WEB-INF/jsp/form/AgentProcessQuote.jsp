<%--looking for all database javabean instances which login.jsp created --%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<%@ page language="java" import ="com.loan.agent.calculators.vo.AgentReviewQuoteVo" %>
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
 <legend>The Data For Analysis</legend>
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
  
 <form name="agentProcessQuoteOnline" id="agentProcessQuoteOnline" method="post"  action="agentProcessQuoteOnline.html" >
 

<fieldset style="width:720px;margin-left:5px;">
 <legend><span>Loan Agent Recommend Loans</span> </legend>
 
   <label class="reviewQuoteTitle1">Loan1:</label>
 <label class="reviewQuoteTitle2">Loan2:</label>
  <label class="reviewQuoteTitle3">Loan3:</label>
  <label class="reviewQuoteTitle4">Loan4:</label><br>
  
<!--   <label class="reviewQuoteLabel">Inquired Terms:</label>
  <label class="reviewQuoteTitle1"> <bean:write name="QuoteVo" property="term1"/> </label>
 <label class="reviewQuoteTitle2"><b><bean:write name="QuoteVo" property="term2"/></b></label>
  <label class="reviewQuoteTitle3"><b><bean:write name="QuoteVo" property="term3"/></b></label>
  <label class="reviewQuoteTitle4"><b><bean:write name="QuoteVo" property="term4"/></b></label><br>
 --> 
 <label class="reviewQuoteLabel">Term:</label>
 <select  name="term1" id="term1" class="reviewQuoteData" >
 <option  value="" selected="selected"></option>
 <option  value="360" >30 Years Fixed</option>
 <option value="240">20 Years Fixed</option>
 <option value="180">15 Years Fixed</option>
  <option value="120">10/1 ARM</option>
 <option value="84">7/1 ARM</option>
 <option value="60">5/1 ARM</option>
 <option value="36">3/1 ARM</option>
 </select>  
 <select name="term2" id="term2" class="reviewQuoteData"  >
 <option value="" selected="selected">
 <option  value="360" >30 Years Fixed</option>
 <option value="240">20 Years Fixed</option>
 <option value="180">15 Years Fixed</option>
  <option value="120">10/1 ARM</option>
 <option value="84">7/1 ARM</option>
 <option value="60">5/1 ARM</option>
 <option value="36">3/1 ARM</option>
 </select>
  <select name="term3" id="term3" class="reviewQuoteData"  >
   <option value="" selected="selected">
  <option  value="360" >30 Years Fixed</option>
 <option value="240">20 Years Fixed</option>
 <option value="180">15 Years Fixed</option>
  <option value="120">10/1 ARM</option>
 <option value="84">7/1 ARM</option>
 <option value="60">5/1 ARM</option>
 <option value="36">3/1 ARM</option>
 </select>
  <select name="term4" id="term4" class="reviewQuoteData"  >
   <option value="" selected="selected">
  <option  value="360" >30 Years Fixed</option>
 <option value="240">20 Years Fixed</option>
 <option value="180">15 Years Fixed</option>
 <option value="120">10/1 ARM</option>
 <option value="84">7/1 ARM</option>
 <option value="60">5/1 ARM</option>
 <option value="36">3/1 ARM</option>
 </select>
 <br>

<label class="reviewQuoteLabel" >Loan Amount</label>
 
 
   <logic:equal name="QuoteVo" property="loan_type" value="Refinance"> 
   <input id="new_loan_amt1" class="reviewQuoteData"  type="text" name="new_loan_amt1" />
   <input id="new_loan_amt2" class="reviewQuoteData"  type="text" name="new_loan_amt2" />
   <input id="new_loan_amt3" class="reviewQuoteData" type="text"  name="new_loan_amt3" />
   <input id="new_loan_amt4" class="reviewQuoteData" type="text"  name="new_loan_amt4" /> 	
  </logic:equal> 
  <logic:equal name="QuoteVo" property="loan_type" value="Purchase">    
   <input id="new_loan_amt1" class="reviewQuoteData"  type="text" value="<bean:write name='QuoteVo' property='loan_amount'/> " name="new_loan_amt1"/>
  <input id="new_loan_amt2" class="reviewQuoteData"  type="text" value="<bean:write name='QuoteVo' property='loan_amount'/> " name="new_loan_amt2"/>
  <input id="new_loan_amt3" class="reviewQuoteData"  type="text" value="<bean:write name='QuoteVo' property='loan_amount'/> " name="new_loan_amt3"/>
  <input id="new_loan_amt4" class="reviewQuoteData"  type="text" value="<bean:write name='QuoteVo' property='loan_amount'/> " name="new_loan_amt4"/>
  
 </logic:equal>
  <br>
 
 <label class="reviewQuoteLabel" id="int_rateLabel" > Interest Rate </label>
  <input id="int_rate1"  class="reviewQuoteData"  type="text" size="25" name="int_rate1"/>
  <input id="int_rate2"  class="reviewQuoteData"  type="text" size="25" name="int_rate2" />
 <input id="int_rate3"  class="reviewQuoteData"  type="text" size="25" name="int_rate3" />
   <input id="int_rate4"  class="reviewQuoteData"  type="text" size="25" name="int_rate4" />

 <br>

<label class="reviewQuoteLabel" id="closing_costLabel">Closing Cost</label>
<input id="closing_cost1"  class="reviewQuoteData" type="text" size="25" name="closing_cost1" />
<input id="closing_cost2" class="reviewQuoteData" type="text" size="25" name="closing_cost2" />
<input id="closing_cost3" class="reviewQuoteData"  type="text" size="25" name="closing_cost3" />
<input id="closing_cost4" class="reviewQuoteData"  type="text" size="25" name="closing_cost4" />
<br>

<label id="pointLabel" class="reviewQuoteLabel">Points </label>
<input id="point1"  class="reviewQuoteData"  type="text" size="25" name="point1" />
<input id="point2"  class="reviewQuoteData"  type="text" size="25" name="point2" />
<input id="point3"  class="reviewQuoteData" type="text" size="25" name="point3" />
<input id="point4"  class="reviewQuoteData" type="text" size="25" name="point4" />
<br>
 
 

<label id="sample" class="reviewQuoteLabel">Sample Data</label>
<a class="noUnderline" href="javascript:newLoanExample('1')"><span class="linkbutton">Sample Data 1 </span></a>
<a class="noUnderline" href="javascript:newLoanExample('2')"><span class="linkbutton">Sample Data 2 </span></a>
<a class="noUnderline" href="javascript:newLoanExample('3')"><span class="linkbutton">Sample Data 3 </span></a>
<a class="noUnderline" href="javascript:newLoanExample('4')"><span class="linkbutton">Sample Data 4 </span></a>
 
   <logic:equal name="QuoteVo" property="loan_type" value="Refinance">     	
     <a class="noUnderline" href="javascript:applyLoanBalance('getRefiRemainBalance')"><span class="linkbutton">Apply Remain Balance </span></a>
    </logic:equal>
  


</fieldset>
 <fieldset style="width:720px;margin-left:5px;">
  <legend>Loan Agent Advice Note</legend> 
   <table style="margin-left:10px;"  border="0" cellpadding="0" cellspacing="1" width="97%" >      
     	 
    <tr> <td>       
      <textarea name="advice_note"   style="width:700px;align:center;margin-left:0px;" rows="4"  cols="60"  >        
         <bean:write name='QuoteVo' property='advice_note'/>
      </textarea>
     </td>
     </tr>
     </table> 
</fieldset>
	<div style="padding-left:260px;">        		    
    
         <logic:equal name="QuoteVo" property="loan_type" value="Refinance">     	
      		<a class="noUnderline" href="javascript:submitAgentQuoteForm('agentProcessQuoteOnline','agentProcessQuoteRefiOnLine.html')"><span class="linkbutton">Submit</span></a>
		</logic:equal>
		 <logic:equal name="QuoteVo" property="loan_type" value="Purchase">     	
      		<a class="noUnderline" href="javascript:submitAgentQuoteForm('agentProcessQuoteOnline','agentProcessQuotePurchOnLine.html')"><span class="linkbutton">Submit</span></a>
		</logic:equal>
		
	    <a class="noUnderline" href="javascript:cancelform()"><span class="linkbutton">Cancel</span></a>
      	
      	<br>
     
 	</div>   
 	 </form> 
 	 </div>
 
 <br>
 <br>   
  
  
  
  
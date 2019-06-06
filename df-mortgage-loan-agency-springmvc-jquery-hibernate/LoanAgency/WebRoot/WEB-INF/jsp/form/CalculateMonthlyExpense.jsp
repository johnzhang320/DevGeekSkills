  <%
	   String imagePath =request.getContextPath()+"/images/layoutImages/";
	  
	   String leftCapture =imagePath+ "mortgageLoanAgency_Monthly_Mortgage_Expense.png";
	  
 %>
  
<!-- <form id="amortForm" name="amortForm" method="post" >-->
  
<div class="externalcontent">

<div class="bigbox">
<div class="module-title">
    Calculate Monthly Total Mortgage Expense   	                 
 </div>	
 
  <div id="errorBox" class="errorblock"></div>
     
<fieldset>
 <legend><span class="style13"> Input Loan Information</span> </legend>
 <label id="loan_amtLabel"  ><font color="red">*</font> Loan Amount </label>
 <input  id="loan_amt" type="text" size="25" name="loan_amt" value="417,000.00" /> 
   <label id="loan_amtMsg" class="errorMsg"></label> 
              
 <br>
 <label id="int_rateLabel"   ><font color="red">*</font> Interest Rate: </label>
 <input   id="int_rate" type="text" size="25" name="int_rate" value="3.875"  />
<label id="int_rateMsg" class="errorMsg"></label> 
 <br>
  
 <label id="termLabel"   ><font color="red">*</font>Term:</label>
 <select id="term"   name="term"  >
   
 <option  value="360" >30 Years Fixed</option>
 <option value="240">20 Years Fixed</option>
 <option value="180">15 Years Fixed</option>
 <option value="120">10/1 ARM</option>
 <option value="84">7/1 ARM</option>
 <option value="60">5/1 ARM</option>
 <option value="36">3/1 ARM</option>
 </select>
 <label id="termMsg" class="errorMsg"></label> 
 <br>

 
      <label id="purch_pricelabel"  ><font color="red">*</font> Purchase Price($): </label>
      <input id="purch_price" type="text" size="25" name="purch_price" value="650,000.00"/>
      <label id="purch_priceMsg" class="errorMsg"></label> 
      <br>
      
      <label id="prop_taxlabel"   ><font color="red">*</font> Property Tax(%): </label>
      <input id="prop_tax" type="text" size="25" name="prop_tax" value="1.25"/>
      <label id="prop_taxMsg" class="errorMsg"></label> 
      <br>
      
      <label id="prop_inclabel"   ><font color="red">*</font>Property Insurance(%): </label>
      <input id="prop_inc"  type="text" size="25" name="prop_inc" value="0.35"/>
      <label id="prop_incMsg" class="errorMsg"></label> 
      <br>
      
       <label id="extra_PMTlabel"   > Extra Monthly Payment </label>
       <input id="extra_PMT"  type="text" name="extra_PMT" value="" size=20 maxlength=150 > 
        <label id="extra_PMTMsg" class="errorMsg"></label> 
       <br> 
 

</fieldset>

</div>

</div>
	<div style="padding-left:180px;">
               		    
       	<a  href="javascript:submitform('calculateMonthlyExpenseOnline.html')"><img style="border:0px;padding-top:10px;padding-left:30px;" 
        		src="<%=request.getContextPath()%>/images/formImages/b_calculate.png" 
    			    		title="Next Pag" width="86" height="30" />
      	</a>
      		<a  href="javascript:cancelform()"><img style="border:0px;padding-top:10px;padding-left:30px;" 
        		src="<%=request.getContextPath()%>/images/formImages/b_cancelAmort.png" 
    			    		title="Next Pag" width="86" height="30" />
      	</a>
 	</div>   
	<div id = "reportFrame" style="display:none;  padding-left:20px;padding-right:20px;padding-top:10px" >
	
	<IFRAME id="amortFrame" style="background-color:white;" WIDTH=750 HEIGHT=400 frameBorder="0" scrolling="no">
	 
	</IFRAME>
</div> 



<!-- </form>-->

 <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/formUtils.js"> </script>  
        
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/amortOnlineReport.js"> </script> 
  
   
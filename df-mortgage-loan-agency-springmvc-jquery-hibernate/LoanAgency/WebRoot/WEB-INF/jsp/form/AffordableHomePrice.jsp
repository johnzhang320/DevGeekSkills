  <%
	   String imagePath =request.getContextPath()+"/images/layoutImages/";
	  
	   String leftCapture =imagePath+ "mortgageLoanAgency_Monthly_Affordable_Price.png";
	  
 %>
  
<!-- <form id="amortForm" name="amortForm" method="post" >-->
  
<div class="externalcontent">

<div class="bigbox">

<div class="module-title">
   Estimate Affordable Home Price For Purchase	                 
 </div>	 
 
  <div id="errorBox" class="errorblock"></div>
 
<fieldset>
 <legend><span class="style13"> Estimate Home Price for Purchase Loan </span> </legend>
 <label id="nonRentalIncomeLabel"  ><font color="red">*</font> Non Rental Monthly Income </label>
 <input  id="nonRentalIncome" type="text" size="25" name="nonRentalIncome" value="6,000.00" /> 
   <label id="nonRentalIncomeMsg" class="errorMsg"></label> 
   <br>
   
   
 <label id="rentalIncomeLabel"  ><font color="red">*</font> Rental Monthly Income </label>
 <input  id="rentalIncome" type="text" size="25" name="rentalIncome" value="700.00" /> 
   <label id="rentalIncomeMsg" class="errorMsg"></label>             
 <br>
 
 
 <label id="debtLabel"  ><font color="red">*</font> Monthly Debt Payment </label>
 <input  id="debt" type="text" size="25" name="debt" value="2200.00" /> 
   <label id="debtMsg" class="errorMsg"></label>             
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
 
 <label id="downPaymentLabel"  ><font color="red">*</font> Down Payment Payment </label>
 <input  id="downPayment" type="text" size="25" name="downPayment" value="200,000.00" /> 
   <label id="downPaymentMsg" class="errorMsg"></label>             
 <br>    
      
      <label id="prop_taxlabel"   ><font color="red">*</font> Property Tax(%): </label>
      <input id="prop_tax" type="text" size="25" name="prop_tax" value="1.25"/>
      <label id="prop_taxMsg" class="errorMsg"></label> 
      <br>
      
      <label id="prop_inclabel"   ><font color="red">*</font>Property Insurance(%): </label>
      <input id="prop_inc"  type="text" size="25" name="prop_inc" value="0.35"/>
      <label id="prop_incMsg" class="errorMsg"></label> 
      <br>
      
 	<label id="LTVLabel"   ><font color="red">*</font>Property Type:</label>
 	<select id="LTV"   name="LTV"  >
   
 		<option  value="0.80">Owner Ocuppied Property</option>
 		<option value="0.75">Investment Property</option>
 		<option value="0.70">Second Home Property</option>
 		 
 	</select>

</fieldset>

</div>

</div>
	<div style="padding-left:180px;">
               		    
       	<a  href="javascript:submitform('affordableHomePriceOnline.html')"><img style="border:0px;padding-top:10px;padding-left:30px;" 
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
  
   
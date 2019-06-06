  <%
	   String imagePath =request.getContextPath()+"/images/layoutImages/";
	  
	   String leftCapture =imagePath+ "mortgageLoanAgency_Monthly_Amortization.png";
	  
 %>
   
 <script	language="javascript" type="text/javascript">
  function MyFunction(baseUrl) {
      alert("test OK "+'monthlyAmortOnline.html');
      }
 </script> 
<!-- <form id="amortForm" name="amortForm" method="post" >-->
  
<div class="externalcontent">
 	  	 <!-- 
  Show Circle Progress Screen when something is running longer
 -->
 <div id="cProgressSamll">
	 <img style="margin-left:170px;margin-top:140px;" src="<%=imagePath%>animateCircle.gif" />
</div>
<div class="bigbox">
<div class="module-title">
   Monthly Amortization Schedule Calculation  	                 
 </div>	 
 <div class="leftAreaSmall">
<fieldset>
 <legend><span class="style13"> Input Loan Information</span> </legend>
 <label id="loan_amtLabel" style="width:90px" ><font color="red">*</font> Loan Amount </label>
 <input style="width:100px;" id="loan_amt" type="text" size="25" name="loan_amt" value="417,000.00" /> 
  
 <br>
 <label id="int_rateLabel" style="width:90px" ><font color="red">*</font> Interest Rate: </label>
 <input style="width:100px;" id="int_rate" type="text" size="25" name="int_rate" value="3.875"  />


 <br>
  
 <label id="termLabel" style="width:90px" ><font color="red">*</font>Term:</label>
 <select id="term" style="width:100px; border:1px solid;" name="term"  >
   
 <option  value="360" >30 Years Fixed</option>
 <option value="240">20 Years Fixed</option>
 <option value="180">15 Years Fixed</option>
 <option value="120">10/1 ARM</option>
 <option value="84">7/1 ARM</option>
 <option value="60">5/1 ARM</option>
 <option value="36">3/1 ARM</option>
 </select>
 <br>

<label id="first_dateLabel" style="width:90px" ><font color="red">*</font>First Pay Date: </label>
<input style="width:100px;" id="first_date" type="text" size="45" name="first_date" value="08-20-2012" /><br>
 

</fieldset>

</div>
<div class="rightAreaSmall">
<fieldset>
 <legend><span class="style13"> Input Extra Payment (if you pay in past & future)</span> </legend>
 <label id="extra_PMTLabel" style="width:100px" > Monthly Payment </label>
 <input style="width:100px;" id="extra_PMT"  type="text" name="extra_PMT" value="" size=20 maxlength=150 > 
 <label id="PMT_dateLabel" style="width:80px" > Starting Date: </label>
 <input style="width:100px;" id="PMT_date" type="text" size="45" name="PMT_date" /><br>
 


   <label id="extra_YPMTLabel" style="width:100px" for="extra_YPMT" >Yearly Payment </label>
   <input style="width:100px;" id="extra_YPMT" type="text" name="extra_YPMT" value="" size=20 maxlength=150 > 
  <label id="YPMT_dateLabel" style="width:80px" > Starting Date: </label>
 
<input style="width:100px;" id="YPMT_date" type="text" size="45" name="YPMT_date" /><br>


  <label id="extra_YPMT_onceLabel" style="width:100px" > Pay Once Only </label>
  <input style="width:100px;" id="extra_YPMT_once" type="text" name="extra_YPMT_once" value="" size=20 maxlength=150 > 
<label id="once_datelabel" style="width:80px" > Starting Date: </label>
 
<input style="width:100px;" id="once_date" type="text" size="45" name="once_date" /><br><br><br>


</fieldset>

	 	
</div>

</div>
	<div style="padding-left:150px;">
          		    
   
      	<br>
      	  <table>
     	  <tr><td>
      		  <input type="button" class="buttonImage" value="Calculate Online Report" onclick="javascript:submitform('monthlyAmortOnline.html');">
     	  </td><td>
		      <input type="button" class="buttonLeft" value="Download PDF Report" onclick="javascript:submitPdfform('monthlyAmortPDF.html');">
		   </td><td>
      		  <input type="button" class="buttonLeft" value="Cancel" onclick="javascript:cancelform();">
 		  </td></tr>
    </table>  	
 	</div>   
 	<div id = "reportFrame" style="display:none;  padding-left:20px;padding-right:20px;padding-top:10px" >
		
		<IFRAME id="amortFrame" style="background-color:lightblue;" WIDTH=750 HEIGHT=625  scrolling="yes">
			 
		</IFRAME>
	</div>
</div>
  


<!-- </form>-->

 <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/formUtils.js"> </script>  
        
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/amortOnlineReport.js"> </script> 
  
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/amortOnlinePDFReport.js"> </script> 
  
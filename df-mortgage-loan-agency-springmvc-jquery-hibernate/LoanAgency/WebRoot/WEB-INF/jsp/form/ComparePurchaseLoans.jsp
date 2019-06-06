<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

   <%
	   String imagePath =request.getContextPath()+"/images/layoutImages/";
	  
	   String leftCapture =imagePath+ "LeftArea_Compare_Purchase_Loans.png";
	  
 %>
 
 
 
<div  class="bigbox"> 
<form name="comparePurchOnLine" id="comparePurchOnLine" method="post" action="comparePurchOnLine.html">
<div class="module-title">
    Compare Purchase Loans   	   
    <input type="button" value="Help" class="helpbutton" onclick="javascript:popUpFullScreen('/websiteHelp.html?helpTab=tabs-15','600','500');">
  </div>	  
  <div id="errorBox" class="errorblock"></div>
     
<fieldset>
 <legend><span class="style13"> Input Purchase Loan Information</span> </legend>
 <label id="loan_amtLabel"  ><font color="red">*</font> Loan Amount($) </label>
 <input  id="loan_amt" type="text" size="25" name="loan_amt" value="417,000.00" /> 
   <label id="loan_amtMsg" class="errorMsg"></label> 
              
 <br>
 <label id="int_rateLabel"   ><font color="red">*</font> Interest Rate(%): </label>
 <input   id="int_rate" type="text" size="25" name="int_rate" value="4.125"  />
<label id="int_rateMsg" class="errorMsg"></label> 
 <br>
  
 <label id="termLabel"   ><font color="red">*</font>Term:</label>
 <select id="term"   name="term"  class="compareLoanData">   
 <option  value="360" >30 Years Fixed</option>
 <option value="240">20 Years Fixed</option>
 <option value="180">15 Years Fixed</option>
  <option value="120">10/1 ARM</option>
 <option value="84">7/1 ARM</option>
 <option value="60">5/1 ARM</option>
 <option value="36">3/1 ARM</option>
  <option  value="" ></option>
 </select>
 <label id="termMsg" class="errorMsg"></label> 
 <br>

 
 
</fieldset>
 

<fieldset >
 <legend><span> Input New Loan Information</span> </legend>
 
  <label class="compareLoanTitle1">Loan1:</label>
  <label class="compareLoanTitle2">Loan2:</label>
  <label class="compareLoanTitle3">Loan3:</label><br>
 
 <label class="compareLoanLabel">Term:</label>
 <select  name="term1" id="term1" class="compareLoanData" >
 <option  value="" selected="selected"></option>
 <option  value="360" >30 Years Fixed</option>
 <option value="240">20 Years Fixed</option>
 <option value="180">15 Years Fixed</option>
  <option value="120">10/1 ARM</option>
 <option value="84">7/1 ARM</option>
 <option value="60">5/1 ARM</option>
 <option value="36">3/1 ARM</option>
 </select>  
 <select name="term2" id="term2" class="compareLoanData"  >
 <option value="" selected="selected">
 <option  value="360" >30 Years Fixed</option>
 <option value="240">20 Years Fixed</option>
 <option value="180">15 Years Fixed</option>
  <option value="120">10/1 ARM</option>
 <option value="84">7/1 ARM</option>
 <option value="60">5/1 ARM</option>
 <option value="36">3/1 ARM</option>
 </select>
  <select name="term3" id="term3" class="compareLoanData"  >
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


 <label class="compareLoanLabel" id="int_rateLabel" > Interest Rate(%) </label>
  <input id="int_rate1"  class="compareLoanData"  type="text" size="25" name="int_rate1"/>
  <input id="int_rate2"  class="compareLoanData"  type="text" size="25" name="int_rate2" />
 <input id="int_rate3"  class="compareLoanData"  type="text" size="25" name="int_rate3" />
  

 <br>

<label class="compareLoanLabel" id="closing_costLabel" > Closing Cost($) </label>
<input id="closing_cost1"  class="compareLoanData" type="text" size="25" name="closing_cost1" />
<input id="closing_cost2" class="compareLoanData" type="text" size="25" name="closing_cost2" />
<input id="closing_cost3" class="compareLoanData"  type="text" size="25" name="closing_cost3" />
<br>

<label id="pointLabel" class="compareLoanLabel">Points(%) </label>
<input id="point1"  class="compareLoanData"  type="text" size="25" name="point1" />
<input id="point2"  class="compareLoanData"  type="text" size="25" name="point2" />
<input id="point3"  class="compareLoanData" type="text" size="25" name="point3" />
<br>
 

<label id="sample" class="compareLoanLabel">Sample Data</label>
<!-- a class="noUnderline" href="javascript:newLoanExample('1')"><span class="linkbutton">Sample Data 1 </span></a>
<a class="noUnderline" href="javascript:newLoanExample('2')"><span class="linkbutton">Sample Data 2 </span></a>
<a class="noUnderline" href="javascript:newLoanExample('3')"><span class="linkbutton">Sample Data 3 </span></a>
-->
<input type="button" class="buttonImage" value="Sample Data 1" onclick="javascript:newLoanExample('1');">
<input type="button" class="buttonLeft" value="Sample Data 2" onclick="javascript:newLoanExample('2');">
<input type="button" class="buttonLeft" value="Sample Data 3" onclick="javascript:newLoanExample('3');">
 
<br>



</fieldset>
 
	<div style="padding-left:160px;">        		    
      <table>
       <tr><td>
      	<input type="button" class="buttonImage" value="Compare" onclick="javascript:submitCompareForm('comparePurchOnLine');">
      	</td><td>
		<input type="button" class="buttonLeft" value="Cancel" onclick="javascript:cancelform();">
		</td></tr>
    </table>  	
    
      	<br>
     
 	</div>   
 	 </form> 
 	
</div>
   


 
 



 
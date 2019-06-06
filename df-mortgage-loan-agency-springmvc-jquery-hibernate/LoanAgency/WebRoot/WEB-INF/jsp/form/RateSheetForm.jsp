 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<style type="text/css">
  .inputDisabled {
     background-color:rgb(204,255,255);
     border:0px;
     disabled:disabled;
     
  }
</style>
<div class="fullscreen" > 
  <div id="errorBox" class="errorblock"></div>
 <form:form  method="POST"  name="rateSheetForm"  commandName="rateSheetForm">
  <fieldset style="margin: 20px 20px 20px 20px;border:solid 2px lightblue">  
       <legend><span class="AccountCreateTableHeader"> Dear  <logic:present name="loginAgentName" scope="session"><span style="color: rgb(168, 0, 26);"><bean:write name="loginAgentName"/></span></logic:present>, please enter today's interest rate:(Red star fields are required, others optional)</span> </legend>
    
  <table style="width: 96%;  text-align: center;" border="1" cellpadding="0" cellspacing="0">
    <tbody>
         
      <tr>
        <td style="text-align: center; background-color: rgb(102, 255, 255);">Term/Loan Amount</td>
        <td style="text-align: center; background-color: rgb(102, 255, 255);">&lt;$417,000<font color="red">*</font></td>
        <td style="text-align: center; background-color: rgb(102, 255, 255);">$417,500~$625,000<font color="red">*</font></td>
        <td style="text-align: center; background-color: rgb(102, 255, 255);">$625,500~$2M</td>
        <td style="text-align: center; background-color: rgb(102, 255, 255);">Credit Score</td>
        <td style="text-align: center; background-color: rgb(102, 255, 255);">Loan To Value(%)</td>
      </tr>
      <tr>
        <td style="background-color: rgb(204, 255, 255); text-align: center;">30 yrs fixed Rate<font color="red">*</font></td>
        <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="15" id="conf30yr" path="conf30yr" /></td>
        <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="20" id="superConf30yr" path="superConf30yr" /></td>
        <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="15" id="jumbo30yr" path="jumbo30yr"/></td>
        <td style="text-align: center; background-color: rgb(204, 255, 255);"><form:input size="12" id="credit30yr" path="credit30yr"/></td>
        <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input id="LTV30yr" size="12" path="LTV30yr"/></td>
      </tr>

       <tr>
         <td style="background-color: rgb(204, 255, 255); text-align: center;">APR</td>
         <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="15" id="conf30apr" path="conf30apr" /></td>
         <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="20" id="superConf30apr" path="superConf30apr" /></td>
         <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="15" id="jumbo30apr" path="jumbo30apr"/></td>
         <td style="text-align: center; background-color: rgb(204, 255, 255);"></td>
         <td style="background-color: rgb(204, 255, 255); text-align: center;"></td>
       </tr>

      <tr>
        <td style="background-color: rgb(204, 255, 255); text-align: center;">15 yrs fixed Rate<font color="red">*</font></td>
        <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="15" id="conf15yr" path="conf15yr" /></td>
        <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="20" id="superConf15yr" path="superConf15yr" /></td>
        <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="15" id="jumbo15yr" path="jumbo15yr"/></td>
        <td style="text-align: center; background-color: rgb(204, 255, 255);"><form:input size="12" id="credit15yr" path="credit15yr"/></td>
        <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input id="LTV15yr" size="12" path="LTV15yr"/></td>
      </tr>
          <tr>
	       <td style="background-color: rgb(204, 255, 255); text-align: center;">APR</td>
	       <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="15" id="conf15apr" path="conf15apr" /></td>
	       <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="20" id="superConf15apr" path="superConf15apr" /></td>
	       <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="15" id="jumbo15apr" path="jumbo15apr"/></td>
	       <td style="text-align: center; background-color: rgb(204, 255, 255);"></td>
	       <td style="background-color: rgb(204, 255, 255); text-align: center;"></td>
        </tr>
     <tr>
        <td style="background-color: rgb(204, 255, 255); text-align: center;">10/1 Fixed Rate</td>
        <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="15" id="conf10yr" path="conf10yr" /></td>
        <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="20" id="superConf10yr" path="superConf10yr" /></td>
        <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="15" id="jumbo10yr" path="jumbo10yr"/></td>
        <td style="text-align: center; background-color: rgb(204, 255, 255);"><form:input size="12" id="credit10yr" path="credit10yr"/></td>
        <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input id="LTV10yr" size="12" path="LTV10yr"/></td>
      </tr>
          <tr>
	        <td style="background-color: rgb(204, 255, 255); text-align: center;">APR</td>
	        <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="15" id="conf10apr" path="conf10apr" /></td>
	        <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="20" id="superConf10apr" path="superConf10apr" /></td>
	        <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="15" id="jumbo10apr" path="jumbo10apr"/></td>
	        <td style="text-align: center; background-color: rgb(204, 255, 255);"></td>
	        <td style="background-color: rgb(204, 255, 255); text-align: center;"></td>
        </tr>

      <tr>
        <td style="background-color: rgb(204, 255, 255); text-align: center;">7/1 ARM Rate</td>
        <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="15" id="conf7yr" path="conf7yr" /></td>
        <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="20" id="superConf7yr" path="superConf7yr" /></td>
        <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="15" id="jumbo7yr" path="jumbo7yr"/></td>
        <td style="text-align: center; background-color: rgb(204, 255, 255);"><form:input size="12" id="credit7yr" path="credit7yr"/></td>
        <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input id="LTV7yr" size="12" path="LTV7yr"/></td>
      </tr>

        <tr>
          <td style="background-color: rgb(204, 255, 255); text-align: center;">APR</td>
          <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="15" id="conf7apr" path="conf7apr" /></td>
          <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="20" id="superConf7apr" path="superConf7apr" /></td>
          <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="15" id="jumbo7apr" path="jumbo7apr"/></td>
          <td style="text-align: center; background-color: rgb(204, 255, 255);"></td>
          <td style="background-color: rgb(204, 255, 255); text-align: center;"></td>
        </tr>


       <tr>
          <td style="background-color: rgb(204, 255, 255); text-align: center;">5/1 ARM Rate</td>
          <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="15" id="conf5yr" path="conf5yr" /></td>
          <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="20" id="superConf5yr" path="superConf5yr" /></td>
          <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="15" id="jumbo5yr" path="jumbo5yr"/></td>
          <td style="text-align: center; background-color: rgb(204, 255, 255);"><form:input size="12" id="credit5yr" path="credit5yr"/></td>
          <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input id="LTV5yr" size="12" path="LTV5yr"/></td>
      </tr>

        <tr>
          <td style="background-color: rgb(204, 255, 255); text-align: center;">APR</td>
          <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="15" id="conf5apr" path="conf5apr" /></td>
          <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="20" id="superConf5apr" path="superConf5apr" /></td>
          <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="15" id="jumbo5apr" path="jumbo5apr"/></td>
          <td style="text-align: center; background-color: rgb(204, 255, 255);"></td>
          <td style="background-color: rgb(204, 255, 255); text-align: center;"></td>
        </tr>



      <tr>
        <td style="background-color: rgb(204, 255, 255); text-align: center;">3/1 ARM Rate</td>
        <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="15" id="conf3yr" path="conf3yr" /></td>
	    <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="20" id="superConf3yr" path="superConf3yr" /></td>
	    <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="15" id="jumbo3yr" path="jumbo3yr"/></td>
	    <td style="text-align: center; background-color: rgb(204, 255, 255);"><form:input size="12" id="credit3yr" path="credit3yr"/></td>
        <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input id="LTV3yr" size="12" path="LTV3yr"/></td>
      </tr>

        <tr>
          <td style="background-color: rgb(204, 255, 255); text-align: center;">APR</td>
          <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="15" id="conf3apr" path="conf3apr" /></td>
          <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="20" id="superConf3apr" path="superConf3apr" /></td>
          <td style="background-color: rgb(204, 255, 255); text-align: center;"><form:input size="15" id="jumbo3apr" path="jumbo3apr"/></td>
          <td style="text-align: center; background-color: rgb(204, 255, 255);"></td>
          <td style="background-color: rgb(204, 255, 255); text-align: center;"></td>
        </tr>

</tbody>

  </table>
   </fieldset>
  
     <div style="margin-left:20px;margin-bottom:10px">
        <input type="button" value="Save Rate" class="buttonImage" onClick="javascript: submitRateSheetform();">
    	<!-- <a  href="javascript: submitRateSheetform();"><img style="border:0px;" src="<%=request.getContextPath()%>/images/formImages/b_submit.png" title="Submit Rate Sheet" width="80" height="28" /></a>-->
     </div>          
</form:form>
 </div>
          
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/form/rateSheetForm.js"> </script>  
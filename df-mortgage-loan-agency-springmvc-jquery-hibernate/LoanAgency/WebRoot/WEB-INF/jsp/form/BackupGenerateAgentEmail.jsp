 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
<style type="text/css">
  .fieldsetClass {
     margin: 20px 20px 20px 20px;border:solid 2px lightblue;
    }
     
  
  .tableTitleClass {
       background-color: rgb(102, 255, 255);padding-left:10px;font-weight:bold;font-size:14px;
  }
  .tdLeftClasss {
       background-color: rgb(225, 255, 255); padding-left:10px;
  }
  .spanClass {
  	   font-size:12px;padding-left:10px;
  }
</style>

 

<div style="background-color: rgb(225, 255, 255); padding-left:10px;" > 
   <fieldset style="margin: 20px 20px 20px 20px;border:solid 2px lightblue;">  
      <legend></legend>
      
        Greeting to you and wish you are doing good !<br>
        My name is  
                <bean:write name="agentProfile" property="firstName"/>&nbsp;
                 <bean:write name="agentProfile" property="lastName"/>&nbsp;. 
                 I am a senior loan officer who is working  
                 <logic:present name="agentProfile" property="companyName">
                    in <bean:write name="agentProfile" property="companyName"/>
                 </logic:present>
                  <logic:present name="agentProfile" property="state">
                    and living in  <br><bean:write name="agentProfile" property="city"/>, <bean:write name="agentProfile" property="state"/>.<br>
                 </logic:present>
                  The reason I email you is that I just bring existing news about free mortgage loan <br>
                  services and updated interest rate on my professional web site.<br>
                  If you are planing to purchasing a home or do refinance for your home or just want to<br>
                  refresh the knowledge about amortization, loan comparison, estimate affordable finance <br>
                  based on income and debt or estimate monthly home expense, update loan policy or prepare<br>
                  loan documents, please click on below links in the table 1, you are able to get satisfied <br>
                  answers. <br>
                  if you click on links, you can open my customized website to find more useful information. <br>
                  In order to get more information, you can directly send purchase or refinance quotes to me <br>
                  by clicking on quote links.<br>
                 
                  If you want to know current Interest Rate I am providing currently, please go through below table 2.<br>
                 
                   
                  Again , if you have any question, feel free to call , email me or open my website by one click.
                 <br> <br> 
                 
                 Thanks and best regards<br>
                
                <table style="width: 96%;  text-align: left;" border="0" cellpadding="3" cellspacing="0">
               <tr><td style="padding-left:30px;">
                 <bean:write name="agentProfile" property="firstName"/>&nbsp;
                 <bean:write name="agentProfile" property="lastName"/>&nbsp;<br><br>
                  
                  Senior Loan Officer<br>
                  
                  <logic:present name="agentProfile" property="companyName">
                   <bean:write name="agentProfile" property="companyName"/><br>
                 </logic:present>      
                
                
              
                 
                    <bean:write name="agentProfile" property="address"/><br>
                 
                    <bean:write name="agentProfile" property="city"/>&nbsp;
                  
                    <bean:write name="agentProfile" property="state"/>&nbsp;
                
                    <bean:write name="agentProfile" property="zipCode"/>&nbsp;
                <br>
                 
                  Office Tel:   <bean:write name="agentProfile" property="workPhone"/><br>
                  
                 
                  Cell Phone:   <bean:write name="agentProfile" property="cellPhone"/><br>
                  
                  
                 LicenseNo:     <bean:write name="agentProfile" property="licenseNo"/><br>
                 
                 License Eligible State:     <bean:write name="agentProfile" property="licenseEligibleState"/><br>
               <br>
                  
               </td>
                  <logic:present name="agentProfile" property="pictureFilename">
                  <td>
               
                      <img width="145" height="185" alt="" id="agentImage" src="http://<bean:write name='hostName'/>/previewPicture.html" />
                     <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="agentProfile" property="firstName"/>&nbsp;
                    <bean:write name="agentProfile" property="lastName"/>&nbsp;    
 		      	  </td>
                  </logic:present>
               </tr>  
             </table>       
                 
                 
    </fieldset>
 </div>
 
<div class="fullscreen" > 
  
   <fieldset style="margin: 20px 20px 20px 20px;border:solid 2px lightblue;">  
      <legend><span class="AccountCreateTableHeader">Links for Quotes,  Calculators, Utilities and Agent Detail Information       
      </span> 
    </legend>
    
  <table style="width: 96%;  text-align: left;" border="1" cellpadding="0" cellspacing="0">
    <tbody>
         
      <tr>
        <td style="background-color: rgb(102, 255, 255);padding-left:10px;font-weight:bold;font-size:14px;;">Click on below links to view calculator and agent detail information</td>
      
      </tr>
      </tbody>
      
      <logic:present name="agentEmailLinks">      
    
    <tr> 
         <td style="background-color: rgb(225, 255, 255); padding-left:10px;">
        
           <a href="javascript:window.open('<bean:write name="agentEmailLinks" property="purchaseQuoteLink"/>','editLine','1360','800').focus();">
               <span style="font-size:12px;padding-left:10px;"><bean:write name="agentEmailLinks" property="purchaseQuoteStr"/></span> 
           </a>
         </td>        
      </tr>
      
      <tr> 
         <td style="background-color: rgb(225, 255, 255); padding-left:10px;">
           <a href="javascript:window.open('<bean:write name="agentEmailLinks" property="refinanceQuoteLink"/>','editLine','1360','800').focus();">
               <span style="font-size:12px;padding-left:10px;"><bean:write name="agentEmailLinks" property="refinanceQuoteStr"/></span> 
           </a>
         </td>        
      </tr>
    
      <tr> 
         <td style="background-color: rgb(225, 255, 255); padding-left:10px;">
           <a href="javascript:window.open('<bean:write name="agentEmailLinks" property="monthlyAmortLink"/>','editLine','1360','800').focus();">
               <span style="font-size:12px;padding-left:10px;"><bean:write name="agentEmailLinks" property="monthlyAmortStr"/></span> 
           </a>
         </td>        
      </tr>
      <tr> 
         <td style="background-color: rgb(225, 255, 255); padding-left:10px;">
           <a href="javascript:window.open('<bean:write name="agentEmailLinks" property="yearlyAmortLink"/>','editLine','1360','800').focus();">
               <span style="font-size:12px;padding-left:10px;"><bean:write name="agentEmailLinks" property="yearlyAmortStr"/></span> 
           </a>
         </td>        
      </tr>
      <tr> 
         <td style="background-color: rgb(225, 255, 255); padding-left:10px;">
           <a href="javascript:window.open('<bean:write name="agentEmailLinks" property="refinanceLoansComparerLink"/>','editLine','1360','800').focus();">
               <span style="font-size:12px;padding-left:10px;"><bean:write name="agentEmailLinks" property="refinanceLoansComparerStr"/></span> 
           </a>
         </td>        
      </tr>
      <tr> 
         <td style="background-color: rgb(225, 255, 255); padding-left:10px;">
           <a href="javascript:window.open('<bean:write name="agentEmailLinks" property="monthlyExpenseLink"/>','editLine','1360','800').focus();">
               <span style="font-size:12px;padding-left:10px;"><bean:write name="agentEmailLinks" property="monthlyExpenseStr"/></span> 
           </a>
         </td>        
      </tr>
      <tr> 
         <td style="background-color: rgb(225, 255, 255); padding-left:10px;">
           <a href="javascript:window.open('<bean:write name="agentEmailLinks" property="affordableCapabilityLink"/>','editLine','1360','800').focus();">
               <span style="font-size:12px;padding-left:10px;"><bean:write name="agentEmailLinks" property="affordableCapabilityStr"/></span> 
           </a>
         </td>        
      </tr>
      <tr> 
         <td style="background-color: rgb(225, 255, 255); padding-left:10px;">
           <a href="javascript:window.open('<bean:write name="agentEmailLinks" property="puschaseLoansComparerLink"/>','editLine','1360','800').focus();">
               <span style="font-size:12px;padding-left:10px;"><bean:write name="agentEmailLinks" property="puschaseLoansComparerStr"/></span> 
           </a>
         </td>        
      </tr>
      <tr> 
         <td style="background-color: rgb(225, 255, 255); padding-left:10px;">
           <a href="javascript:window.open('<bean:write name="agentEmailLinks" property="loadEducationLink"/>','editLine','1360','800').focus();">
               <span style="font-size:12px;padding-left:10px;"><bean:write name="agentEmailLinks" property="loadEducationStr"/></span> 
           </a>
         </td>        
      </tr>
      
      <tr> 
         <td style="background-color: rgb(225, 255, 255); padding-left:10px;">
           <a href="javascript:window.open('<bean:write name="agentEmailLinks" property="downloadApplicationFormLink"/>','editLine','1360','800').focus();">
               <span style="font-size:12px;padding-left:10px;"><bean:write name="agentEmailLinks" property="downloadApplicationFormStr"/></span> 
           </a>
         </td>        
      </tr>
      <tr> 
         <td style="background-color: rgb(225, 255, 255); padding-left:10px;">
           <a href="javascript:window.open('<bean:write name="agentEmailLinks" property="freeCreditScoreLink"/>','editLine','1360','800').focus();">
               <span style="font-size:12px;padding-left:10px;"><bean:write name="agentEmailLinks" property="freeCreditScoreStr"/></span> 
           </a>
         </td>        
      </tr>                                                        
   
   
   
   </logic:present>
  
  </table>
   </fieldset>
   </div>
   <div class="fullscreen" > 
  <fieldset style="margin: 20px 20px 20px 20px;border:solid 2px lightblue;">  
      <legend><span class="AccountCreateTableHeader"> Mortgage Loan Agent:  
            <logic:present name="agentProfile" scope="session">
             <font style="color:rgb(142,17,70);"> 
                 <bean:write name="agentProfile" property="firstName"/>&nbsp;
                 <bean:write name="agentProfile" property="lastName"/>&nbsp; 
              </font>
            </logic:present>provided Interested Rate:
      </span> 
    </legend>
    
  <table style="width: 96%;  text-align: left;" border="1" cellpadding="0" cellspacing="0">
    <tbody>
         
      <tr>
        <td style="background-color: rgb(102, 255, 255);padding-left:10px;font-weight:bold;font-size:14px;;">Term/Loan Amount</td>
        <td style="background-color: rgb(102, 255, 255);padding-left:10px;font-weight:bold;font-size:14px;;">&lt;$417,000<font color="red">*</font></td>
        <td style="background-color: rgb(102, 255, 255);padding-left:10px;font-weight:bold;font-size:14px;;">$417,500~$625,000<font color="red">*</font></td>
        <td style="background-color: rgb(102, 255, 255);padding-left:10px;font-weight:bold;font-size:14px;;">$625,500~$2M</td>
        <td style="background-color: rgb(102, 255, 255);padding-left:10px;font-weight:bold;font-size:14px;;">Credit Score</td>
        <td style="background-color: rgb(102, 255, 255);padding-left:10px;font-weight:bold;font-size:14px;;">Loan To Value(%)</td>
      </tr>
      </tbody>
      
      <logic:present name="InterestRateList">
      <logic:iterate id="intRate" name="InterestRateList" type="com.loan.agent.dao.RateSheet">
     
      <tr>
        <td style="background-color: rgb(225, 255, 255); padding-left:10px;"><bean:write name="intRate" property="term"/></td>
        <td style="background-color: rgb(225, 255, 255); padding-left:10px;"><bean:write name="intRate" property="conformRate"/></td>
        <td style="background-color: rgb(225, 255, 255); padding-left:10px;"><bean:write name="intRate" property="superConformRate"/></td>
        <td style="background-color: rgb(225, 255, 255); padding-left:10px;"><bean:write name="intRate" property="jumboRate"/></td>
        <td style="background-color: rgb(225, 255, 255); padding-left:10px;"><bean:write name="intRate" property="creditScore"/></td>
        <td style="background-color: rgb(225, 255, 255); padding-left:10px;"><bean:write name="intRate" property="loanToValue"/></td>
      </tr>

       <tr>
         <td style="background-color: rgb(225, 255, 255); padding-left:10px;">APR</td>
         <td style="background-color: rgb(225, 255, 255); padding-left:10px;"><bean:write name="intRate" property="jumboApr"/></td>
         <td style="background-color: rgb(225, 255, 255); padding-left:10px;"><bean:write name="intRate" property="conformApr"/></td>
         <td style="background-color: rgb(225, 255, 255); padding-left:10px;"><bean:write name="intRate" property="superConformApr"/></td>
         <td style="background-color: rgb(225, 255, 255); padding-left:10px;"></td>
         <td style="background-color: rgb(225, 255, 255); padding-left:10px;"></td>
       </tr>
       </logic:iterate>
      </logic:present>
  
  </table>
   </fieldset>
          
 
 </div>
          
   
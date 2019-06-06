 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>
 
<style type="text/css">
  .fieldsetClass {
     margin: 20px 20px 20px 20px;border:solid 2px lightblue;
    }
     
  
  .tableTitleClass {
       background-color: rgb(221,243,249);padding-left:10px;font-weight:bold;font-size:14px;
  }
  .tdLeftClasss {
       background-color: rgb(225, 255, 255); padding-left:10px;color:black;
  }
  .spanClass {
  	   font-size:12px;padding-left:10px;
  }
  
</style>

 

<div style="background-color: rgb(225, 255, 255); padding-left:10px;color:black;" > 
   <fieldset style="margin: 20px 20px 20px 20px;border:solid 2px lightblue;">  
      <legend></legend>
        <br>
        How are you ?<br>
        Wish you are doing well !<br>
      <!--   My name is  
                <bean:write name="agentProfile" property="firstName"/>&nbsp;
                 <bean:write name="agentProfile" property="lastName"/>&nbsp;. 
                 I am a senior loan officer who is working  
                 <logic:present name="agentProfile" property="companyName">
                    in <bean:write name="agentProfile" property="companyName"/>
                 </logic:present>
                  <logic:present name="agentProfile" property="state">
                    and living in  <br><bean:write name="agentProfile" property="city"/>, <bean:write name="agentProfile" property="state"/>.<br>
                 </logic:present><br> -->
              
               	    <logic:present name="selectedAppCheckList" scope="session">
               	        <br> <strong>Please submit following loan documents:</strong><br>
               	        <table border="1" cellpadding="2" cellspacing="0"  style="width: 96%;  text-align: left;">
               	        
               	         <logic:iterate id="checkList" name="selectedAppCheckList" type="com.loan.agent.dao.AppCheckList">
               	           <tr>
               	               <td>
               	                    <strong><bean:write name="checkList" property="title"/></strong>
               	                     <bean:write name="checkList" property="note"/>
               	               </td>
               	           </tr>          
               	         </logic:iterate>
               	        </table>    
               	    </logic:present>
		            <logic:present name="quoteForm" scope="session">
		            <br> <strong>You recently sent me quote, detail as following shown</strong>
                        <logic:equal name="quoteForm" property="loanType" value="Purchase" >
      		         	<jsp:include page="PurchaseQuoteSuccess.jsp" flush="true" />
      		         	</logic:equal>
      		         	<logic:equal name="quoteForm" property="loanType" value="Refinance" >
      		         	<jsp:include page="RefinanceQuoteSuccess.jsp" flush="true" />
      		         	</logic:equal>
		      		</logic:present>
		      		 
		      		 <br> 
               
                    <logic:present name="NicheProgramId">
                       <strong>New services:</strong>                     
                      * Exclusive and distinctive Niche Loan Programs to meet your special needs.<br>
                    </logic:present>
                    
                     <logic:present name="InteratRateId">
                      <strong>Rate services:</strong>     
                      * Updated Interest Rate.<br>
                    </logic:present>
                    
                     <logic:present name="LoansAgentLinksId">
                      <strong>Loan-Agent.com services:</strong>     
                      * Loans-Agent.com Website Links to help you quote Refinance & Purchase from me and bunch of Loan Calculators .<br>
                     </logic:present>                       <logic:present name="ApplicationFormId">
                       
                      * Available Application Form Download.<br><br>
                     </logic:present>
                    
                      If any questions or need some assists , please don't hesitate to contact me. <br>                      
                      
                      Looking forward to hearing from your soon.<br>                     
                      
                      
                 <br> <br> 
                 
                 Thanks and best regards<br><br>
                
                <table style="width: 96%;  text-align: left;" border="0" cellpadding="3" cellspacing="0">
               <tr><td style="padding-left:30px;">
               <strong>  <bean:write name="agentProfile" property="firstName"/>&nbsp;
                 <bean:write name="agentProfile" property="lastName"/>&nbsp;<br><br>
                </strong> 
                  
                  Senior Loan Officer<br>
                  
                  <logic:present name="agentProfile" property="companyName">
                   <bean:write name="agentProfile" property="companyName"/><br>
                 </logic:present>      
                
                   
                    <bean:write name="agentProfile" property="address"/><br>
                 
                    <bean:write name="agentProfile" property="city"/>&nbsp;
                  
                    <bean:write name="agentProfile" property="state"/>&nbsp;
                
                    <bean:write name="agentProfile" property="zipCode"/>&nbsp;
                <br>
                
                  Email Address: <bean:write name="agentProfile" property="emailAddress"/><br>
                
                  Office Tel:   <bean:write name="agentProfile" property="workPhone"/><br>
                  
                 
                  Cell Phone:   <bean:write name="agentProfile" property="cellPhone"/><br>
                  
                  
                 DRE No:     <bean:write name="agentProfile" property="dreNo"/><br>
                 
                 NMLS No:     <bean:write name="agentProfile" property="nmlsNo"/><br>
                 
                 License Eligible State:     <bean:write name="agentProfile" property="licenseEligibleState"/><br>
               <br>
                  
               </td>
                <logic:present name="AgentPictureId">
                  <logic:present name="agentProfile" property="pictureFilename">
                  <td>
               
                 
                     <img width="145" height="185" alt="" id="agentImage" src='<bean:write name="agentEmailPictureURL"/>'>
                     
                     <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<bean:write name="agentProfile" property="firstName"/>&nbsp;
                    <bean:write name="agentProfile" property="lastName"/>&nbsp;    
 		      	  </td>
                  </logic:present>
                  </logic:present>
               </tr>  
             </table>       
                 
                 
    </fieldset>
 </div>


 <logic:present name="ApplicationFormId" >  
         	<br>	 	  		 
   		 	&nbsp;<strong>Please click on:</strong> 
   		 	<br>
   		 
   <table style="width: 96%;  text-align: left;" border="0" cellpadding="10" cellspacing="0">
   
      
      <logic:present name="agentEmailLinks">      
        <tr> 
         <td style="background-color: rgb(255,255,255); padding-left:10px;color:black;">
           <a style="color:black;" href='<bean:write name="agentEmailLinks" property="downloadApplicationFormLink"/>'>
               <span style="font-size:12px;padding-left:10px;"><bean:write name="agentEmailLinks" property="downloadApplicationFormStr"/></span> 
           </a> 
         </td>        
      </tr>
               
    <tr> 
         <td style="background-color: rgb(255,255,255); padding-left:10px;color:black;color:black;">
     
           <a style="color:black;" href='<bean:write name="agentEmailLinks" property="purchaseQuoteLink"/>'>
               <span style="font-size:12px;padding-left:10px;"><bean:write name="agentEmailLinks" property="purchaseQuoteStr"/></span> 
           </a> 
         
         </td>        
      </tr>
      
      <tr> 
         <td style="background-color: rgb(255,255,255); padding-left:10px;color:black;">
           <a style="color:black;" href='<bean:write name="agentEmailLinks" property="refinanceQuoteLink"/>'>
               <span style="font-size:12px;padding-left:10px;"><bean:write name="agentEmailLinks" property="refinanceQuoteStr"/> </span> 
           </a> 
         </td>        
      </tr>     
                                       
   
   
   
   </logic:present>
  
  </table>   
	  
     
        
 </logic:present>
 <br>
 <logic:present name="InteratRateId" >     
   <div class="fullscreen" > 
  <fieldset style="margin: 20px 20px 20px 20px;border:solid 2px lightblue;">  
      <legend><strong>Loan officer:  
            <logic:present name="agentProfile" scope="session">
             <font style="color:rgb(142,17,70);"> 
                 <bean:write name="agentProfile" property="firstName"/>&nbsp;
                 <bean:write name="agentProfile" property="lastName"/>&nbsp; 
              </font>
            </logic:present>provided Interested Rate:  
      </strong>
    </legend>
    
  <table style="width: 96%;  text-align: left;" border="1" cellpadding="4" cellspacing="0">
    <tbody>
         
      <tr>
        <td style="background-color: rgb(153, 255, 255);padding-left:10px;font-weight:bold;font-size:14px;;">Term/Loan Amount</td>
        <td style="background-color: rgb(153, 255, 255);padding-left:10px;font-weight:bold;font-size:14px;;">&lt;$417,000<font color="red">*</font></td>
        <td style="background-color: rgb(153, 255, 255);padding-left:10px;font-weight:bold;font-size:14px;;">$417,500~$625,000<font color="red">*</font></td>
        <td style="background-color: rgb(153, 255, 255);padding-left:10px;font-weight:bold;font-size:14px;;">$625,500~$2M</td>
        <td style="background-color: rgb(153, 255, 255);padding-left:10px;font-weight:bold;font-size:14px;;">Credit Score</td>
        <td style="background-color: rgb(153, 255, 255);padding-left:10px;font-weight:bold;font-size:14px;;">Loan To Value(%)</td>
      </tr>
      </tbody>
      
      <logic:present name="InterestRateList">
      <logic:iterate id="intRate" name="InterestRateList" type="com.loan.agent.dao.RateSheet">
     
      <tr>
        <td style="background-color: rgb(255, 255, 255); padding-left:10px;color:black;"><bean:write name="intRate" property="term"/></td>
        <td style="background-color: rgb(255, 255, 255); padding-left:10px;color:black;"><bean:write name="intRate" property="conformRate"/></td>
        <td style="background-color: rgb(255, 255, 255); padding-left:10px;color:black;"><bean:write name="intRate" property="superConformRate"/></td>
        <td style="background-color: rgb(255, 255, 255); padding-left:10px;color:black;"><bean:write name="intRate" property="jumboRate"/></td>
        <td style="background-color: rgb(255, 255, 255); padding-left:10px;color:black;"><bean:write name="intRate" property="creditScore"/></td>
        <td style="background-color: rgb(255, 255, 255); padding-left:10px;color:black;"><bean:write name="intRate" property="loanToValue"/></td>
      </tr>

       <tr>
         <td style="background-color: rgb(221,243,249); padding-left:10px;color:black;">APR</td>
         <td style="background-color: rgb(221,243,249); padding-left:10px;color:black;"><bean:write name="intRate" property="jumboApr"/></td>
         <td style="background-color: rgb(221,243,249); padding-left:10px;color:black;"><bean:write name="intRate" property="conformApr"/></td>
         <td style="background-color: rgb(221,243,249); padding-left:10px;color:black;"><bean:write name="intRate" property="superConformApr"/></td>
         <td style="background-color: rgb(221,243,249); padding-left:10px;color:black;"></td>
         <td style="background-color: rgb(221,243,249); padding-left:10px;color:black;"></td>
       </tr>
       </logic:iterate>
      </logic:present>
  
  </table>
   </fieldset>    
 
 </div>
 </logic:present>
 <br>
 <logic:present name="NicheProgramId" >  
 
 <br>
   
    <strong>   
      
                 <bean:write name="agentProfile" property="firstName"/>&nbsp;
                 <bean:write name="agentProfile" property="lastName"/>&nbsp; 
              
      Exclusively Niche Loan Program   <br> 
       
           
   </strong>
     <logic:present name="agentProfile" property="nicheIntro">  
          
       
        <table cellpadding="4" cellspacing="0">
          <thead>
  			<tr>   		 	  		 
   		 	<td style="background-color: rgb(153, 255, 255); text-align: center;" width="90%">Niche Loan Program Introduction</td>
   		 	</tr>
   		 	</thead>
   		 	<tbody>
           <tr>   
              <td style="background-color: rgb(255,255,255);" width="95%">
          		 <bean:write name="agentProfile" property="nicheIntro"/>
          	  </td>
          	  </tr>
          	  </tbody>
         </table>
        
        </logic:present>
        <br>
      <logic:present name="NichesList" scope="session" >  
           
     <%int line=0; %>
       <table   width="100%"  cellpadding="4" cellspacing="0" >
     	 <thead>
  			<tr>   		 	  		 
   		 	<td style="background-color: rgb(153, 255, 255); text-align: center;" width="90%">Niche Loan Program Title and Description</td>
   		 	</tr>
   		 	</thead>
 	   	<tbody>
 	   	
 	    
 	 	<logic:iterate id="review" name="NichesList" type="com.loan.agent.mvc.formbean.NicheForm" scope="session" indexId="rowIndex"> 
   	 	    <tr>
  		      <%if (line%2==0) {%>
      			 <td style="background-color: rgb(255,255,255);" width="95%"> 
        	 <%} else { %>
        	    <td style="background-color: rgb(221,243,249);" width="95%">
        	   <%}
        	    line++;        	    
        	    %>
    	 	  
    	   
    	   <strong>
    	      * <bean:write name="review" property="nicheTitle"/> 
    	  </strong>   
    	    
    	     <font style="font-size:12px;color:RGB(50,50,50);">  
    	       
    	   		   <bean:write name="review" property="nicheNote"/> 
    	   		  
    	   	 </font>
    	    </td>                   
    			
  			</tr>
    		 
          </logic:iterate>
         
        </tbody>
 </table>
    
 </logic:present>
  
   
 </logic:present>
 
 <br>
 
 
  <logic:present name="LoansAgentLinksId" >
<div class="fullscreen" > 
 
   <fieldset style="margin: 20px 20px 20px 20px;border:solid 2px lightblue;">  
      <legend><Strong>Links for Mortgage Loan Services</Strong>      
      
    </legend>
   
  <table style="width: 96%;  text-align: left;" border="1" cellpadding="10" cellspacing="0">
    <tbody>
         
      <tr>
        <td style="background-color: rgb(153, 255, 255);padding-left:10px;font-weight:bold;font-size:14px;;">
           <span style="font-size:14px;padding-left:10px;font-weight:bold;"> Click on below links to get mortgage loan services 
           </span>
        </td>
      
      </tr>
      </tbody>
      
      <logic:present name="agentEmailLinks">      
    
    <tr> 
         <td style="background-color: rgb(255,255,255); padding-left:10px;color:black;color:black;">
     
           <a style="color:black;" href='<bean:write name="agentEmailLinks" property="purchaseQuoteLink"/>'>
               <span style="font-size:12px;padding-left:10px;"><bean:write name="agentEmailLinks" property="purchaseQuoteStr"/></span> 
           </a>
         
         </td>        
      </tr>
      
      <tr> 
         <td style="background-color: rgb(225,255,255); padding-left:10px;color:black;">
           <a style="color:black;" href='<bean:write name="agentEmailLinks" property="refinanceQuoteLink"/>'>
               <span style="font-size:12px;padding-left:10px;"><bean:write name="agentEmailLinks" property="refinanceQuoteStr"/> </span> 
           </a>
         </td>        
      </tr>
    
    <!--   <tr> 
         <td style="background-color: rgb(255,255,255); padding-left:10px;color:black;">
           <a style="color:black;" href='<bean:write name="agentEmailLinks" property="monthlyAmortLink"/>'>
               <span style="font-size:12px;padding-left:10px;"><bean:write name="agentEmailLinks" property="monthlyAmortStr"/></span> 
           </a>
         </td>        
      </tr>
      <tr> 
         <td style="background-color: rgb(225, 255, 255); padding-left:10px;color:black;">
           <a style="color:black;" href='<bean:write name="agentEmailLinks" property="yearlyAmortLink"/>'>
               <span style="font-size:12px;padding-left:10px;"><bean:write name="agentEmailLinks" property="yearlyAmortStr"/></span> 
           </a>
         </td>        
      </tr>
      <tr> 
         <td style="background-color: rgb(255,255,255); padding-left:10px;color:black;">
           <a style="color:black;" href='<bean:write name="agentEmailLinks" property="refinanceLoansComparerLink"/>'>
               <span style="font-size:12px;padding-left:10px;"><bean:write name="agentEmailLinks" property="refinanceLoansComparerStr"/></span> 
           </a>
         </td>        
      </tr> -->
      <tr> 
         <td style="background-color: rgb(225,255,255); padding-left:10px;color:black;">
           <a style="color:black;" href='<bean:write name="agentEmailLinks" property="monthlyExpenseLink"/>'>
               <span style="font-size:12px;padding-left:10px;"><bean:write name="agentEmailLinks" property="monthlyExpenseStr"/></span> 
           </a>
         </td>        
      </tr>
      <tr> 
         <td style="background-color: rgb(255,255,255); padding-left:10px;color:black;">
           <a style="color:black;" href='<bean:write name="agentEmailLinks" property="affordableCapabilityLink"/>'>
               <span style="font-size:12px;padding-left:10px;"><bean:write name="agentEmailLinks" property="affordableCapabilityStr"/></span> 
           </a>
         </td>        
      </tr>
 <!--      <tr> 
         <td style="background-color: rgb(225, 255, 255); padding-left:10px;color:black;">
           <a style="color:black;" href='<bean:write name="agentEmailLinks" property="puschaseLoansComparerLink"/>'>
               <span style="font-size:12px;padding-left:10px;"><bean:write name="agentEmailLinks" property="puschaseLoansComparerStr"/></span> 
           </a>
         </td>        
      </tr>-->
      <tr> 
         <td style="background-color: rgb(255,255,255); padding-left:10px;color:black;">
           <a style="color:black;" href='<bean:write name="agentEmailLinks" property="loadEducationLink"/>'>
               <span style="font-size:12px;padding-left:10px;"><bean:write name="agentEmailLinks" property="loadEducationStr"/></span> 
           </a>
         </td>        
      </tr>
      
      <tr> 
         <td style="background-color: rgb(225,255,255); padding-left:10px;color:black;">
           <a style="color:black;" href='<bean:write name="agentEmailLinks" property="downloadApplicationFormLink"/>'>
               <span style="font-size:12px;padding-left:10px;"><bean:write name="agentEmailLinks" property="downloadApplicationFormStr"/></span> 
           </a>
         </td>        
      </tr>
      <tr> 
         <td style="background-color: rgb(255,255,255); padding-left:10px;color:black;">
           <a style="color:black;" href='<bean:write name="agentEmailLinks" property="freeCreditScoreLink"/>'>
               <span style="font-size:12px;padding-left:10px;"><bean:write name="agentEmailLinks" property="freeCreditScoreStr"/></span> 
           </a>
         </td>        
      </tr>                                                        
   
   
   
   </logic:present>
  
  </table>
   </fieldset>
  
   </div>
  
   <br>
     </logic:present>
     
 
          
   
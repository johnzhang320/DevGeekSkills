<%--looking for all database javabean instances which login.jsp created --%>
 
<%@ page language="java" import="com.loan.agent.calculators.vo.YearlyAmortizationVo" %>

<%@ page language="java" import="com.loan.agent.calculators.vo.AmortParamVo" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean" prefix="bean" %>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic" prefix="logic" %>

<%@ page language="java" import="java.util.*" %>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/layout4loan.css" />
 
 <%    
    int page_no=1;  
    int line=0;  
 %>
 

   <html>
    <head>
    <title> Calculate and View Amortization </title>
    </head>
    <body>
 <div class="content">
    <br>
    <logic:present name="aparam"> 
       <bean:define id="summary" name="summaryVo"/> 
     <table class="amertize"  border="1" cellpadding="0" cellspacing="0" width="95%" bgcolor=white>
      
  	<CAPTION ALIGN=TOP><span class="style27"> Yearly Amortization Summary Page </span> </CAPTION> 
 

     <tr>
     <th><label class="title">Loan Amount</label></th>
     <th><label class="title">Interest Rate(%)</label></th>
     <th><label class="title">Term Months</label></th>
     <th><label class="title">Real Pay Months</label></th>
     <th><label class="title">Monthly Payment</label></th>
     <th><label class="title">Total Monthly Payment</label></th>
     <th><label class="title">Total Interest Payment</label></th>
     <th><label class="title">Total Principle Payment</label></th> 
     <th><label class="title">First Payment Day</label></th>
     
     </tr>
      
   <tr class="rowwhite">   
     <td><font size=-1><bean:write name="aparam" property="loanAmount"/></font></td>
     <td><font size=-1><bean:write name="aparam" property="interestRate"/></font></td>
     <td><font size=-1><bean:write name="aparam" property="term"/></font></td>    
      <td><font size=-1><b><bean:write name="summary" property="realPayMonths"/></b></font></td>  
     
      <td><font size=-1><b><bean:write name="summary" property="monthlyPayment"/></b></font></td>        
        <td><font size=-1><b><bean:write name="summary" property="strSummaryMonthPayment"/></b></font></td>                   
      <td><font size=-1><b><bean:write name="summary" property="strSummaryIntPayment"/></b></font></td>   
  	  <td><font size=-1><b><bean:write name="summary" property="strSummaryPrinPayment"/></b></font></td> 
     <td><font size=-1><bean:write name="aparam" property="first_Date"/></font></td>
     </tr>
      </table>
    <br>
    
     <logic:equal name="summary" property="pay_extra" value="yes">
     <table class="amertize"  border="1" cellpadding="0" cellspacing="0" width="90%" bgcolor=white>      
   
     <tr>
     <th><label class="title">Extra Monthly Payment</label></th>
     <th><label class="title">Starting Date</label></th>
     <th><label class="title">Adjusted Monthly Payment</label></th>
     <th><label class="title">Extra Yearly Payment</label></th>
     <th><label class="title">Starting Date</label></th>
     <th><label class="title">Pay one time Only</label></th>
     <th><label class="title">Payment Date</label></th>

     </tr>
     <tr class="rowwhite">   
    
        <td><font size=-1><bean:write name="aparam" property="extra_PMT"/></font></td>
        <td><font size=-1><bean:write name="aparam" property="PMT_Date"/></font></td>
        <td><font size=-1><b><bean:write name="summary" property="adjustedMonthlyPayment"/></b></font></td>        
        <td><font size=-1><bean:write name="aparam" property="extra_YPMT"/></font></td>
        <td><font size=-1><bean:write name="aparam" property="YPMT_Date"/></font></td>
        <td><font size=-1><bean:write name="aparam" property="extra_YPMT_once"/></font></td>
        <td><font size=-1><bean:write name="aparam" property="YPMT_once_Date"/></font></td>
     
   
 
    </tr>
    
   </table>
   </logic:equal>
   </logic:present>
   <br> 
    <logic:present name="yearlyAmortList" >  
    
    
      <table class="amertize"  border="1" cellpadding="0" cellspacing="0" width="93%"bgcolor=white>
        <CAPTION ALIGN=TOP><span class="style27"> Yearly Amortization Scheduler Report(Page:<%=page_no%>) </span> </CAPTION> 
  		<tr>
    	
   		 	<th><label class="title">YearNo</label></th>
    		 <th><label class="title">Year</label></th>
     	 	<th><label class="title">Begin Balance</label></th>
     		<th><label class="title">Yearly Payment</label></th>
     		<th><label class="title">Interest</label></th>
     		<th><label class="title">Principle</label></th>
     		<th><label class="title">Remain Balance</label></th> 
    	</tr>   
   	 	<logic:iterate id="amortItem" name="yearlyAmortList" type="YearlyAmortizationVo" indexId="rowIndex"> 
   	 	   
   	  	   
   	 	     <%if (line%2==0) {%>
      			<tr class="rowwhite">    
        	 <%} else { %>
        	    <tr class="rowshade"> 
        	   <%}
        	    line++;
        	    
        	    %>
    	 	<td><font size=-1><bean:write name="amortItem" property="yearNo"/></font></td>
  			<td><font size=-1><bean:write name="amortItem" property="year"/></font></td>                   
  			 <td><font size=-1><bean:write name="amortItem" property="beginBalance"/></font></td>   
        	<td><font size=-1><bean:write name="amortItem" property="yearlyPayment"/></font></td>                   
      		<td><font size=-1><bean:write name="amortItem" property="interestPayment"/></font></td>                   
      		<td><font size=-1><bean:write name="amortItem" property="principlePayment"/></font></td>  
  			<td><font size=-1><bean:write name="amortItem" property="remainBalance"/></font></td>  
  			</tr>                
      	 
  		            
        </logic:iterate>
         <logic:present name="aparam"> 
         <bean:define id="summary" name="summaryVo"/>
         <tr class="rowwhite"> 
    	 <td><font size=-1>&nbsp</font></td>
  		 <td><font size=-1>&nbsp</font></td>                   
  		 <td><label class="title"><b>Summary</b></font></td>   
  		 <td><font size=-1><b><bean:write name="summary" property="strSummaryMonthPayment"/></b></font></td>                   
      	 <td><font size=-1><b><bean:write name="summary" property="strSummaryIntPayment"/></b></font></td>   
  		 <td><font size=-1>&nbsp</font></td>                   
  		 <td><font size=-1><b><bean:write name="summary" property="strSummaryPrinPayment"/></b></font></td>                   
  		 <td><font size=-1>&nbsp</font></td>                   
  		</tr>
        </logic:present>
       </table>   
        
     </logic:present>
   </div> 
  </body>        
  </html>
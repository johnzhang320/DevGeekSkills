<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

 <!--  <link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/ui-darkness/jquery-ui.css">
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.js"></script> 
	<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.js"></script>
 -->
<!-- Tricky: whatever you download from website searched by google, it has bugs
     Correct way from above links such as http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.js
     copy paste to local file： jquery-1.7.2.js
     
  -->
 <!-- Make the local file for demo when wireless doesn't work!!!! -->
    <link rel="stylesheet" href="jquery-ui-1.8.21.css"></script>
  <script type="text/javascript" src="jquery-1.7.2.js"></script>
  <script type="text/javascript" src="jquery-ui-1.8.21.js"></script>

  
 <!--   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common/formStyle.css" />	
  -->
<%
    String hostName = "http://"+request.getServerName()+"/";
    int portNumber = request.getServerPort();		
	if (portNumber==8080 && hostName.equalsIgnoreCase("localhost")) {
		hostName= "http://localhost:8080/";
	}
	String zipCodeURL=hostName+"WebServiceRESTProject/services/zipCodes/findByZip/";
	System.out.println("zipCodeURL="+zipCodeURL);
%>
 <script type="text/javascript">
    $(document).ready(function () {

        $('#butCallAjax').click(function () {
            jQuery.support.cors = true;


            $.ajax(
                {
                    type: "GET",
                    url: '"http://localhost:8080/WebServiceRESTProject/services/zipCodes/findByZip/94087',
                    data: "{}",
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    success: function (data) {

                        //alert('success');
                        $.each(data, function (key, value) {
                           alert("key="+key+",value="+value);

                        });
                    },
                    error: function (msg, url, line) {
                        alert('error trapped in error: function(msg, url, line)');
                        alert('msg = ' + msg + ', url = ' + url + ', line = ' + line);

                    }
                });


            //alert('button click');

        });
    });
</script>
<script type='text/javascript'>
window.onload = initAll;
var xmlhttp = false;

function initAll() {
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	}
	else {
		if (window.ActiveXObject) {
			try {
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch (e) { }
		}
	}

	 
}
function getdetails(zip) {
        var empno = document.getElementById("empno");
        var url = "http://localhost:8080/WebServiceRESTProject/services/zipCodes/findByZip/" + zip;
        xmlhttp.open('GET',url,true);
        xmlhttp.send(null);
        xmlhttp.onreadystatechange = function() {

                  if (xmlhttp.readyState == 4) {
                  if ( xmlhttp.status == 200) {
                       var zRow = eval( "(" +  xmlhttp.responseText + ")");
                       if (zRow==="") return;
                     
                        
                 }
                 else
                       alert("Error ->" + xmlhttp.responseText);
              }
        };
    }
    
  function purchase() {
        document.getElementById("purchaseDisplay").style.display="block";
         document.getElementById("refinanceDisplay").style.display="none";
   }
 
 function refinance() {
        document.getElementById("purchaseDisplay").style.display="none";
        document.getElementById("refinanceDisplay").style.display="block";
   }
   
   
	$("#loanType").live('change', function() {
	//    alert("display purchase");
    	$("#purchaseDisplay").css("display","block");
   		$("#refinanceDisplay").css("display","none");
	});
   $("#Refinance").live('change',function() {
   // alert("display refinance");
    	$("#purchaseDisplay").css("display","none");
   		$("#refinanceDisplay").css("display","block");
       
	});
   $(function() {
	$("#purchaseDate").datepicker({
		dateFormat: 'mm-dd-yy',
		onSelect: function(selectedDate) {
			$("#purchaseDate").empty().append(selectedDate);
		}
	});
});

  
 function subform() {
  
	   document.forms['quoteForm1'].submit();
	}

$(document).ready(function(){
 
	$('lable').css("padding-left","150px");
 
});
</script>
 
 <!-- id="customerQuoteForm1" -->
 
	 <form:form  method="POST"  name="quoteForm1"  commandName="quotePart1">
	  <input type="hidden" name="zipCodeURL" value="<%=zipCodeURL%>"/>
	 	<!-- <form:errors path="*" cssClass="errorblock" element="div" /> -->
		<div style="width:900px; margin-left:20px;	border:1px #7eafd2 solid; 	background-image: url('images/layoutImages/verticalBlueToWhile.png');	background-repeat:repeat-x; ">
            <div class="caption_box">
                 <span style="color: slategray">Please Enter Your Personal Information:</span>
             </div>
               <fieldset >  
                   <legend><span class="AccountCreateTableHeader">Enter your contact, please:</span> </legend>
                    <img src="<%=request.getContextPath()%>/images/formImages/lock64x64.png"  width="40" height="40"  />
             	<!-- glabol error is validated by action -->
				 
                 <label style="margin-left:20px;width: 150px;"><font color="red">*</font>First Name:</label>
                   
                  <form:input path="firstName"  cssClass="firstName"/> 
		           	  <span style="color:Red"><form:errors path="firstName"  cssClass="error" /></span> <br> 
                      
	              <label><font color="red">*</font>Last Name:</label>
		                              
                 <form:input path="lastName"  maxlength="50"/>
              
         		  <form:errors path="lastName" cssClass="error"/> <br>
           
                  <label><font color="red">*</font>Phone No:</label>
                  <form:input path="phoneNo"   maxlength="80" /> 
		           	  <span style="color:Red"><form:errors path="phoneNo"  cssClass="error" /></span> <br> 

	       
                  <label><font color="red">*</font>Email Address:</label>
                  <form:input path="email"  maxlength="250" cssClass="email" /> 
		           	  <span style="color:Red"><form:errors path="email"  cssClass="error" /></span> <br> 

			 
                  <label><font color="red">*</font>State:</label>
               
                  <form:select path="state" cssClass="width:300px;">
                 	<form:option value="" />
              	   <form:options items="${stateMap}" />
                  </form:select> 
		           	  <span style="color:Red"><form:errors path="state"  cssClass="error" /></span> <br> 

            
                 <label>Gross Annual Income:</label>
                  <form:input path="grossAnnualIncome"  maxlength="250"   /> <br>
		     
              <!--   <form:select path="grossAnnualIncome" cssClass="width:300px;">
                	<form:option value="" />
                	<form:options items="${priceMap}" />
                </form:select><br/>
             -->
             
                <label>Credit Score:</label>
                 <form:input path="creditScore" maxlength="150" cssClass="width:300px;" /><br/>
               <br>
      </fieldset>
                        
    
 
          <fieldset style="margin: 20px 20px 20px 20px;border:solid 2px lightblue">  
              <legend><span class="AccountCreateTableHeader">Enter your quoted loan information:</span> </legend>
  
 	          <img src="<%=request.getContextPath()%>/images/formImages/user_add64x64.png"  width="40" height="40" />
                
                   <label style="margin-left:20px;width: 150px;margin-top:20px">
                       <font color="red">*</font><span style="font-size:13px;">Quoted Loan Type:</span> 
                   </label>                        
                     <div style="margin-top:16px;">
                      <form:radiobutton cssClass="radio" onchange="javascript:refinance();" path="loanType"  value="Refinance" /><label class="small">Refinance </label>
                      <form:radiobutton cssClass="radio" onchange="javascript:purchase();" path="loanType"  value="Purchase" /><label class="small">Purchase</label><br>
                     </div>
    	           	  <span style="color:Red"><form:errors path="loanType"  cssClass="error" /></span> <br> 
                     
                 <div id="purchaseDisplay" style="display: none;"> 
                      <label><font color="red">*</font>Purchase Price:</label>
                   
                       <form:select path="purchasePrice"  cssClass="width:200px;" >
                       	<form:option value=""/>
                       	<form:options items="${priceMap}" />
                       </form:select><br/>
  
                       <label><font color="red">*</font>Purchase Date:</label>
                       <form:input path="purchaseDate"   maxlength="80" /><br/>
                 
                       <label><font color="red">*</font>Down Payment:</label>
                   
                       <form:select path="downpayment"   cssClass="width:200px;" >
                       	<form:option value=""/>
                       	<form:options items="${priceMap}" />
                       </form:select><br/>
                    
                 </div>    
                
               
           
               <div id="refinanceDisplay" style="display: none;"> 
                   
                    <label><font color="red">*</font>EstimateHome Price:</label> 
                   
                       <form:select path="estimateHomeValue">
                       	<form:option value=""/>
                       	<form:options items="${priceMap}" />
                       </form:select><br>
                       
                </div>    
            
            
                  <label><font color="red">*</font><span style="font-size:13px;">Quoted Loan Term:</span></label>
                  <label style="font-size:12px;width:347px;text-align:left;">(Loan Amount: below 417k Conforming; above: Jumbo) </label><br>
                     
                       <c:forEach items="${loanTermList}" var="termObj">
	         				 <label style="text-align:right">${termObj}:</label><input class="radio" type="checkbox" name="loanTerms" value="${termObj}">
	    			   </c:forEach> 
                      <!--  <form:checkboxes cssClass="checkbox" path="loanTerms" items="${loanTermMap}" delimiter="<br>" /> 
                      -->
                   <br> 
                   
                   <label><font color="red">*</font>Property Zip Code:</label>
                    <form:input path="propertyZipCode"  maxlength="30"  cssClass="width:200px;" /> 
		           	  <span style="color:Red"><form:errors path="propertyZipCode"  cssClass="error" /></span> <br> 

               
                   <label>Property Street:</label>
                    <form:input path="propertyAddress"  size="50" maxlength="100"  cssClass="width:300px;" /><br/>
                      
                
               
                   <label>Property City:</label>
           			 <form:input path="propertyCity"  maxlength="100"  cssClass="width:300px;" /><br/>
                    
                 
                   <label>Property Type:</label>
                   
                   <form:select path="propertyType" cssClass="width:200px">
                      <form:option value=""></form:option>
                      <form:options items="${propertyTypeList}"></form:options>
                   </form:select><br/>
                                 
         
                   <label><font color="red">*</font> Occupancy Status:</label>
                   <c:forEach items="${occupyStatusList}" var="occupy">
                       <input class="radio" type="radio" name="occupancyStatus"/><label style="width: 130px;text-align:left"> ${occupy}</label>
                   </c:forEach>
                   
		           	  <span style="color:Red"><form:errors path="occupancyStatus"  cssClass="error" /></span> <br> 
                   
                   <br>
 				   <br>
           </fieldset>
    
          <fieldset style="margin: 20px 20px 20px 20px;border:solid 2px lightblue">  
              <legend><span class="AccountCreateTableHeader"> Enter your loan history information:</span> </legend>
        
				<img src="<%=request.getContextPath()%>/images/formImages/user_add64x64.png" width="40" height="40"  /> 
 
                   <label style="margin-left:20px;width: 150px;margin-top:22px;">First Loan Balance:</label>
                   <div style="margin-top:12px;"> 
                        <form:select path="firstLoanBalance"  cssClass="width:200px;" >
                       	<form:option value=""/>
                       	<form:options items="${priceMap}" />
                       </form:select><br/>
                  </div>
                    <label>First Loan Interest Rate:</label>
           			 <form:input path="firstLoanRate"  maxlength="100"  cssClass="width:300px;" /><br/>
                        
                   <label>First Loan Term:</label>
                   
                   <form:select path="firstLoanTerm" cssClass="width:200px">
                      <form:option value=""></form:option>
                      <form:options items="${loanTermList}"></form:options>
                   </form:select><br/>
                      
                   <label>Second Loan Balance:</label>
                         <form:select path="secondLoanBalance"  cssClass="width:200px;" >
                       	<form:option value=""/>
                       	<form:options items="${priceMap}" />
                       </form:select><br/>
                  
                 <label>Second Loan Interest Rate:</label>
           			 <form:input path="secondLoanRate"  maxlength="100"  cssClass="width:300px;" /><br/>
                   
                 
                   <label>Second Loan Term:</label>
                   <form:select path="secondLoanTerm" cssClass="width:200px">
                      <form:option value=""></form:option>
                      <form:options items="${loanTermList}"></form:options>
                   </form:select><br/>
                   
              
                   <label>Auto Loan Balance:</label>
                   
                       <form:select path="autoLoanBalance"  cssClass="width:200px;" >
                       	<form:option value=""/>
                       	<form:options items="${priceMap}" />
                       </form:select><br/>
                    
                   
      			  
                 <label>Quote Description:</label>
           			 <form:textarea path="requestDescription"  rows="6"  cols="80" /><br/>
				<br>
             </fieldset>
			<div style="margin-left:20px;margin-bottom:20px">
    			 <a  href="javascript: subform()"><img style="border:0px;" src="<%=request.getContextPath()%>/images/formImages/b_submit.png" title="Submit A Quote" width="80" height="28" /></a>
            
        
            
       	    </div>                                              
               
         </div>   
      </form:form> 

<script type="text/javascript">
 	
	function addCommas(nStr)
	{
	    nStr = nStr.replace(',','');
	    nStr = nStr.replace('$','');
	    nStr = nStr.replace(".00","");
    	 nStr += '';
   		 x = nStr.split('.');
  		  x1 = x[0];
   		 x2 = x.length > 1 ? '.' + x[1] : '';
   		 var rgx = /(\d+)(\d{3})/;
   		 while (rgx.test(x1)) {
       		 x1 = x1.replace(rgx, '$1' + ',' + '$2');
    	}
    	return x1 + x2;
}
	
	 
	function getStdUSDollar(str) {
		return "$"+addCommas(str)+".00";
	}
    var reg_email=/^\w+([\.-]?\w+)@\w+([\.-]?\w+)*(\.\w{2,3})+$/
    var reg_phoneNo = /^\(?(\d{3})\)?[\.\-\/]?(\d{3})[\.\-\/]?(\d{4})$/
    var reg_zip = /^\d\d\d\d\d$/
    
    var fideTime=8000
    $("#email").blur(function () {
	    var str = $("#email").val();
	 
	    if (str==="") {
	    	 $(this).after("<span class='email_empty'> <font color=red>Email Address is required!</font></span>");
	    	  $("span").filter('.email_empty').fadeOut(fideTime);
	    } else if (!reg_email.test(str)) {
	    	 $(this).after("<span class='email_invalid'><font color=red> You input invalid Email Address! </font></span>");
	    	 $("span").filter('.email_invalid').fadeOut(fideTime);
		}  else if (reg_email.test(str)) {
		 	 $("span").filter('.email_invalid').fadeOut(fideTime);
		}
	  });
	  
	   $("#phoneNo").blur(function () {
	    var str = $("#phoneNo").val();
	 
	    if (str==="") {
	    	 $(this).after("<span class='phoneNo_empty'> <font color=red>Phone No is required!</font></span>");
	    	  $("span").filter('.phoneNo_empty').fadeOut(fideTime);
	    } else if (!reg_phoneNo.test(str)) {
	    	 $(this).after("<span class='phoneNo_invalid'><font color=red> You input invalid Phone No! </font></span>");
	    	 $("span").filter('.phoneNo_invalid').fadeOut(fideTime);
		}  else if (reg_phoneNo.test(str)) {
		 	//auto-format phone number
		 	var phoneArray = reg_phoneNo.exec(str);
			if (phoneArray) {
			 	$("#phoneNo").val("(" + phoneArray[1] + ")" + phoneArray[2] + "-" + phoneArray[3]);
		 	}
		}
	  });
	  
	  
	/* $("#phoneNo").keydown(function(event){
	    var str = $("#phoneNo").val()+ String.fromCharCode(event.keyCode);
	     
		 if (reg_phoneNo.test(str)) {		    
		 	var phoneArray = reg_phoneNo.exec(str);
			if (phoneArray) {
			 	var fin = "(" + phoneArray[1] + ")" + phoneArray[2] + "-" + phoneArray[3];
			  	fin =fin.substring(0,fin.length-1);    
			 	$("#phoneNo").val(fin);
			 	 
		 	}
		 }
		});*/
	 $("#phoneNo").keyup(function(event){
	    var str = $("#phoneNo").val();	     
		 if (reg_phoneNo.test(str)) {		    
		 	var phoneArray = reg_phoneNo.exec(str);
			if (phoneArray) {
			 	var fin = "(" + phoneArray[1] + ")" + phoneArray[2] + "-" + phoneArray[3];
			   	$("#phoneNo").val(fin);
			 	 
		 	}
		 }
		});
  $("#propertyZipCode").blur(function(){
    var str = $(this).val();
    if (str==="") return;
	 if (!reg_zip.test(str)) {
	    	 $(this).after("<span class='propertyZipCode'><font color=red> Zip Code Must be five digitals! remove rest charactors after 5th </font></span>");
	    	 $("span").filter('.propertyZipCode').fadeOut(fideTime*2);
	    	 $(this).val(str.substring(0,5));
	    }
	    // varify zip exists or not
	    
	});  
	

    $("#grossAnnualIncome").blur(function () {
	 	 var str = $(this).val();
	 	 if (str==="") return;
 		 $(this).val(getStdUSDollar(str));
    	});	 
    
     $("#grossAnnualIncome").keyup(function(event){
	     var str = $(this).val();
	 	 if (str==="") return;
	 	  $(this).val(addCommas(str));
		});
	  // use jquery each do required fields checking
	 var fieldNames = {
	   	 'First Name': '#firstName',
	     'Last Name': '#lastName',
	     'State': '#state',
	     'Property Zip Code': '#propertyZipCode',
	     'Purchase Price':'#purchasePrice',
	      'Purchase Date':'#purchaseDate',
	      'Down Payment':'#downpayment',
	      'Estimate Home Value':'#estimateHomeValue'
	     };
	 
	$.each (fieldNames, function(key,value) {
	
       $(value).blur(function () {
	      var str = $(value).val();
	      if (str==="") {
	     	 $(this).after("<span class='empty'> <font color=red>"+key+" is required!</font></span>");
	    	  $("span").filter('.empty').fadeOut(fideTime);
	      }
	    });
	  });  	  
	  
	  
 
   
</script>
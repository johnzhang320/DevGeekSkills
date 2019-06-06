 
var notEmptyFields = {
		 'Loan Amount': '#loan_amt',
	     'Interest Rate':'#int_rate',
	     'Loan Term':'#term',
	     'First Payement Date': '#first_date'	     
	     };
var extraFields = {
		 'Extra Monthly Payment':'#extra_PMT',
	     'Extra Yearly Payment':'#extra_YPMT',
	     'IExtra Yearly Pay Once':'#extra_YPMT_once',
	     'Extra Monthly Payment Starting Date': '#PMT_date',
		 'Extra Yearly Payment Starting Date': '#YPMT_date',
		 'Extra Pay Once Starting Date': '#once_date'
	     };


 function cancelform(){
	  $.each (notEmptyFields, function(key,value) {
		   	 $(value).val("");
	  });
	  $.each (extraFields, function(key,value) {
		   	 $(value).val("");
	  });
	  document.getElementById("reportFrame").style.display="none";
	  
 }
 function submitPdfform(baseUrl) {
	 
	 
    document.getElementById("reportFrame").style.display="block";
    var FrameId = document.getElementById("amortFrame");
    var errorFlag=false;
    
	if (isEmptyNoShortMsg(notEmptyFields)) {
		 
		errorFlag=true;
	}
	
	 
    if (errorFlag) {
    	return;
    }
   // var baseUrl="yearlyAmortOnline.html?";
    var count=0;
   
   // var baseUrl="yearlyAmortOnline.html?";
    
    $.each (notEmptyFields, function(key,value) {
   	 var str = $(value).val().replace(/,/g,"");
   	 if (value.indexOf("date")!=-1) {
   		 mm=str.substring(0,2);
   		 yyyy=str.substring(6,10);
   		 
   		 baseUrl +="&"+ "first_mm"+"="+mm;
   		 baseUrl +="&"+ "first_yyyy"+"="+yyyy;
   		 baseUrl +="&"+ "first_yyyy"+"="+yyyy;
   	 }  
   		 if (count==0) {
   			 baseUrl += "?"+value.substr(1,value.length)+"="+str;
   		 } else {
   			 baseUrl +="&"+ value.substr(1,value.length)+"="+str;
   		 }
   	 
   	 count=1;
   });  
    $.each (extraFields, function(key,value) {
     var str = $(value).val().replace(/,/g,"");
   	 if (str!="") {
   		 if (value.indexOf("date")!=-1) {
   	   		 mm=str.substring(0,2);
   	   		 yyyy=str.substring(6,10);
   	   		// alert("value="+value);
   	   		 var fieldName=null;
   	   		 if (value=="#PMT_date") {
   	   			 fieldName="PMT";
   	   			 
   	   		 }
   	   		 if (value=="#YPMT_date") {
	   			 fieldName="YPMT";
	   			 
	   		 }
   	   		 if (value=="#once_date") {
   	   			 fieldName="YPMT_once";
  			 
   	   		 }
   	   		 baseUrl +="&"+ fieldName+"_mm="+mm;
   	   		 baseUrl +="&"+ fieldName+"_yyyy="+yyyy;
   	   	 }  
   	   		 baseUrl += "&"+value.substr(1,value.length)+"="+str;
   	   	 
   	 } 
   });
   
   
   FrameId.setAttribute('scrolling','no'); 
  // console.log(baseUrl);
   FrameId.setAttribute('src', baseUrl);
	document.getElementById("cProgressSamll").style.display="block";
	
	setTimeout(circleScreenShow,2000);
 }

function circleScreenShow() {
document.getElementById("cProgressSamll").style.display="none";
}	 
  
 
 
  
 function formToJSON() {
		return JSON.stringify({
			"begin_balance": $("#loan_amt").val(), 
			"year_int_rate": $("#int_rate").val(), 
			"month":$("#term").val() 
			});
 }
 
 
  var dateFields = {
	   	 'First Payement Date': '#first_date',
	     'Extra Monthly Payment Starting Date': '#PMT_date',
	     'Extra Yearly Payment Starting Date': '#YPMT_date',
	     'Extra Pay Once Starting Date': '#once_date'
	     };

  var dollarFields = {
		     'Loan Amount': '#loan_amt',
		     'Interest Rate':'#int_rate',
		     'Extra Monthly Payment':'#extra_PMT',
		     'Extra Yearly Payment':'#extra_YPMT',
		     'IExtra Yearly Pay Once':'#extra_YPMT_once'			 
		    	 
		     };
  
  usDollarsKeyIn(dollarFields);
  
 /* JQuery Date Picker */
	$(function() {
	$.each (dateFields, function(key,value) {
		$(value).datepicker({
			dateFormat: 'mm-dd-yy',
			changeMonth:true,
			changeYear:true,		 
			onSelect: function(selectedDate) {
			$(value).empty().append(selectedDate);
			}
		});
		});
	});
 
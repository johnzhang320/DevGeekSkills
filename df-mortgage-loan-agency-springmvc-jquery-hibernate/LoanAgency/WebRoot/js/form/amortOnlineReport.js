
var opacityFields = {
		 '417,000.00': '#loan_amt',
	     '3.875':'#int_rate',
	     '08-20-2012': '#first_date',
	     '650,000.00':'#purch_price',
	     '1.25':'#prop_tax',
	     '0.35':'#prop_inc',	     
	     '6,000.00':'#nonRentalIncome',
	     '1200':'#rentalIncome',
	     '200,000.00':'#downPayment',
	     '2200':'#debt'	          
	     }; 


 
$(document).ready(function() {
	var date = new Date();
	var currentDate = (date.getMonth()+1)+"-"+date.getDay()+"-"+date.getFullYear();
	 
	$.each (opacityFields,function(key,value) {
	   
	 
		if ($(value).val()==key) {
			$(value).css({opacity:0.5});
		} else {
			$(value).css({opacity:1});
		}
	    $(value).focus( function () {
	       $(value).css({opacity:1});
	      // $(value).val("");
	    
	   	});
	});  
});

var notEmptyFields = {
		 'Loan Amount': '#loan_amt',
	     'Interest Rate':'#int_rate',
	     'Loan Term':'#term',
	     'First Payement Date': '#first_date',	
	      'Purchase Price':'#purch_price',
	 	  'Property Tax':'#prop_tax',
	 	 'Property Insurance':'#prop_inc',
	 	  'Non Rental Income':'#nonRentalIncome',
		  'Rental Income':'#rentalIncome',
		  'Monthly Debt Payment':'#debt',
		  'Down Payment':'#downPayment',
		  'Loan To Value Ratio':'#LTV'    
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
	  $.each (dollarFields, function(key,value) {
		   	 $(value).val("");
	  });
	  document.getElementById("reportFrame").style.display="none";
	  
 }

 
 
 function submitform(baseUrl) {
   
	var orginUrl = baseUrl; 
    document.getElementById("reportFrame").style.display="block";
    var FrameId = document.getElementById("amortFrame");
    var errorFlag=false;
    
	if (isEmpty(notEmptyFields)) {
		 
		errorFlag=true;
	}
	if (isNumericFields(dollarFields)) {
		errorFlag=true;
	}
	 
    if (errorFlag) {
    	return;
    }
    //var baseUrl="monthlyAmortOnline.html?";
    var count=0;
   
    
    $.each (notEmptyFields, function(key,value) {
      var str = $(value).val();
	  if (str!=undefined) {
		  var str = $(value).val().replace(/,/g,"");
		  if (value.indexOf("date")!=-1) {
   		 mm=str.substring(0,2);
   		 yyyy=str.substring(6,10);
   		 
   		 baseUrl +="&"+ "first_mm"+"="+mm;
   		 baseUrl +="&"+ "first_yyyy"+"="+yyyy;
   		 baseUrl +="&"+ "first_yyyy"+"="+yyyy;
   	     }  
   		 if (count==0) {
   			 baseUrl +="?"+ value.substr(1,value.length)+"="+str;
   		 } else {
   			 baseUrl +="&"+ value.substr(1,value.length)+"="+str;
   		 }
   	 
   		 count=1;
	  }
   });  
    $.each (extraFields, function(key,value) {
  	  var str = $(value).val();
	  if (str!=undefined) {	
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
	  }
   });
  
     
    FrameId.setAttribute('src', baseUrl);
 }
 
 function submitCompareForm(formName) {
	 
	   
	    var errorFlag=false;
	    var baseUrl = formName+".html";
	    var formNameSet = "#"+formName;
	    
	    
	    
		if (isEmpty(notEmptyFields)) {
			 
			errorFlag=true;
		}
		if (isNumericFields(dollarFields)) {
			errorFlag=true;
		}
		/*if (formName=='compareRefiOnLine') {
			if (compareDateToNow ($("#first_date").val(),new Date())==-1) {
				errorFlag=true;
				errorWarn("#first_date","wrong!","Refinance curent Loan started pay day should not be later than today!!");
			}   
		}*/
	    if (errorFlag) {
	    	return;
	    }
	   // document.forms[formName].submit(); 
	    //very powerful serilize() function , get parameters from input or select 
	    dataString = $(formNameSet).serialize().replace(/%2C/g,'');
	    
	    
     
	    
	    $.ajax({
			type: 'POST',			 
			dataType: "json",
			url: baseUrl,			
			data:   dataString,
			success: function(data, textStatus, jqXHR){
			//	console.log("loan Amount="+data.remainBalance+",borrower="+data.borrower+",Aggressive Home Price="+data.AffordableInfo.aggresivePrice+",conservative home price="+data.AffordableInfo.conservativePrice);
				$("#reportFrame").css('display','block');
				$("label[for='loan_amt']").html($("#loan_amt").val());
				$("label[for='term']").html($("#term").val());
				$("label[for='int_rate']").html($("#int_rate").val());
				$("label[for='first_date']").html($("#first_date").val());
				$("label[for='remain_balance']").html(data.remain_balance);
				
				$("label[for='int_rate1']").html($("#int_rate1").val());
				$("label[for='int_rate2']").html($("#int_rate2").val());
				$("label[for='int_rate3']").html($("#int_rate3").val());
				 
				$("label[for='new_loan_amt1']").html($("#new_loan_amt1").val());
				$("label[for='new_loan_amt2']").html($("#new_loan_amt2").val());
				$("label[for='new_loan_amt3']").html($("#new_loan_amt3").val());
				   
		
				$("label[for='closing_cost1']").html($("#closing_cost1").val());
				$("label[for='closing_cost2']").html($("#closing_cost2").val());
				$("label[for='closing_cost3']").html($("#closing_cost3").val());
		
				$("label[for='point1']").html($("#point1").val());
				$("label[for='point2']").html($("#point2").val());
				$("label[for='point3']").html($("#point3").val());
				
				$("label[for='term1']").html($("#term1").val());
				$("label[for='term2']").html($("#term2").val());
				$("label[for='term3']").html($("#term3").val());
				
	 
				$("label[for='PMT']").html(data.curLoan.monthPayment);
				$("label[for='paid_num']").html(data.curLoan.paidInterest);
				$("label[for='remain_num']").html(data.curLoan.remainTimes);
				$("label[for='total_int_paid']").html(data.curLoan.paidInterest);
				$("label[for='total_unint_paid']").html(data.curLoan.unpaidInterets);
				$("label[for='unpaid_remain_bal']").html(data.curLoan.unpainLoanAmt);
				 
				$("label[for='PMT1']").html(data.Loan1.monthPayment);
				$("label[for='monthly_saving1']").html(data.Loan1.monthSaving);
				$("label[for='BEP1']").html(data.Loan1.breakEventPoint);
				$("label[for='term1']").html(data.Loan1.remainTimes);				
				$("label[for='total_unint_paid1']").html(data.Loan1.unpaidInterets);
				$("label[for='unpaid_remain_bal1']").html(data.Loan1.unpainLoanAmt);
				
				$("label[for='PMT2']").html(data.Loan2.monthPayment);
				$("label[for='monthly_saving2']").html(data.Loan2.monthSaving);
				$("label[for='BEP2']").html(data.Loan2.breakEventPoint);
				$("label[for='term2']").html(data.Loan2.remainTimes);				
				$("label[for='total_unint_paid2']").html(data.Loan2.unpaidInterets);
				$("label[for='unpaid_remain_bal2']").html(data.Loan2.unpainLoanAmt);
				
				$("label[for='PMT3']").html(data.Loan3.monthPayment);
				$("label[for='monthly_saving3']").html(data.Loan3.monthSaving);
				$("label[for='BEP3']").html(data.Loan3.breakEventPoint);
				$("label[for='term3']").html(data.Loan3.remainTimes);				
				$("label[for='total_unint_paid3']").html(data.Loan3.unpaidInterets);
				$("label[for='unpaid_remain_bal3']").html(data.Loan3.unpainLoanAmt);
			},
			error: function(jqXHR, textStatus, errorThrown){
				 
			}
		});

 }
 
/**
 * submitAgentQuoteForm('agentProcessQuoteOnline','agentProcessQuoteRefiOnLine')
 * submitAgentQuoteForm('agentProcessQuoteOnline','agentProcessQuotePurchOnLine')
 * @param formName
 */
 function submitAgentQuoteForm(formName,baseUrl) {
	 
	   
	    var errorFlag=false;
	   
	    var formNameSet = "#"+formName;	    
	    
	    
		if (isEmpty(notEmptyFields)) {
			 
			errorFlag=true;
		}
		 
		 
	    if (errorFlag) {
	    	return;
	    }
	   // document.forms[formName].submit(); 
	    //very powerful serilize() function , get parameters from input or select 
	    dataString = $(formNameSet).serialize().replace(/%2C/g,'');
	    
	  
	    
	    $.ajax({
			type: 'POST',			 
			dataType: "json",
			url: baseUrl,			
			data:   dataString,
			success: function(data, textStatus, jqXHR){
		 		$("#reportFrame").css('display','block');
				$("label[for='loan_amt']").html($("#loan_amt").val());
				$("label[for='term']").html($("#term").val());
				$("label[for='int_rate']").html($("#int_rate").val());
				$("label[for='first_date']").html($("#first_date").val());
				$("label[for='remain_balance']").html(data.remain_balance);
				
				$("label[for='int_rate1']").html($("#int_rate1").val());
				$("label[for='int_rate2']").html($("#int_rate2").val());
				$("label[for='int_rate3']").html($("#int_rate3").val());
				$("label[for='int_rate4']").html($("#int_rate4").val());
				 
				$("label[for='new_loan_amt1']").html($("#new_loan_amt1").val());
				$("label[for='new_loan_amt2']").html($("#new_loan_amt2").val());
				$("label[for='new_loan_amt3']").html($("#new_loan_amt3").val());
				$("label[for='new_loan_amt4']").html($("#new_loan_amt4").val());
				   
		
				$("label[for='closing_cost1']").html($("#closing_cost1").val());
				$("label[for='closing_cost2']").html($("#closing_cost2").val());
				$("label[for='closing_cost3']").html($("#closing_cost3").val());
				$("label[for='closing_cost4']").html($("#closing_cost4").val());
		
				$("label[for='point1']").html($("#point1").val());
				$("label[for='point2']").html($("#point2").val());
				$("label[for='point3']").html($("#point3").val());
				$("label[for='point4']").html($("#point4").val());
			
				$("label[for='term1']").html($("#term1").val());
				$("label[for='term2']").html($("#term2").val());
				$("label[for='term3']").html($("#term3").val());
				$("label[for='term4']").html($("#term4").val());
				
	 
				$("label[for='PMT']").html(data.curLoan.monthPayment);
				$("label[for='paid_num']").html(data.curLoan.paidInterest);
				$("label[for='remain_num']").html(data.curLoan.remainTimes);
				$("label[for='total_int_paid']").html(data.curLoan.paidInterest);
				$("label[for='total_unint_paid']").html(data.curLoan.unpaidInterets);
				$("label[for='unpaid_remain_bal']").html(data.curLoan.unpainLoanAmt);
				 
				$("label[for='PMT1']").html(data.Loan1.monthPayment);
				$("label[for='monthly_saving1']").html(data.Loan1.monthSaving);
				$("label[for='BEP1']").html(data.Loan1.breakEventPoint);
				$("label[for='term1']").html(data.Loan1.remainTimes);				
				$("label[for='total_unint_paid1']").html(data.Loan1.unpaidInterets);
				$("label[for='unpaid_remain_bal1']").html(data.Loan1.unpainLoanAmt);
				
				$("label[for='PMT2']").html(data.Loan2.monthPayment);
				$("label[for='monthly_saving2']").html(data.Loan2.monthSaving);
				$("label[for='BEP2']").html(data.Loan2.breakEventPoint);
				$("label[for='term2']").html(data.Loan2.remainTimes);				
				$("label[for='total_unint_paid2']").html(data.Loan2.unpaidInterets);
				$("label[for='unpaid_remain_bal2']").html(data.Loan2.unpainLoanAmt);
				
				$("label[for='PMT3']").html(data.Loan3.monthPayment);
				$("label[for='monthly_saving3']").html(data.Loan3.monthSaving);
				$("label[for='BEP3']").html(data.Loan3.breakEventPoint);
				$("label[for='term3']").html(data.Loan3.remainTimes);				
				$("label[for='total_unint_paid3']").html(data.Loan3.unpaidInterets);
				$("label[for='unpaid_remain_bal3']").html(data.Loan3.unpainLoanAmt);
				
				$("label[for='PMT4']").html(data.Loan4.monthPayment);
				$("label[for='monthly_saving4']").html(data.Loan4.monthSaving);
				$("label[for='BEP4']").html(data.Loan4.breakEventPoint);
				$("label[for='term4']").html(data.Loan4.remainTimes);				
				$("label[for='total_unint_paid4']").html(data.Loan4.unpaidInterets);
				$("label[for='unpaid_remain_bal4']").html(data.Loan4.unpainLoanAmt);
			},
			error: function(jqXHR, textStatus, errorThrown){
				 
			}
		});

}

 
//Helper function to serialize all the form fields into a JSON string
 function formToJSON() {  
 	return {	 
 		"loan_amt": $('#loan_amt').val(), 
 		"int_rate": $('#int_rate').val(),
 		"term": $('#term').val(),
 		"first_date": $('#first_date').val() 
 		};
 }
 
  var dateFields = {
	   	 'First Payement Date': '#first_date',
	     'Extra Monthly Payment Starting Date': '#PMT_date',
	     'Extra Yearly Payment Starting Date': '#YPMT_date',
	     'Extra Pay Once Starting Date': '#once_date'
	     };

  var dollarFields = {
		     'Loan Amount': '#loan_amt',		  
		     'Extra Monthly Payment':'#extra_PMT',
		     'Extra Yearly Payment':'#extra_YPMT',
		     'Extra Yearly Pay Once':'#extra_YPMT_once',
	    	 'Purchase Price':'#purch_price',
		 	 'Property Tax':'#prop_tax',
		     'Property Insurance':'#prop_inc',
		 	 'Non Rental Income':'#nonRentalIncome',
		 	 'Rental Income':'#rentalIncome',
		 	 'Monthly Debt Payment':'#debt',
		 	 'Loan To Value Ratio':'#LTV',
		 	 'term':'#term',	 
		 	 'New Loan 1 term':'#term1',	 
		 	 'New Loan 2 term':'#term2',
		 	 'New Loan 3 term':'#term3',		
		 	 'New Loan 1 Amount':'#new_loan_amt1',	 
		 	 'New Loan 2 Amount':'#new_loan_amt2',
		 	 'New Loan 3 Amount':'#new_loan_amt3',		
		 	 'New Loan 3 Amount':'#new_loan_amt4',		
		 	 'New Loan 1 Closing Cost':'#closing_cost1',	 
		 	 'New Loan 2 Closing Cost':'#closing_cost2',
		 	 'New Loan 3 Closing Cost':'#closing_cost3',
		 	 'New Loan 3 Closing Cost':'#closing_cost4',
		 	 'New Loan 1 Point':'#point1',	 
		 	 'New Loan 2 Point':'#point2',
		 	 'New Loan 3 Point':'#point3',
		 	 'New Loan 3 Point':'#point4',
		     };
  
  usDollarsKeyIn(dollarFields);
  
 /* JQuery Date Picker */
	$(function() {
	$.each (dateFields, function(key,value) {
		$(value).datepicker({
			dateFormat: 'mm-dd-yy',
			changeMonth:true,
			changeYear:true,	
			gotoCurrent: true,
			onSelect: function(selectedDate) {
			$(value).empty().append(selectedDate);
			}
		});
		});
	});
	/**
	 * applyLoanBalance('compareRefiOnLine')  --- loan comparision
	 * applyLoanBalance('getRefiRemainBalance') --- Agent Process Quote
	 * @param formName
	 */
	
	function applyLoanBalance(formName) {
	 
			
			  var errorFlag=false;
			  var baseUrl = formName+".html";
			  
			  if (formName=='compareRefiOnLine') {
			      baseUrl = "getRemainBalance.html";
			  }
			    var formNameSet = "#"+formName;	    
			    
				if (formName=='compareRefiOnLine') { 
			    
				if (isEmpty(notEmptyFields)) {
					 
					errorFlag=true;
				}
				if (isNumericFields(dollarFields)) {
					errorFlag=true;
				}
			
					if (compareDateToNow ($("#first_date").val(),new Date())==-1) {
						errorFlag=true;
						errorWarn("#first_date","wrong!","Refinance curent Loan started pay day should not be later than today!!");
					}   
			
					if (errorFlag) {
						return;
					}
				}
			   // document.forms[formName].submit(); 
			    //very powerful serilize() function , get parameters from input or select 
			    dataString = $(formNameSet).serialize().replace(/%2C/g,'');
			    
			    
		  
			   
			    $.ajax({
					type: 'POST',			 
					dataType: "json",
					url: baseUrl,			
					data:   dataString,
					success: function(data, textStatus, jqXHR){
			 		 
					 
						
						$('#new_loan_amt1').val(data.curLoan.unpainLoanAmt);
						$('#new_loan_amt2').val(data.curLoan.unpainLoanAmt);
						$('#new_loan_amt3').val(data.curLoan.unpainLoanAmt);
						$('#new_loan_amt4').val(data.curLoan.unpainLoanAmt);
						 
		    },
				error: function(jqXHR, textStatus, errorThrown){
					 
				}
			});
  
	}
	
	function newLoanExample(choice) {
		
		
		
		if (choice=='1') {
		 
			$('#term1').val('360');
		/*	$('#new_loan_amt1').val('417,000.00');*/
			$('#int_rate1').val('3.125');
			$('#closing_cost1').val('5,000.00');
			$('#point1').val('1');
		}
		if (choice=='2') {
		 
			$('#term2').val('360');
			/*	$('#new_loan_amt1').val('417,000.00');*/
			$('#int_rate2').val('3.625');
			$('#closing_cost2').val('');
			$('#point2').val('');
		}
		
		if (choice=='3') {
			 
			$('#term3').val('180');
			/*	$('#new_loan_amt1').val('417,000.00');*/
			$('#int_rate3').val('4.625');
			$('#closing_cost3').val('');
			$('#point3').val('');
		}
		if (choice=='4') {
			 
			$('#term4').val('120');
			/*	$('#new_loan_amt1').val('417,000.00');*/
			$('#int_rate4').val('5.375');
			$('#closing_cost4').val('');
			$('#point4').val('');
		}
	}
	
	
	

	 
	 var dollarFields = {
	     'Loan Amount': '#loanAmount',
	     'Estimate Home Value':'#estimateHomeValue',
	     'First Loan Balance':'#firstLoanBalance',
	     'Second Loan Balance':'#secondLoanBalance',
	      'Total Annual income':'#annualIncome'
	     };
	     
	 $.each (dollarFields, function(key,value) {
   		 $(value).keyup(function(event){
	    	 var str = $(this).val();
	 	 	if (str==="") return;
	 	 	if (!isNumeric(str)) {
	 	 	   errorWarn(value,"Invalid !",key+" must be numeric digits !<br>");
	 	 	} else {
	 	 		cleanWarn(value);
	 	 	}
	 	 	 $(this).val(getStdUSDollar(str));
		}); 
	  });  	
	
	  // checking if empty 
	 var fieldNames = {
	   	// 'Loan Amount': '#loanAmount',
	     'Estimate Home Value':'#estimateHomeValue',
	     'Total Annual Income': '#annualIncome',
	     'Borrower Credit Score': '#borrowerCreditScore',   
	     'Property Type': '#propertyType',
	     'Occupancy Status':'#occupancyStatus',
	    // 'Current Loan Initial Amount':'#firstLoanBalance',
	     'Current Loan Rate':'#firstLoanRate',
	     'Current Loan Term':'#firstLoanTerm'
	      
	     };
	/**
	 *  Check if is numeric
	 */     
	var fieldNamesInvalid ={
			 'Loan Amount': '#loanAmount',
		     'Estimate Home Value':'#estimateHomeValue',
			 'First Loan Balance':'#firstLoanBalance',
		     'Second Loan Balance':'#secondLoanBalance',
		     'First Loan Rate':'#firstLoanRate',
		     'Second Loan Rate':'#secondLoanRate'
		}; 
	 

	 
	function submitform() {
	
	  var errorFlag=false;
	  var errorMessage="";
	  var loanAmount=0;
	  /**
	   * Check if empty or not only here
	   */
	  if (isEmpty (fieldNames)) {
		  errorFlag=true;
	  }
	  if (!isTextCodeMatch("4")) {
	 	  errorFlag=true;
	 }     
	  
	   if (!errorFlag) {
		   $("#form_action").attr('name', '_target1');
		   $("#form_action").attr('value', 'Next');
		  
	   	  

		   document.forms['loan_quote'].submit();
	   }
	 }  	  
	    
	function cancelform() {
		
		 $.each (fieldNames, function(key,value) {
	      
         var str = $(value).val();
         var label = value+"Label";
         var msg = value+"Msg";
         	$(value).css('border','1px solid #888888');
	      			$(label).css('color','#1040A3');
	      			 $(msg).html("");
	      			 $(value).val("");
	         
	    });
	}	
	
	
	/* JQuery Date Picker */
	$(function() {
		$("#firstDate").datepicker({
			dateFormat: 'mm-dd-yy',
			changeDay:true,
			changeMonth:true,
			changeYear:true,		
			onSelect: function(selectedDate) {
			$("#firstDate").empty().append(selectedDate);
			}
		});
	});
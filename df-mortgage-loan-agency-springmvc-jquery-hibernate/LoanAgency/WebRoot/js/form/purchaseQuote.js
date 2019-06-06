 
 		
	 var dollarFields = {
	     'Loan Amount': '#loanAmount',
	     'Purchase Price':'#purchasePrice',
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
	 
	 
	  // use jquery each do required fields checking
	 var fieldNames = {
	   	 'Loan Amount': '#loanAmount',
	     'Total Annual Income': '#annualIncome',
	     'Borrower Credit Score': '#borrowerCreditScore',
	     'Property Type': '#propertyType',
	     'Occupancy Status':'#occupancyStatus',
	     'Purchase Price':'#purchasePrice',
	   	 'Email Address': '#emailAddress',
	     'First Name': '#firstName',
	   
	     };
	     
	// $("#loanAmountLabel").css('color','#ff0000');
	 
	 $("#emailAddress").blur(function () {
 		 var email = $("#emailAddress").val();	 
 		 
 		 if (email==null || email.substring(0,1)==" ")
 			 return;
 		//fetchRegisteredUser(this);
 	 });	
	 /**
	  *  Call DWR to fetch registered user 
	  */
	 var email;
	 function fetchRegisteredUser(emailField) {
		 str = $(emailField).val();
		 email = str;
		 StateCountyManager.findUserByEmail(str,{callback:callBackFetchUser});
	 }
	 
	function callBackFetchUser(userObj) {
			 
		if (userObj.emailAddress==null)	{					 
				 cleanWarn('#emailAddress');
				 
			 } else {
			  
	 			$('#firstName').val(userObj.firstName);
	 			$('#lastName').val(userObj.lastName);
	 			$('#phoneNo').val(userObj.phoneNo);
	 		 }
			 
		 
	   }
	 
	function savePurchaseform() {
		
	  /**
	   *  Check if empty only here
	   */
	  var errorFlag=false;
	  
	 if (isEmpty(fieldNames)) {
		 errorFlag=true;
	 }
	 if (!isTextCodeMatch("4")) {
	 	  errorFlag=true;
	 }       
	 var loanAmount = dollarValue($("#loanAmount").val());
	   
	   
	 var purchasePrice= dollarValue($("#purchasePrice").val());
	
	 if ( loanAmount>purchasePrice ) {
		   errorWarn("#loanAmount","Wrong!","Loan Amount should not be greater than Purchase Price !!<br>");
		   errorFlag=true;
	 } 
		
	
	      
	  if (!errorFlag)
	    	cleanWarn("#loanAmount");
	  /**
	   *  check if loan amount > purchase price
	   */
	  if (!errorFlag) {
		  document.getElementById("cProgressSamll").style.display="block";
		  document.forms['loan_quote'].action="purchaseQuote.html";
		 // document.forms[0].submit();
		 //  document.forms['loan_quote'].submit();	
		  document.getElementById("loan_quote").submit();
	       
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
		$("#purchaseDate").datepicker({
			dateFormat: 'mm-dd-yy',
			changeDay:true,
			changeMonth:true,
			changeYear:true,		 
			onSelect: function(selectedDate) {
			//$("#purchaseDate").empty().append(selectedDate);
			}
		});
	});
	 
 
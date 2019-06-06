 /**
  * Control Button
  */
   function previousForm() {
    	 $("#form_action").attr('name', '_target0');
    	 $("#form_action").attr('value', 'Previous');
    	 document.forms['loan_quote'].submit();
    }

   function cancelForm() {
  	 $("#form_action").attr('name', '_cancel');
  	 $("#form_action").attr('value', 'Cancel');
  	 document.forms['loan_quote'].submit();
  }
   
	 var dollarFields = {
	     'Annual Non Rental Income': '#noneRentalIncome',
	     'Annual Rental Income':'#rentalIncome'
	     };
	     
	$.each (dollarFields, function(key,value) {
   		 $(value).keyup(function(event){
	    	 var str = $(this).val();
	 	 	if (str==="") return;
	 	 	 $(this).val(getStdUSDollar(str));
		}); 
	  });  	
	
	
	  // use jquery each do required fields checking
	 var NoEmptyFields = {
	   	 'Email Address': '#emailAddress',
	     'Password': '#password',
	     'First Name': '#firstName',
	     'Phone No.': '#phoneNo',
	     'Annual None Rental Income':'#noneRentalIncome'
	     };
	 var dollarFields = {    
			 'Annual None Rental Income':'#noneRentalIncome',
			 'Annual Rental Income':'#rentalIncome',
			 'Credit Score':'#creditScore'
	 };
	 /**
	  * ,
	     'Rental Income':'#rentalIncome',
	  */
	 
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
	 
	function submitForm() {
	 
	  /*
	   *  Check if empty only here
	   */
	  var errorFlag=false;
	  
	  	if (isEmpty(NoEmptyFields)) {
	  		errorFlag=true;
	  	}
	  
	  
	 
	    if (!errorFlag) {	   		 
	    	   $("#form_action").attr('name', '_finish');
	    	   $("#form_action").attr('value', 'Next');
	    	   var password= document.forms['loan_quote'].password.value;
	    	 //  var encrypted_password = convertToSHA256(password);
	    	 //  document.forms['loan_quote'].password.value=encrypted_password;
	    	 
	      	   document.forms['loan_quote'].submit();
	     }
	 }  	  
	    
	$("#phoneNo").keyup(function(event){
	    var str = $("#phoneNo").val();	     
		 if (isValidPhoneNo(str)) {		    
		 	var phoneArray = getArrayPhoneNo(str);
			if (phoneArray) {
			 	var fin = "(" + phoneArray[1] + ")" + phoneArray[2] + "-" + phoneArray[3];
			   	$("#phoneNo").val(fin);
			 	 
		 	}
		 }
		});
	
	 $("#password").blur(function () {
 		 var pass = $("#password").val();	 
 		 if (pass==null || pass.substring(0,1)==" ")
 			 return;
 		 var password= document.forms['loan_quote'].password.value;
 		 
	   	 //  var encrypted_password = convertToSHA256(password);
	   	//   document.forms['loan_quote'].password.value=encrypted_password;
 	 });	
	 
	 $("#emailAddress").blur(function () {
 		 var email = $("#emailAddress").val();	 
 		 if (email==null || email.substring(0,1)==" ")
 			 return;
 		fetchRegisteredUser(this);
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
			 
		if (userObj.emailAddress=="notFound")	{					 
				 cleanWarn('#emailAddress');
				 
			 } else {
				 errorWarn('#emailAddress',"Exists!",email+" has been registered in our database, you can change and then resumit registered data !!<br>");
			 
	 		 //   $('#password').val(userObj.password);
	 		  //  $('#confirmPassword').val(userObj.password);
	 			$('#firstName').val(userObj.firstName);
	 			$('#lastName').val(userObj.lastName);
	 			$('#phoneNo').val(userObj.phoneNo);
	 			$('#state').val(userObj.state);
	 			$('#rentalIncome').val(userObj.rentalIncome);
	 			$('#noneRentalIncome').val(userObj.noneRentalIncome);	 	
	 			$('#creditScore').val(userObj.credtScore);
			 }
			 
		 
	   }
	 
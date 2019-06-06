 /**
    *   Below Section for Agent Login
    */
 	var loginIsEmptyfields = {
	   	 'Email Address': '#emailAddress',
	     'Password': '#password'	     
	 };
 	function loginSubmitForm() {
 		var email = $("#emailAddress").val();
 		var errorFlag=false;
 		if (isEmpty(loginIsEmptyfields)) {
 			errorFlag=true; 
 		}
 		
 		if (!isValidEmailField("#emailAddress")) {
 			errorFlag=true; 
 		}
 		 
 	
 		 if (!errorFlag) {
 			// var encrypted_password = convertToSHA256($("#password").val());
 			// document.getElementById("password").value=encrypted_password;
 			 //alert(encrypted_password);
 	 		 
 	 		 document.forms['loginForm'].action='userLogin.html';
 			 document.forms['loginForm'].submit();	 
 		 }
 	}
 	
   /**
    *   Below Section for Agent Signup
    */
 	var signupIsEmptyfields = {
 		   	 'Email Address': '#emailAddress',
 		     'Password': '#password',
 		     'Confirm Password': '#confirmPassword',
 		     'First Name': '#firstName',
 		     'Phone No.': '#phoneNo',
 		     'Annual None Rental Income':'#noneRentalIncome' 		   
 		 };
 	 var dollarFields = {
 			 'Annual None Rental Income':'#noneRentalIncome',
  		     'Annual Rental Income':'#rentalIncome',
  		     'Credit Score':'#creditScore'
 		     };
 	 
 	function signUpSubmitForm() {
 		var errorFlag=false;
 		if (isEmpty(signupIsEmptyfields)) {
 			errorFlag=true;
 		}
 		if (!isValidEmailField("#emailAddress")) {
 			errorFlag=true;
 		}
  		var password = $("#password").val();
 		var cpassword = $("#confirmPassword").val();
 		if (password==cpassword) {
 			cleanWarn("#confirmPassword");
 	   } else {
		   errorWarn("#confirmPassword","Not Match","Confirm Password not match Password, Try again !!<br>");
		   errorFlag=true;
 		}
 	 
 		if (!errorFlag) {
 			
 			 document.forms['loginForm'].action='userSignup.html';  			 
 			document.forms['loginForm'].submit();	
 		 
 		}
 	}
 	
 	 
    /**
     *  Below Section for Agent Forget
     */
 	var forgetIsEmptyfields = {
		   	 'Email Address': '#emailAddress'
 	};
   function forgetSubmitForm() {
	   if (isEmpty(forgetIsEmptyfields)) {
		   return;
	   }
	   document.forms['loginForm'].submit();	
     	
    }
	/**
	 *  Call DWR to see if user exists or not
	 *  
	 */
	 function isUserEmailExists (emailField) {
		 str = $(emailField).val();
		// StateCountyManager.isAgentEmailExists(str,callbackForEmail);
		
	 
	    var callbackForEmail = function (exists) {
		 alert("exists="+exists);
		   if(exists) {
			  return true;
		   } else {
			   return false;
		   }
	    };
	    return (callbackForEmail);
	 } 
	 /**
	  *  Call DWR to fetch registered user 
	  */
	 var email;
	 function fetchRegisteredUser(emailField) {
		 str = $(emailField).val();
		 email = str;
	//	 StateCountyManager.findUserByEmail(str,{callback:callBackFetchUser});
	 }
	 
	function callBackFetchUser(userObj) {
			// alert("userObj="+userObj);
			 if (userObj===undefined || userObj==null) {
				 
				 cleanWarn('#emailAddress');
				 
			 } else {
				 errorWarn('#emailAddress',"Exists!",email+" has been registered in our database, Please Enter Password!<br>");
			 
	 		 //   $('#password').val(userObj.password);
	 		   // $('#confirmPassword').val(userObj.password);
	 			$('#firstName').val(userObj.firstName);
	 			$('#lastName').val(userObj.lastName);
	 			$('#phoneNo').val(userObj.phoneNo);
	 			$('#state').val(userObj.state);
	 			$('#rentalIncome').val(userObj.rentalIncome);
	 			$('#noneRentalIncome').val(userObj.noneRentalIncome);	 	
	 			$('#creditScore').val(userObj.credtScore);
			 }
			 
		 
	   }
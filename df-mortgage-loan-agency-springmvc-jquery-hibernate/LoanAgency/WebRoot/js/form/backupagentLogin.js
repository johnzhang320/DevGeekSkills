 
	 
	$("#workPhone").keyup(function(event){
	    var str = $(this).val();	     
		 if (isValidPhoneNo(str)) {		    
		 	var phoneArray = getArrayPhoneNo(str);
			if (phoneArray) {
			 	var fin = "(" + phoneArray[1] + ")" + phoneArray[2] + "-" + phoneArray[3];
			   	$(this).val(fin);
			 	 
		 	}
		 }
	});	     
	 
	$("#cellPhone").keyup(function(event){
	    var str = $(this).val();	     
		 if (isValidPhoneNo(str)) {		    
		 	var phoneArray = getArrayPhoneNo(str);
			if (phoneArray) {
			 	var fin = "(" + phoneArray[1] + ")" + phoneArray[2] + "-" + phoneArray[3];
			   	$(this).val(fin);
			 	 
		 	}
		 }
	});

	
	
   /**
    *   Below Section for Agent Login
    */
 	var loginIsEmptyfields = {
	   	 'Email Address': '#emailAddress',
	     'Password': '#password'	     
	 };
 	function loginSubmitForm() {
          
 		var formNameSet = "#agentLoginForm"; 
 		var baseUrl = "agentLoginValidate.html";
 		var redirectTo = "AgentReviewQuote";
 		var email = $("#emailAddress").val();
 		var errorFlag=false;
 		if (isEmpty(loginIsEmptyfields)) {
 			errorFlag=true; 
 		}
 		
 		if (!isValidEmail(email)) {
 			errorFlag=true; 
 		}
 		
 	   
	  
/*	   var emailAddress = document.getElementById("emailAddress").value;
	   var password = document.getElementById("password").value;
	   dataString ="emailAddress="+emailAddress+"&password="+password+"&redirectTo="+redirectTo;
	   
    
	    $.ajax({
			type: 'POST',			 
			dataType: "json",
			url: baseUrl,			
			data:   dataString,
			success: function(data, textStatus, jqXHR){
				// console.log("returned Authorized="+data.Authorized);
				 if (data.Authorized=="NO") {
					 
					   errorWarn("#emailAddress","incorrect", "Username or password is incorret, please try again or signup !!<br>");
					   errorWarn("#password","incorrect", "Username or Password is incorret, please try again or signup !!<br>");
					   errorFlag=true;
				 }
				 if (data.Authorized=="YES") {
					 
					 errorFlag=false;
					 cleanWarn("#emailAddress");
					 cleanWarn("#password");
				 }
			},
			error: function(jqXHR, textStatus, errorThrown){
				 
			}
		});
*/
	    if (errorFlag) {
	    	return;
	    }
	    document.forms['agentForm'].submit();  
 	}
 	
   /**
    *   Below Section for Agent Signup
    */
 	var signupIsEmptyfields = {
 		   	 'Email Address': '#emailAddress',
 		     'Password': '#password',
 		     'Confirm Password': '#confirmPassword',
 		     'First Name': '#firstName',
 		     'Company Name': '#companyName',
 		     'License Eligible State': '#licenseEligibleState',
 		     'license No': '#licenseNo',
 		     'Work Phone No': '#workPhone',
 		     'Your Picture': '#pictureContent',
 		     'Living State': '#state' 		    
 		 };
 	
 	 
 		 
 	function signUpSubmitForm() {
 		 
 		var errorFlag=false;
 		if (isEmpty(signupIsEmptyfields)) {
 			errorFlag=true;
 		}
 		if (!isValidEmailField("#emailAddress")) {
 			errorFlag=true;
 		}
 		if (isUserEmailExists ("#emailAddress")) {
 		   var email = $("#emailAddress").val();
 		   errorWarn("#emailAddress","Exists!",email+" exists our database, you are return user, please confirm data !!<br>");
 		  errorFlag=true;
 		}  
 		 
 		
 		if (!isValidPhonNoField("#phoneNo")) {
 			errorFlag=true;
 		}
 		var password = $("#password").val();
 		var cpassword = $("#confirmPassword").val();
 		if (password==cpassword) {
 			if (!errorFlag)
 				cleanWarn("#emailAddress"); 			 
 	   } else {
		   errorWarn("#confirmPassword","Not Match","Confirm Password not match Password, Try again !!<br>");
		   errorFlag=true;
 		}
 	 
 		if (!errorFlag) {
 			cleanWarn("#emailAddress");
 			 document.forms['agentForm'].submit();	
  		} 
 	}
 	
 	
 	
 	
 	
 	/**
 	 *  email for signup
 	 */
 	 $("#emailAddress").blur(function () {
 		 
 		 var email = $("#emailAddress").val();	 
 		 if (email==="" || email.substring(0,1)==" ")
 			 return;
 		//fetchRegisteredAgent(this);
 	 });	
 	
	 var email;
	 function fetchRegisteredAgent(emailField) {
		 str = $(emailField).val();
		 email = str;
		// StateCountyManager.findAgentByEmail(str,{callback:callBackFetchAgent});
	 }
	 
	function callBackFetchAgent(agentObj) {
			
			if (agentObj.emailAddress=="notFound")	{		 
				 cleanWarn('#emailAddress');
				 
			 } else {
				 errorWarn('#emailAddress',"Exists!",email+" has been registered in our database!! Please Enter Password!<br>");
			 
				 $('#firstName').val(agentObj.firstName );
				 $('#lastName').val(agentObj.lastName);
				 $('#emailAddress').val(agentObj.emailAddress);
				 $('#username').val(agentObj.username);
				// $('#password').val(agentObj.password);
				// $('#confirmPassword').val(agentObj.confirmPassword);
				 $('#companyName').val(agentObj.companyName);
				 $('#workPhone').val(agentObj.workPhone );
				 $('#cellPhone').val(agentObj.cellPhone );
				 $('#address').val(agentObj.address);
				 $('#city').val(agentObj.city);
				 $('#state').val(agentObj.state );
				 $('#zipCode').val(agentObj.zipCode);
				 $('#description').val(agentObj.description);
				 $('#licenseNo').val(agentObj.licenseNo );
				 $('#licenseEligibleState ').val(agentObj.licenseEligibleState );
				 $('#pictureFilename').val(agentObj.pictureFilename);

			 }
			 
	   }
 	 
    /**
     *  Below Section for Agent Forget
     */
 	var forgetIsEmptyfields = {
		   	 'Email Address': '#emailAddress'
 	};
   function forgetSubmitForm() {
	   if (!isEmpty(forgetIsEmptyfields)) {
		   document.forms['agentForm'].submit();	
		}
     	
    }
   
    
	  
	     
	
	

	
	
	 
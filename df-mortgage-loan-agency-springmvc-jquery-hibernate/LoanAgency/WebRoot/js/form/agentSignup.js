 var result = "notMatch";
	 
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
 		var email = $("#emailAddress").val();
 		var errorFlag=false;
 		if (isEmpty(loginIsEmptyfields)) {
 			errorFlag=true; 
 		}
 		
 		if (!isValidEmailField($("#emailAddress"))) {
 			errorFlag=true; 
 			
 			// console.log("errorflag="+errorFlag+",invalid email");
 		}
	   
 		 if (!errorFlag) {
 			// var encrypted_password = convertToSHA256($("#password").val());
 			// document.getElementById("password").value=encrypted_password;
 			 document.forms['agentForm'].action='agentLogin.html';
 			 document.forms['agentForm'].submit();
 		 }
 	}
 	
 
 	
	var editEmptyfields = {
		   	 'Email Address': '#emailAddress',
		     'First Name': '#firstName',
		     'Company Name': '#companyName',
		     'License Eligible State': '#licenseEligibleState',
		     'license No': '#licenseNo',
		     'Work Phone No': '#workPhone',
		     'Your Picture': '#pictureContent',
		     'Living State': '#state' 		    
		 };
 	function previewPicture() {
 		document.forms['agentForm'].action='previewPicture.html';
		document.forms['agentForm'].submit();	
 	} 
 		 

 	function editSubmitForm() {
		 
 		var errorFlag=false;
 		if (isEmpty(signupIsEmptyfields)) {
 			errorFlag=true;
 		}
 		if (!isValidEmailField("#emailAddress")) {
 			errorFlag=true;
 		}
 		// console.log("errorflag="+errorFlag);
 	
 		var password = $("#password").val();
 	
  
 		if (!errorFlag) {
 		
 			 cleanWarn("#emailAddress");
 			 document.forms['agentForm'].action='agentSignupProcess.html';
 			 document.forms['agentForm'].submit();	
  		} 
 	}	
 	$("#fileUpload").click(function () {
 		
 		  dataString = $("#agentForm").serialize().replace(/%2C/g,'');
 	 
 		
 	});
 	 
 	
 	
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
   
	var gmailEmptyfields = {
		   	 'Email Address': '#emailAddress',
		   //  'Password': '#password',
	}   
	  
  function doEmailTest() {
		var email= document.forms['agentForm'].emailAddress.value; 
    	var password=document.forms['agentForm'].password.value;		
		var errorFlag=false;		   
		if (isEmpty(gmailEmptyfields)) {
			
			var errorFlag=true;
		}
	    if (!errorFlag) {
	     
	    
	    	popUpFixedName('/configureEmailServer.html?emailAddress='+email+"&password="+password,'600','350');
	    }
  }	     
	
	
 function forgetPassword() {
	 var prevPageEmailAddress =document.forms['agentForm'].emailAddress.value; 
	 
	 if (isBlank(prevPageEmailAddress)) {
		 popUpFixedName('/forgetPassword.html','600','300');
	 } else {
		 popUpFixedName('/forgetPassword.html?prevPageEmailAddress='+prevPageEmailAddress,'600','350');
	 }
 }
 
 /**
  *  Check if verify code match
  */
 function checkVerifyCodeMatch() {
	 var realCode=document.forms[0].realCertPicCode.value;
	 var enteredCode=document.forms[0].certPicCode.value; 
	 alert("realcode="+realCode+",enteredCode="+enteredCode);
	 if (realCode==enteredCode) {
		 return true;
	 }
	 return false;
 }
 /**
  *   Below Section for Agent Signup
  */
	var signupIsEmptyfields = {
		   	 'Email Address': '#emailAddress',
		     'Confirm Password': '#confirmPassword',
		     'First Name': '#firstName',
		     'Company Name': '#companyName',
		     'License Eligible State': '#licenseEligibleState',
		     'license No': '#licenseNo',
		     'Work Phone No': '#workPhone',
		     'Your Picture': '#pictureContent',
		     'Living State': '#state',	
		     'Text Code':	'#enteredCertPicCode'
		    
		 }; 
 
	
	/**
	 *  Encrypt password if leave password text box
	 */
	stringCryption.initialize("password");
	
 /**
  *  Submit Signup agent form
  */
	function signUpSubmitForm() {
	
 		var errorFlag=false;
 		if (isEmpty(signupIsEmptyfields)) {
 			errorFlag=true;
 		}
 		var loginAgentId = document.getElementById("loginAgentId").value;
 		 
 		if (isBlank(loginAgentId)) {
 			errorFlag =isPasswordEmpty("#password");
 		
 		}
 		
 		if (!isAcceptTermAndAgreement("termAgreement")) {
 			errorFlag = true;
 		}
 		
 		if (!isValidEmailField("#emailAddress")) {
 			errorFlag=true;
 		}
 		
 	   if (!isTextCodeMatch("6")) {
 		   errorFlag=true;
 	   }
 	    
 	   if (!errorFlag) {
 			// var encrypted_password = convertToSHA256($("#password").val());
 			// document.getElementById("password").value=encrypted_password;
   			 cleanWarn("#emailAddress");
 			 document.forms['agentForm'].action='agentSignupProcess.html';
   			 
   			 //document.forms['agentForm'].action='agentSignup.html';
 			 document.forms['agentForm'].submit();	
  		} 
 	}
 	
	


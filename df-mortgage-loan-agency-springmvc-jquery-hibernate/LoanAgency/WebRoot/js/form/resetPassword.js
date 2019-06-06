var gmailEmptyfields = {
	   	 'Password': '#password',
	   	 'Confirm Password': '#confirmPassword',
	};
  /**
	 *  Encrypt password if leave password text box
	 */
  stringCryption.initialize("password");
  stringCryption.initialize("confirmPassword");

  
  function resetPassword() {
	  
	   var errorFlag=false;		   
		if (isEmpty(gmailEmptyfields)) {			
			var errorFlag=true;
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
	         document.forms[0].action="resetPassword.html";
			 document.forms[0].submit();
		}
  }
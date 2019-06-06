 
   /**
    *   Below Section for Agent Login
    */
 	var loginIsEmptyfields = {
	   	 'Email Address': '#emailAddress',
	     'Password': '#password'	     
	 };
 	
 	
 	
 	function settingEmailServer() {
 		var email = $("#emailAddress").val();
 		var errorFlag=false;
 		if (isEmpty(loginIsEmptyfields)) {
 			errorFlag=true; 
 		}
 		
 		if (!isValidEmailField($("#emailAddress"))) {
 			errorFlag=true; 
 			
 			 
 		}
	   
 		 if (!errorFlag) {
 			 cleanWarn("#emailAddress"); 	
 			 document.forms['emailServerForm'].action='configureEmailServer.html'; 		 
 			 document.forms['emailServerForm'].submit();
 		 }
 	}
 	
  

    
	  
	     
	
	

	
	
	 
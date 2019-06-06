 

   /**
    *   Below Section for Agent Login
    */
 	var loginIsEmptyfields = {
	   	 'Email List': '#emailList',
	     
	 };
 	function doSubmitForm() {
 		var email = $("#emailList").val();
 		var errorFlag=false;
 		if (isEmpty(loginIsEmptyfields)) {
 			errorFlag=true; 
 		}
 		
 		 
 		 if (!errorFlag) {
 			 document.forms['emailListForm'].actionType.value="saveEmailList";
 			 document.forms['emailListForm'].action='doEmailList.html';
 			 document.forms['emailListForm'].submit();
 		 }
 	}
 	
 	
 	 
	 
	function uploadEmailListFile() {
	  
		// document.forms['emailListForm'].actionType.value="uploadEmailListFile";		 
	 	 document.forms['emailListForm'].action='doEmailList.html';
	 	 document.forms['emailListForm'].submit();
	 		 
	} 
   
    
	  
	     
	
	

	
	
	 
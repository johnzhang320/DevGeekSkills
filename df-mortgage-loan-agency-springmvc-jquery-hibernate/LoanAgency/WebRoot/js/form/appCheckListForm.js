 
	
   /**
    *   Below Section for Agent Login
    */
 	var fieldNames = {
	   	 'Title ': '#title' 
	    
	 };
 	
 	function submitform() { 		 
 		 errorFlag=false; 		 
 		  if (isEmpty(fieldNames)) {
 			  errorFlag=true;
 		  }
		 if (!errorFlag) {	   
			 
			 document.forms[0].submit(); 	
			  
			
		 } else {
			 messageDisplay(msg,"warn");  		
		 }
 	}
 	
  
 	function cancelform() { 		 
		  		
		   
		 document.forms['appCheckList_form'].title.value='';
		 document.forms['appCheckList_form'].note.value='';
			
		 
	}
	
    
	  
	     
	
	

	
	
	 
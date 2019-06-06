 
	
   /**
    *   Below Section for Agent Login
    */
 	var fieldNames = {
	   	 'Niche Title ': '#nicheTitle' 
	    
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
		 
 		
		   
		 document.forms['niche_form'].nicheTitle.value='';
		 document.forms['niche_form'].nicheNote.value='';
			
		 
	}
	
    
	  
	     
	
	

	
	
	 
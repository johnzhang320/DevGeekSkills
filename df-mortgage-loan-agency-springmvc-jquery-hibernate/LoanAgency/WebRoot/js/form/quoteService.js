	 var termFields = {
			 '30 years fixed':'30yr',
			 '20 years fixed':'20yr',
			 '15 years fixed':'15yr',
			 '10/1 ARM':'10yr',
			 '7/1 ARM': '7yr', 
			 '5/1 ARM': '5yr', 
			 '3/1 ARM': '3yr' };

	     
	 $.each (termFields, function(key,value) {
   		 $("#"+value).click(function(event){
   			// alert("key="+key+",value="+value);
   			 var checked = document.getElementById(value).checked;
   			 if  (checked) {
   				 var str = $(this).val();
   				 if (str==="") return;   				 
   			     var note = document.forms[0].note.value;
   				 note = note+"("+key+"). \n";    
		   		 document.forms[0].note.value =  note;
   				  
   			 }
   		  }); 
	  });  	
	 
	 function saveCheckedNiche(nicheTitle,nicheId) {
		   
		    var checked = document.getElementById(nicheId).checked;
		    var note = document.forms[0].note.value;
		    if (checked) { 		
		    	note =note+"("+nicheTitle+"). \n";    
		   		document.forms[0].note.value =  note;
		    } 
		   
		}	   	 
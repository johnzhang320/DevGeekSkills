 
	
   /**
    *   Below Section for Agent Login
    */
 	var fieldNames = {
	   	 'Niche Decription ': '#nicheIntro' 
	    
	 };
 	
 	function submitNicheIntro() { 		 
 		 errorFlag=false; 		 
 		 var nicheIntro = document.getElementById("nicheIntro").value;
 		  alert("nicheIntro="+nicheIntro);
 		  if (isEmpty(fieldNames)) {
 			  errorFlag=true;
 		  }
		 if (!errorFlag) {
		   
			 
			    var errorFlag=false;
			    var baseUrl = "nichesIntroSave.html?nicheIntro="+nicheIntro;
			    var nicheIntro = document.getElementById("nicheIntro").value;
			  
		      
			    
			    $.ajax({
					type: 'POST',			 
					dataType: "json",
					url: baseUrl,			
					success: function(data, textStatus, jqXHR){
						 document.getElementById('saveNicheIntroSuccess').style.display="black";
						 document.getElementById('nicheIntroSpan').value=data.successMessage;
					},
					   error: function(jqXHR, textStatus, errorThrown){
						 
					}
				});
		 
			
		 } 
 	}
 	
  
 	function cancelform() { 		 
		 
 		
		   
		 document.forms['niche_form'].nicheTitle.value='';
		 document.forms['niche_form'].nicheNote.value='';
			
		 
	}
	
    
	  
	     
	
	

	
	
	 
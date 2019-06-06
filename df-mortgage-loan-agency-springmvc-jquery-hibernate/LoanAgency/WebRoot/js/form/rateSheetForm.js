 	
	  // use jquery each do required fields checking
	 var fieldNames = {
	   	 'Conforming 30 Yr Fixed Rate ': '#conf30yr',
	     'Super Conforming 30 Yr Fixed Rate ': '#superConf30yr',
	  	 'Conforming 15 Yr Fixed Rate ': '#conf15yr',
	     'Super Conforming 15 Yr Fixed Rate ': '#superConf15yr',
	    
	     };
	     
	// $("#loanAmountLabel").css('color','#ff0000');
	 

	 
	function submitRateSheetform() {
	  /**
	   *  Check if empty only here
	   */
	  var errorFlag=false;
	  
	  if (isEmpty(fieldNames)) {
		  errorFlag=true;
	  }
	    
	
	
	  /**
	   *  check if loan amount > purchase price
	   */
	  if (!errorFlag) {
	  	   $("#form_action").attr('name', '_target1');
	   	   $("#form_action").attr('value', 'Next');
	   	   /**
	   	    *  Maybe agentLogin.html or agentEnterForm.html forward control to rateSheetForm inside of server, which is very quickly
	   	    *  but in browser address bar still show the agentLogin or agentEnterForm, this is way to hide the
	   	    *  address and when child form will be submitted , we must specify the form action to point child form
	   	    *  From Architecture view, we need a leading page call system menu.jsp by ModelAndView, then people only see this leading page 
	   	    *  url and never see any address in address bar
	   	    */
	  	     document.forms['rateSheetForm'].action='rateSheetForm.html';
	    	 document.forms['rateSheetForm'].submit();
	    }
	 }  	  
	    
	function cancelform() {
	
		 $.each (fieldNames, function(key,value) {
	      
          var str = $(value).val();
          var label = value+"Label";
          var msg = value+"Msg";
          	$(value).css('border','1px solid #888888');
	      			$(label).css('color','#1040A3');
	      			 $(msg).html("");
	      			 $(value).val("");
	         
	    });
	}
	

	
	
	 
	 
 
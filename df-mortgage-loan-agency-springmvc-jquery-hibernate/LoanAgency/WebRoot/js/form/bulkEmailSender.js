  
    $("#sendEmailList").click(function () {
 		  document.getElementById("fromEmailAddress").style.display="none";	
 		   
	});
    $("#sendEmailToMe").click(function () { 		
    	 document.getElementById("fromEmailAddress").style.display="block";	 
		  
	});
   /**
    *   Below Section for Agent Signup
    */
 	var emailMarketingFields = {
 		   	 'From Email Address': '#fromEmailAddress',
 		     'Subject': '#subject',
 		     'Email Content': '#emailContent'	  
 		      
 	 };
 	
 	 
 	/**
 	 *  We will use subject as download file name
 	 */
	var emailContentFields = {
			 'Subject': '#subject',
		    
		      
		 };
 	 
 	
	function uploadEmailContent() {
		 			 
 			 document.getElementById("actionType").value="uploadEmailContent";
 			 document.forms['emailForm'].action="bulkEmailSender.html";
 			 document.forms['emailForm'].submit();	
  		 
	}
	      
	function downloadEmailContent() {
		var errorFlag=false;
 		if (isEmpty(emailContentFields)) {
 			errorFlag=true;
 		}
 		//var emailContent = document.getElementById("emailContent").value;
 		 
  		if (!errorFlag) { 			 
 			 document.getElementById("actionType").value="saveEmailContent";
 			 document.forms['emailForm'].action="bulkEmailSender.html";
 			 document.forms['emailForm'].submit();	
  		} 
	} 
	
   function update() {
         document.getElementById("emailSendStatus").style.display="none";
         settimeout("refeshEmailStatus",100);
   }
   function refeshEmailStatus() {  	   
   	  
   	  document.getElementById("emailSendStatus").style.display="block";
   	  
   }
  
	
  function sendBatchEmail() {
 		
 		var msg="";  
 		var esConnectionStatus = document.forms[0].esConnectionStatus.value;
 		var authUserName = document.forms[0].authUserName.value;
 		var authPassword = document.forms[0].authPassword.value;
 		var fromEmailAddress = document.forms[0].fromEmailAddress.value;
 		var subject = document.forms[0].subject.value;
 		var emailContent = document.forms[0].emailContent.value;
 		var errorFlag=false;
 		 
 		var emailList = document.getElementById("emailList").value;
 		var emailListStatus =  document.getElementById("emailListStatus").value;
 		
 		if (isBlank(authUserName) || isBlank(authPassword) || isBlank(esConnectionStatus) || ("OK"!=esConnectionStatus) ) {			 		 
 			errorFlag=true;
 			msg+="Email Server has not been correctly setup yet !<br>";
 		}
 		 
 		 
 		var sendToEmailList =true;
 		var sendToMe = false;
 		/**
 		 *  Always send by email list even send single email like reply, reply set recipient list
 		 */
 				
 		   
 		   document.getElementById("sendEmailList").value="emailList";
   		 
 		
 		if (sendToEmailList) {
 			if (isBlank(emailList) || "notCreated"==emailList ) {
 				errorFlag=true;
 				msg+="Email List has not been created yet ! <br>";

 			}  
 		}
 		
 	 
 			
 			if (isBlank(fromEmailAddress)) {
 				
 				errorFlag=true;
 				msg+="Single Email Address is required!<br>";
 			} else {
 				if (!isValidEmail(fromEmailAddress)) {
 					errorFlag=true;
 					msg+="Single Email Address: "+ fromEmailAddress+" is invalid!<br>";
 				}
 			}
 		 
 		
 		if (isBlank(subject) ) {
 			errorFlag=true;
 			msg+="Subject is required!<br>";

 		}  
 		if (isBlank(emailContent) ) {
 			errorFlag=true;
 			msg+="Email Content has not been created yet!<br>";

 		}  
 		
 		if (!errorFlag) { 		
 			 document.getElementById("cProgressSamll").style.display="block";
 		      $("#errorBox").css('display','none');
 		     document.getElementById("actionType").value="sendEmailContent";
			 document.forms['emailForm'].action="bulkEmailSender.html";
			 document.forms['emailForm'].submit();	
  		} else {  			 
  			 messageDisplay(msg,"warn");  			 
  		} 
 		
 	}
 
 	function generateAgentEmailContent() {
 		var errorFlag=false;
 	//	if (isEmpty(emailContentFields)) {
 		//	errorFlag=true;
 		//}
 		//var emailContent = document.getElementById("emailContent").value;
 		 document.getElementById("cProgressSamll").style.display="block";
  		if (!errorFlag) { 			 
  			 document.getElementById("cProgressSamll").style.display="block";  	 		
 			 document.getElementById("actionType").value="generateAgentEmailContent";
 			 document.forms['emailForm'].action="bulkEmailSender.html";
 			 document.forms['emailForm'].submit();	
  		} 
 	}
 	 
   
 	function FromEmailAddress(view) {
 		 document.getElementById("fromEmailAddress").stype.display=view;
 	}
	/**
	 *  Save subject, leave subject activate
	 */  
	 $("#subject").blur(function () {
 		 var subject = $("#subject").val();	 
 		
 		 if (isBlank(subject)) { 
 			 return;
 		 }
 		
 		saveSubject(subject);
	 });	
	 function saveSubject(subject) { 	    
			    var baseUrl = "saveEmailSubject.html?emailSubject="+subject;			     
			    $.ajax({
					type: 'POST',			 
					dataType: "json",
					url: baseUrl,			
					success: function(data, textStatus, jqXHR){
						
					},
					   error: function(jqXHR, textStatus, errorThrown){
 					}
				});
 	}
		function cleanEmailAll() {
			var r=confirm("'Clean up Email' will remove all current email content, email listing, email subject and all attachments if you added. \n Do you really want to clean? Press OK to proceed.");
			if (r==true)
			  {
				  document.getElementById("actionType").value="cleanEmail";
				  document.forms['emailForm'].action="bulkEmailSender.html";
				  document.forms['emailForm'].submit();	
	 		      
			  }
			 
		} 	 
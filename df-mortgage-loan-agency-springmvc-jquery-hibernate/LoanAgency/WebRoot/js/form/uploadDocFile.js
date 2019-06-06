	
   /**
    *   Below Section for Agent Login
    */
 	var loginIsEmptyfields = {
	   	 'Email List ': '#emailAddress',
	     'Password': '#password'	     
	 };
 	function uploadDocFile() { 		 
 		  errorFlag=false; 		 
 		 document.getElementById("cProgressSamll").style.display="block";
 		 document.getElementById("cProgressSamll").style.left="60px;";
 		 if (!errorFlag) {
 		     document.getElementById("actionType").value="uploadDocFile";
 			 document.forms['emailServerForm'].action='uploadDocFile.html';
 			 document.forms['emailServerForm'].submit();
 		 }
 	}
 	
  
 	function applyEmailList() { 		 
		  errorFlag=false; 		 
		  document.getElementById("cProgressSamll").style.display="block";
		  var emailList = document.getElementById("emailList").value;
		  var msg="";
		  if (isBlank(emailList)  ) {
	 			errorFlag=true;
	 			msg+="Email List has not been created yet ! <br>";

	 	 }  
		 if (!errorFlag) {
		     document.getElementById("actionType").value="saveChangedEmailList";
			 document.forms['emailServerForm'].action='uploadDocFile.html';
			 document.forms['emailServerForm'].submit();
			
		 } else {
			 messageDisplay(msg,"warn");  		
		 }
	}
	
	function downloadEmailContent() {
		
		var errorFlag=false;
		var emailList = document.getElementById("emailList").value;
		 
		
		  if (isBlank(emailList) || emailList=="") {
	 			errorFlag=true;
	 	 }  
 		 
  		if (!errorFlag) { 	
  			// document.getElementById("cProgressSamll").style.display="block";
 			 document.getElementById("actionType").value="downLoadEmailList";
 			 document.forms['emailServerForm'].action="uploadDocFile.html";
 			 document.forms['emailServerForm'].submit();	
  		} else {
  			 $("#errorBox").css('display','block');
  			 $("#errorBox").html("Email List has not been created yet ! <br>"); 
			 		
		 }
	} 
	
    
	function SelectFirstName(firstNamePtr) {
		document.getElementById("actionType").value="chosenFirstNamePtr";
		document.getElementById("firstNamePtr").value=firstNamePtr;
		document.forms['emailServerForm'].action='uploadDocFile.html';
		//document.forms['emailServerForm'].submit();
	}
	function SelectEmail(emailPtr) {
		document.getElementById("actionType").value="chosenEmailPtr";
		document.getElementById("emailPtr").value=emailPtr;
		document.forms['emailServerForm'].action='uploadDocFile.html';
		//document.forms['emailServerForm'].submit();
	} 
	     
	function confirmChoose() {
		var errorFlag=false;
		var firstNamePtr = document.getElementById("firstNamePtr").value;
		var emailPtr = document.getElementById("emailPtr").value;
		if (isBlank(firstNamePtr)) {
			 $("#errorBox").css('display','block');
  			 $("#errorBox").html("Please select first name column ! <br>"); 
  			errorFlag=true;		
		}
		if (isBlank(emailPtr)) {
			 $("#errorBox").css('display','block');
 			 $("#errorBox").html("Please select email address column ! <br>"); 
 			errorFlag=true;		
		}
		if (!errorFlag) {
			document.getElementById("actionType").value="confirmChosen";
			document.forms['emailServerForm'].action='uploadDocFile.html';
			document.forms['emailServerForm'].submit();
		}
	}
	

	
	
	 